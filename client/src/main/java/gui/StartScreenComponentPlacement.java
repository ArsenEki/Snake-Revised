package gui;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle;

/**
 * This class takes care of the placement of the components on the start screen.
 */
public class StartScreenComponentPlacement {

    /**
     * This method will set up the placement of the components.
     */
    public static void placementGroupOne(JPanel panel, GroupLayout groupLayout,
                                         JLabel levelmusicLabel, JLabel levelmodeLabel,
                                         JLabel levelnumberLabel, JLabel selectLevelLabel,
                                         JLabel highscoreLabel, JButton startGameButton,
                                         JButton deleteUserButton, JButton logoutButton,
                                         JButton helpButton, JTable highscoresTable,
                                         JPanel badgePanel, JComboBox snakeMode,
                                         JComboBox snakeLevel, JComboBox snakeMusic) {
        panel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addGap(66, 66, 66)
                                .addGroup(groupLayout.createParallelGroup(
                                    GroupLayout.Alignment.LEADING, false)
                                    .addComponent(levelmusicLabel)
                                    .addComponent(levelmodeLabel)
                                    .addComponent(snakeMode, 0,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(levelnumberLabel)
                                    .addComponent(snakeLevel, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(snakeMusic, GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startGameButton, GroupLayout.PREFERRED_SIZE,
                                            0, Short.MAX_VALUE)))
                            .addGroup(groupLayout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addComponent(selectLevelLabel)))
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(highscoreLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING,
                                groupLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(badgePanel, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35)
                                    .addGroup(groupLayout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addComponent(highscoresTable, GroupLayout.PREFERRED_SIZE,
                                            142, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteUserButton, GroupLayout.PREFERRED_SIZE,
                                            150, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE,
                                            150, GroupLayout.PREFERRED_SIZE))
                                    .addGap(62, 62, 62))))
                .addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(helpButton)
                    .addGap(25, 25, 25))
        );
    }

    /**
     * This method will set up the placement of the components.
     */
    public static void placementGroupTwo(GroupLayout groupLayout, JLabel selectLevelLabel,
                                         JLabel levelmodeLabel, JLabel highscoreLabel,
                                         JLabel levelnumberLabel, JLabel levelmusicLabel,
                                         JButton helpButton, JButton deleteUserButton,
                                         JButton logoutButton, JButton startGameButton,
                                         JTable highscoresTable, JComboBox snakeMusic,
                                         JComboBox snakeMode, JComboBox snakeLevel,
                                         JPanel badgePanel) {
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                        .addComponent(helpButton)
                        .addGap(106, 106, 106)
                        .addComponent(highscoresTable, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(snakeMusic, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(deleteUserButton, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE,
                                            31, GroupLayout.PREFERRED_SIZE))
                                .addComponent(startGameButton, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(49, 49, 49))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(selectLevelLabel)
                            .addComponent(highscoreLabel))
                        .addGap(22, 22, 22)
                        .addComponent(levelmodeLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(snakeMode, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(levelnumberLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(snakeLevel, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(levelmusicLabel))
                            .addGroup(groupLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(badgePanel, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
        );
    }
}
