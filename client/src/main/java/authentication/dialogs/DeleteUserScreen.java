package authentication.dialogs;

import gui.StartScreenNew;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Allows user to delete an existing user.
 */
@SuppressWarnings({"PMD.DataflowAnomalyAnalysis"})
public class DeleteUserScreen extends JDialog {

    @Autowired
    transient UserService userService = new UserService();

    public static final long serialVersionUID = 4328743;

    private transient JPanel backGround;
    private transient JLabel userLabel;
    private transient JLabel passwordLabel;
    private transient JButton deleteUserButton;
    private transient JPasswordField password;
    private transient JButton cancelButton;
    private transient JLabel title;
    private transient JTextField username;
    private transient JFrame frame;

    /**
     * Creates new form LoginScreen.
     */
    public DeleteUserScreen(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

        backGround = new JPanel();
        title = new JLabel();
        username = new JTextField();
        userLabel = new JLabel();
        password = new JPasswordField();
        passwordLabel = new JLabel();
        deleteUserButton = new JButton();
        cancelButton = new JButton();

        // Sets the font and other reusable properties.
        String font = "Lucida Grande";
        Color colorText = new Color(40, 180,100);
        Color colorTextField = new Color(0,70,50);
        Color colorBackground = new Color(0, 50, 50);

        // Sets the frame specifics
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(600, 450));
        getRootPane().setDefaultButton(deleteUserButton);

        // Sets the background specifics
        backGround.setBackground(colorBackground);
        backGround.setPreferredSize(new Dimension(600, 450));

        // Sets the title specifics
        DialogStyler.title1(title, "Kill your snake");

        // Sets the label for the username
        DialogStyler.title2(userLabel, "Username");

        // Sets the password label
        DialogStyler.title2(passwordLabel, "Password");

        // Sets the username textfield specifics
        DialogStyler.textField(username);

        // Sets the password textfield specifics
        DialogStyler.passwordField(password);

        // Sets specifics for the delete user button
        DialogStyler.button(deleteUserButton, "Delete User");
        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        // Sets specifics for the cancel button
        DialogStyler.button(cancelButton, "Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        // Placement of components
        {
            GroupLayout backGroundLayout = new GroupLayout(backGround);
            backGround.setLayout(backGroundLayout);

            DialogComponentPlacement.componentPlacement(backGroundLayout, title,
                    userLabel, passwordLabel, password, username, deleteUserButton, cancelButton);

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(backGround, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(backGround, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            );
        }

        pack();
        setLocationRelativeTo(getParent());
    }


    private void deleteUserButtonActionPerformed(ActionEvent evt) {
        // If the username isn't filled in, this error message is shown.
        if (getUsername().equals("") || getUsername() == null) {
            JOptionPane.showMessageDialog(DeleteUserScreen.this,
                    "Hi, please fill in a username.",
                    "Hold your snakes.",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println(1);
            return;
        }
        // If the password isn't filled in, this error message is shown.
        if (getPassword().equals("") || getPassword() == null) {
            JOptionPane.showMessageDialog(DeleteUserScreen.this,
                    "Hi, please fill in a password.",
                    "Hold your snakes.",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println(2);
            return;
        }

        // Creates a user and sets the username and password to the filled in values.
        User user = new User(getUsername(), getPassword());
        // Sends the user to the authenticationService and deletes the account.
        // Disposes screen afterwards.

        if (!user.getUserName().equals(StartScreenNew.getUser().getUserName())) {
            //Must delete own user
            JOptionPane.showMessageDialog(DeleteUserScreen.this,
                    "You can only delete your own snake.",
                    "Hold your snakes friendo.",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println(3);

            return;
        }

        if (!userService.sendUserToServer(user, "/login")) {
            // Credentials must be correct.
            JOptionPane.showMessageDialog(DeleteUserScreen.this,
                    "Invalid credentials, please try again.",
                    "Hold your snakes friendo.",
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println(3);

            return;
        }

        Boolean success = userService.sendUserToServer(user, "/delete");
        if (success) {
            JOptionPane.showMessageDialog(DeleteUserScreen.this,
                    "You have successfully deleted the account.",
                    "Sssuccesss",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            LoginScreen loginScreen = new LoginScreen(frame, true);
            loginScreen.setSucceeded(false);
            loginScreen.setVisible(true);
            System.out.println(4);

        } else {
            // If the username doesn't exist, this errormessage is shown.
            JOptionPane.showMessageDialog(DeleteUserScreen.this,
                    "Snake doesn't exist, try another name!",
                    "Hold your snakes friendo.",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println(5);

    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        dispose();
        StartScreenNew startScreenNew = new StartScreenNew(frame, true);
        startScreenNew.setVisible(true);
    }

    /**
     * Returns the username typed in the textbox.
     *
     * @return String username
     */
    public String getUsername() {
        return username.getText().trim();
    }

    /**
     * Returns the password typed in the textbox.
     *
     * @return String password
     */
    public String getPassword() {
        return new String(password.getPassword());
    }
}
