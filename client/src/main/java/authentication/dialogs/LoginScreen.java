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
 * Allows user to fill in username and password and update to the sendUserToServer screen.
 */
@SuppressWarnings({"PMD.DataflowAnomalyAnalysis"})
public class LoginScreen extends JDialog {

    @Autowired
    transient UserService userService = new UserService();

    public static final long serialVersionUID = 4328743;

    private transient JPanel backGround;
    private transient JLabel userLabel;
    private transient JLabel passwordLabel;
    private transient JButton loginButton;
    private transient JPasswordField password;
    private transient JButton registerButton;
    private transient JLabel title;
    private transient JTextField username;
    private transient JFrame frame;
    private boolean succeeded;

    /**
     * Creates new form LoginScreen.
     */
    public LoginScreen(Frame parent, boolean modal) {
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
        loginButton = new JButton();
        registerButton = new JButton();

        // Sets the font and other reusable properties.
        String font = "Lucida Grande";
        Color colorText = new Color(40, 180,100);
        Color colorTextField = new Color(0,70,50);
        Color colorBackground = new Color(0, 50, 50);

        // Sets the frame specifics
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(600, 450));
        getRootPane().setDefaultButton(loginButton);

        // Sets the background specifics
        backGround.setBackground(colorBackground);
        backGround.setPreferredSize(new Dimension(600, 450));

        // Sets the title specifics
        DialogStyler.title(title, "Snake Game");

        // Sets the label for the username
        DialogStyler.title2(userLabel, "Username");

        // Sets the password label
        DialogStyler.title2(passwordLabel, "Password");

        // Sets the username textfield specifics
        DialogStyler.textField(username);

        // Sets the password textfield specifics
        DialogStyler.passwordField(password);

        // Sets specifics for the login button
        DialogStyler.button(loginButton, "Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        // Sets specifics for the register button
        DialogStyler.button(registerButton, "Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        // Placement of components
        {
            GroupLayout backGroundLayout = new GroupLayout(backGround);
            backGround.setLayout(backGroundLayout);

            DialogComponentPlacement.componentPlacement(backGroundLayout, title,
                    userLabel, passwordLabel, password, username, loginButton, registerButton);

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


    private void loginButtonActionPerformed(ActionEvent evt) {

        // If the username isn't filled in, this error message is shown.
        if (getUsername().equals("") || getUsername() == null) {
            JOptionPane.showMessageDialog(LoginScreen.this,
                    "Hi, please fill in a username.",
                    "Hold your snakes.",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // If the password isn't filled in, this error message is shown.
        if (getPassword().equals("") || getPassword() == null) {
            JOptionPane.showMessageDialog(LoginScreen.this,
                    "Hi, please fill in a password.",
                    "Hold your snakes.",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Creates a user and sets the username and password to the filled in values.
        User user = new User(getUsername(), getPassword());
        // Sends the user to the authenticationService and if true allow access to game.
        // Disposes screen afterwards.
        if (userService.sendUserToServer(user, "/login")) {
            JOptionPane.showMessageDialog(LoginScreen.this,
                    "Hi " + getUsername() + "! You have successfully logged in.",
                    "Sssuccesss",
                    JOptionPane.INFORMATION_MESSAGE);
            setSucceeded(true);
            StartScreenNew.setUser(user);
            dispose();
            StartScreenNew startScreenNew = new StartScreenNew(frame, true);
            startScreenNew.setVisible(true);

        } else {
            // Show an error message with relevant information.
            JOptionPane.showMessageDialog(LoginScreen.this,
                    "Invalid username or password",
                    "Something went wrong.",
                    JOptionPane.ERROR_MESSAGE);
            // Reset username and password.
            resetUsername();
            resetPassword();
            setSucceeded(false);
        }
    }

    private void registerButtonActionPerformed(ActionEvent evt) {
        // Creates and shows a RegisterScreen.
        dispose();
        RegisterScreen registerScreen = new RegisterScreen(frame, true);
        registerScreen.setVisible(true);
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

    /**
     * Sets whether or not the login information is correct.
     * @param set the succes value to be set
     */
    public void setSucceeded(boolean set) {
        this.succeeded = set;
    }

    /**
     * Returns whether or not the login information is correct.
     *
     * @return boolean existing login information
     */
    public boolean isSucceeded() {
        return succeeded;
    }

    /**
     * Resets the username field.
     */
    public void resetUsername() {
        username.setText("");
    }

    /**
     * Resets the password field.
     */
    public void resetPassword() {
        password.setText("");
    }

}
