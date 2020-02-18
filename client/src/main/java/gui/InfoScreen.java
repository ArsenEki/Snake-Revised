package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 * This class sets up the info screen.
 */
@SuppressWarnings({"PMD.BeanMembersShouldSerialize"})
public class InfoScreen extends JDialog {

    public static final long serialVersionUID = 4328743;

    private JButton readyButton;
    private JLabel welcomeTitle;
    private JLabel goodLuckLabel;
    private JPanel panel;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane3;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JTextPane textPane3;

    /**
     * Creates new info screen.
     */
    public InfoScreen(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        panel = new JPanel();
        welcomeTitle = new JLabel();
        scrollPane1 = new JScrollPane();
        textPane1 = new JTextPane();
        scrollPane2 = new JScrollPane();
        textPane2 = new JTextPane();
        scrollPane3 = new JScrollPane();
        textPane3 = new JTextPane();
        goodLuckLabel = new JLabel();
        readyButton = new JButton();

        // Screen settings
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new Rectangle(0, 23, 600, 450));
        setPreferredSize(new Dimension(600, 450));
        setResizable(false);
        setSize(new Dimension(400, 300));
        setLocationRelativeTo(getParent());
        setAlwaysOnTop(true);
        getRootPane().setDefaultButton(readyButton);

        // Panel background settings
        panel.setBackground(new Color(0, 50, 50));

        // Title settings
        welcomeTitle.setFont(new Font("Lucida Grande", 0, 24));
        welcomeTitle.setForeground(new Color(0, 170, 70));
        welcomeTitle.setText("Welcome to our Snake Game!");

        // First text pane setting
        textPane1.setText("Choosing what level you would like to play happens one the left "
                + "side of the screen. There you can pick whether you want a modern "
                + "looking snake or a classic Nokia like snake. You also get the option "
                + "to pick the level difficulty, that sets the level layout. And lastly the level "
                + "music sets the level background music. ");
        textPaneToScrollPane(scrollPane1, textPane1);

        // Second textpane setting
        textPane2.setText("The levels 2 and 3 have to be unlocked. You will need to have at "
                + "least 5 and 10 points respectively. Points are gained by either eating apples "
                + "or bananas. Apples award you with 1 point, bananas with 2! Be quick though, "
                + "if you don't reach the banana in time it turns brown and loses it's points!");
        textPaneToScrollPane(scrollPane2, textPane2);

        // Third testpane setting
        textPane3.setText("After the game has ended, your score gets saved and so new high "
                + "scores can be set! Compare your score with other players and try to get the "
                + "Nr.1 Player badge! ");
        textPaneToScrollPane(scrollPane3, textPane3);

        // Sets the good luck wish
        goodLuckLabel.setFont(new Font("Lucida Grande", 0, 18));
        goodLuckLabel.setForeground(new Color(0, 170, 70));
        goodLuckLabel.setText("Good Luck!");

        // Sets the button
        StartScreenComponentStyler.button(readyButton, "I'm Ready!");
        readyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });


        // Sets the components
        {
            GroupLayout groupLayout = new GroupLayout(panel);
            panel.setLayout(groupLayout);
            groupLayout.setHorizontalGroup(
                    groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(groupLayout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(welcomeTitle, GroupLayout.PREFERRED_SIZE, 500,
                                                            GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING,
                                                    groupLayout.createSequentialGroup()
                                                            .addGap(50, 50, 50)
                                                            .addGroup(groupLayout.createParallelGroup(
                                                                    GroupLayout.Alignment.TRAILING, false)
                                                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 500,
                                                                            GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 500,
                                                                            GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 500,
                                                                            GroupLayout.PREFERRED_SIZE)
                                                                    .addGroup(GroupLayout.Alignment.LEADING,
                                                                            groupLayout.createSequentialGroup()
                                                                                    .addComponent(goodLuckLabel)
                                                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                    .addComponent(readyButton)))))
                                    .addContainerGap(50, Short.MAX_VALUE))
            );

            groupLayout.setVerticalGroup(
                    groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(welcomeTitle, GroupLayout.PREFERRED_SIZE,
                                            52, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 60,
                                            GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(goodLuckLabel, GroupLayout.PREFERRED_SIZE, 52,
                                                    GroupLayout.PREFERRED_SIZE)
                                            .addComponent(readyButton))
                                    .addContainerGap(20, Short.MAX_VALUE))
            );

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(panel, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(panel, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }
    }

    /**
     * Method to link textpane to scrollpane.
     */
    private void textPaneToScrollPane(JScrollPane scrollPane, JTextPane textPane) {
        scrollPane.setViewportView(textPane);
        scrollPane.setViewportBorder(null);
        scrollPane.setBorder(null);
        textPane.setEditable(false);
        textPane.setForeground(new Color(0, 170, 70));
        textPane.setBackground(new Color(0, 50, 50));
        textPane.setOpaque(true);
    }
}
