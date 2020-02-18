package authentication.dialogs;

import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;

/**
 * This class creates the splash screen that is shown first when the game starts.
 */
public class SplashScreen extends JFrame {

    // Variable declaration
    private transient JLabel titleLabel;
    private transient JLabel loadingLabel;
    private transient JPanel panel;
    public transient JProgressBar progressBar;
    public transient JLabel percentage;

    public static final long serialVersionUID = 4328743;

    /**
     * Creates new form SplashScreen.
     */
    public SplashScreen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

        // Setup reusable color and font settings
        Color colorText = new Color(0, 170, 70);
        Color colorBackGround = new Color(0, 40, 20);
        Font fontTitle = new Font("Lucida Grande", 1, 36);
        Font fontSmallText = new Font("Lucida Grande", 1, 14);

        panel = new JPanel();
        titleLabel = new JLabel();
        progressBar = new JProgressBar();
        loadingLabel = new JLabel();
        percentage = new JLabel();

        // Setting the default closing operation
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel.setBackground(colorBackGround);

        titleLabel.setFont(fontTitle);
        titleLabel.setForeground(colorText);
        titleLabel.setText("Snake");

        progressBar.setForeground(colorText);
        progressBar.setBackground(colorText);

        loadingLabel.setFont(fontSmallText);
        loadingLabel.setForeground(colorText);
        loadingLabel.setText("Loading...");

        percentage.setForeground(colorText);
        percentage.setText("100%");

        // Code to place the components
        {
            GroupLayout panelLayout = new GroupLayout(panel);
            panel.setLayout(panelLayout);

            SequentialGroup group = panelLayout.createSequentialGroup()
                .addContainerGap(40, 40)
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE,
                        400, GroupLayout.PREFERRED_SIZE)
                .addComponent(titleLabel))
                .addGap(50, 50, 50))
                .addGroup(GroupLayout.Alignment.TRAILING,
                        panelLayout.createSequentialGroup()
                .addComponent(loadingLabel, GroupLayout.PREFERRED_SIZE,
                        80, GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)));

            panelLayout.setHorizontalGroup(
                    panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(group)
                            .addGroup(panelLayout.createSequentialGroup()
                                    .addGap(215, 215, 215)
                            .addComponent(percentage)
                                    .addGap(215, 215, 215))
            );

            panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(titleLabel)
                    .addGap(100, 100, 100)
                    .addComponent(loadingLabel, GroupLayout.PREFERRED_SIZE,
                            20, GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(progressBar, GroupLayout.PREFERRED_SIZE,
                            30, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(percentage)
                    .addContainerGap(50, 50))
            );

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
        }

        pack();
    }

}
