package authentication.dialogs;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 * This class handles the component placemet for the dialog classes.
 */
public class DialogComponentPlacement {

    /**
     * Method to place the components.
     */
    public static void componentPlacement(GroupLayout backGroundLayout, JLabel title,
                                          JLabel userLabel, JLabel passwordLabel,
                                          JPasswordField password, JTextField username,
                                          JButton upperButton, JButton lowerButton) {
        backGroundLayout.setHorizontalGroup(
            backGroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(title, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GroupLayout.Alignment.TRAILING,
                    backGroundLayout.createSequentialGroup()
                        .addContainerGap(200, Short.MAX_VALUE)
                        .addGroup(backGroundLayout.createParallelGroup(
                            GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING,
                                backGroundLayout.createSequentialGroup()
                                    .addComponent(userLabel, GroupLayout.PREFERRED_SIZE,
                                         200, GroupLayout.PREFERRED_SIZE)
                                    .addGap(200, 200, 200))
                            .addGroup(GroupLayout.Alignment.TRAILING,
                                 backGroundLayout.createSequentialGroup()
                                     .addComponent(passwordLabel,
                                         GroupLayout.PREFERRED_SIZE, 200,
                                         GroupLayout.PREFERRED_SIZE)
                                     .addGap(200, 200, 200))
                            .addGroup(GroupLayout.Alignment.TRAILING,
                                backGroundLayout.createSequentialGroup()
                                    .addGroup(backGroundLayout.createParallelGroup(
                                        GroupLayout.Alignment.TRAILING)
                                        .addComponent(password,
                                            GroupLayout.PREFERRED_SIZE, 200,
                                            GroupLayout.PREFERRED_SIZE)
                                        .addComponent(username,
                                            GroupLayout.PREFERRED_SIZE, 200,
                                            GroupLayout.PREFERRED_SIZE))
                                    .addGap(200, 200, 200))))
                .addGroup(backGroundLayout.createSequentialGroup()
                    .addGroup(backGroundLayout.createParallelGroup(
                        GroupLayout.Alignment.LEADING)
                        .addGroup(backGroundLayout.createSequentialGroup()
                            .addGap(250, 250, 250)
                            .addComponent(upperButton))
                        .addGroup(backGroundLayout.createSequentialGroup()
                            .addGap(250, 250, 250)
                            .addComponent(lowerButton)))
                    .addGap(0, 0, 0))
        );

        backGroundLayout.setVerticalGroup(
                backGroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backGroundLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(title, GroupLayout.PREFERRED_SIZE,
                                        100, GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(userLabel, GroupLayout.PREFERRED_SIZE,
                                        30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(username, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE,
                                        30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(password, GroupLayout.PREFERRED_SIZE,
                                        30, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(upperButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lowerButton)
                                .addContainerGap(60, Short.MAX_VALUE))
        );
    }

}
