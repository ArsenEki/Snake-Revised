package authentication.dialogs;

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
 * Allows user to fill in username and password and update to the sendUserToServer screen.
 */
@SuppressWarnings({"PMD.DataflowAnomalyAnalysis"})
public class RegisterScreen extends JDialog {

    @Autowired
    transient UserService userService = new UserService();

    public static final long serialVersionUID = 4328743;

    private transient JPanel backGround;
    private transient JLabel userLabel;
    private transient JLabel passwordLabel;
    private transient JButton registerButton;
    private transient JPasswordField password;
    private transient JButton cancelButton;
    private transient JLabel title;
    private transient JTextField username;
    private transient JFrame frame;
    private boolean succeeded;

    /**
     * Creates new form RegisterScreen.
     */
    public RegisterScreen(Frame parent, boolean modal) {
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
        registerButton = new JButton();
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
        getRootPane().setDefaultButton(registerButton);

        // Sets the background specifics
        backGround.setBackground(colorBackground);
        backGround.setPreferredSize(new Dimension(600, 450));

        // Sets the title specifics
        DialogStyler.title1(title, "Create a new snake");

        // Sets the label for the username
        DialogStyler.title2(userLabel, "Username");

        // Sets the password label
        DialogStyler.title2(passwordLabel, "Password");

        // Sets the username textfield specifics
        DialogStyler.textField(username);

        // Sets the password textfield specifics
        DialogStyler.passwordField(password);

        // Sets specifics for the register button
        DialogStyler.button(registerButton, "Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerButtonActionPerformed(evt);
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
                    userLabel, passwordLabel, password, username, registerButton, cancelButton);

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


    private void registerButtonActionPerformed(ActionEvent evt) {
        // If the username isn't filled in, this error message is shown.
        if (getUsername().equals("") || getUsername() == null) {
            JOptionPane.showMessageDialog(RegisterScreen.this,
                    "Hi, please fill in a username.",
                    "Hold your snakes.",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // If the password isn't filled in, this error message is shown.
        if (getPassword().equals("") || getPassword() == null) {
            JOptionPane.showMessageDialog(RegisterScreen.this,
                    "Hi, please fill in a password.",
                    "Hold your snakes.",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Creates a user and sets the username and password to the filled in values.
        User user = new User(getUsername(), getPassword());
        // Sends the user to the authenticationService and creates a new account.
        // Disposes screen afterwards.
        Boolean success = userService.sendUserToServer(user, "/signup");
        if (success) {
            JOptionPane.showMessageDialog(RegisterScreen.this,
                    "Hi " + getUsername() + "! You have successfully registered.",
                    "Sssuccesss",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            LoginScreen loginScreen = new LoginScreen(frame, true);
            loginScreen.setVisible(true);
        } else {
            // If the username has already been taken, this errormessage is shown.
            JOptionPane.showMessageDialog(RegisterScreen.this,
                    "Hi, try another username!.",
                    "Hold your snakes friendo.",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        dispose();
        LoginScreen loginScreen = new LoginScreen(frame, true);
        loginScreen.setVisible(true);
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
