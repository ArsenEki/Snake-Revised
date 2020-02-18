package authentication.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class holds the styling of the dialog components.
 */
public class DialogStyler {

    // Sets the font and other reusable properties.
    private static String font = "Lucida Grande";
    private static Color colorText = new Color(40, 180,100);
    private static Color colorTextField = new Color(0,70,50);

    /**
     * This method styles the big titles.
     */
    public static JLabel title(JLabel label, String title) {
        label.setFont(new Font(font, 0, 36));
        label.setForeground(colorText);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(title);
        label.setMaximumSize(new Dimension(200, 50));
        label.setMinimumSize(new Dimension(200, 50));
        label.setPreferredSize(new Dimension(300, 100));
        label.setSize(new Dimension(50, 20));
        return label;
    }

    /**
     * This method styles the smaller titles.
     */
    public static JLabel title1(JLabel label, String title) {
        label.setFont(new Font(font, 0, 28));
        label.setForeground(colorText);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(title);
        label.setMaximumSize(new Dimension(200, 50));
        label.setMinimumSize(new Dimension(200, 50));
        label.setPreferredSize(new Dimension(300, 100));
        label.setSize(new Dimension(50, 20));
        return label;
    }

    /**
     * This method styles the small titles.
     */
    public static JLabel title2(JLabel label, String title) {
        label.setFont(new Font(font, 0, 18));
        label.setForeground(colorText);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(title);
        return label;
    }

    /**
     * This method styles the textfields.
     */
    public static JTextField textField(JTextField field) {
        field.setBackground(colorTextField);
        field.setFont(new Font(font, 0, 14));
        field.setForeground(colorText);
        return field;
    }

    /**
     * This method styles the passwordfield.
     */
    public static JPasswordField passwordField(JPasswordField field) {
        field.setBackground(colorTextField);
        field.setForeground(colorText);
        return field;
    }

    /**
     * This method styles the buttons.
     */
    public static JButton button(JButton button, String title) {
        button.setBackground(colorTextField);
        button.setForeground(colorText);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setText(title);
        button.setMinimumSize(new Dimension(100,30));
        return button;
    }

}
