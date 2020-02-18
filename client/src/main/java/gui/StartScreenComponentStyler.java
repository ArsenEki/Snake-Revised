package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 * This class makes sure the components on the start screen are styled correctly.
 */
public class StartScreenComponentStyler {

    private static Font fontTitle = new Font("Lucida Grande", 0, 24);
    private static Font fontTitle2 = new Font("Lucida Grande", 0, 18);
    private static Color colorText = new Color(0, 170, 70);
    private static Color colorText2 = new Color(40, 180, 100);
    private static Color colorTextField = new Color(0, 70, 50);
    private static Color colorBackground = new Color(0, 50, 50);

    /**
     * This method styles the big titles.
     */
    public static JLabel title(JLabel label, String title) {
        label.setFont(fontTitle);
        label.setForeground(colorText);
        label.setText(title);
        return label;
    }

    /**
     * This method styles the smaller titles.
     */
    public static JLabel title2(JLabel label, String title) {
        label.setFont(fontTitle2);
        label.setForeground(colorText2);
        label.setText(title);
        return label;
    }

    /**
     * This method styles the buttons.
     */
    public static JButton button(JButton button, String title) {
        button.setBackground(colorTextField);
        button.setForeground(colorText2);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setText(title);
        button.setMinimumSize(new Dimension(100,30));
        return button;
    }

    /**
     * This method styles the tables.
     */
    public static JTable table(JTable table) {
        table.setGridColor(colorTextField);
        table.setRowHeight(25);
        table.setBackground(colorTextField);
        table.setSelectionBackground(colorText);
        table.setSelectionForeground(colorTextField);
        table.setFont(fontTitle2);
        table.setForeground(colorText2);
        return table;
    }

}

