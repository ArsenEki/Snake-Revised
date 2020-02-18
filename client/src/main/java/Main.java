import authentication.dialogs.LoginScreen;
import authentication.dialogs.SplashScreen;

import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings({"PMD.DataflowAnomalyAnalysis"})
public class Main {

    /**
     * This is the main entry point to the client application. It initiates the GUI.
     *
     * @param args - arguments passed by the user
     */
    public static void main(String[] args) {

        // Set up a basic screen and the splashscreen.
        final JFrame frame = new JFrame("Sssnake");
        SplashScreen splashScreen = new SplashScreen();

        // Set up the splashscreen so it shows before the game starts up.
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                splashScreen.setVisible(true);
            }
        });

        splashScreen.setLocationRelativeTo(null);

        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(30);
                splashScreen.progressBar.setValue(i);
                splashScreen.percentage.setText(Integer.toString(i) + "%");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splashScreen.dispose();

        // Show a Login Screen
        LoginScreen loginScreen = new LoginScreen(frame, true);
        loginScreen.setLocationRelativeTo(null);
        loginScreen.setVisible(true);

    }
}