package gui;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic", "PMD.UseProperClassLoader"})
public class AudioHandling {
    private static Clip backgroundClip;
    private static Clip eatClip;
    private static Clip failClip;
    private static Clip turn1Clip;
    private static Clip turn2Clip;
    private static String backgroundAudioName;
    private static boolean muteAudio;
    private static boolean gameMusicPlaying = true;

    AudioHandling() {

    }

    public static void setBackgroundAudioName(String name) {
        backgroundAudioName = name;
    }

    public static void setMuteAudio(boolean mute) {
        muteAudio = mute;
    }

    /**
     * This method will set up the audio assets of the game.
     */
    public void setUpAudio() {
        try {
            URL url = this.getClass().getClassLoader().getResource(backgroundAudioName);
            AudioInputStream backAudioIn = AudioSystem.getAudioInputStream(url);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(backAudioIn);
            url = this.getClass().getClassLoader().getResource("fail.wav");
            AudioInputStream failAudioIn = AudioSystem.getAudioInputStream(url);
            failClip = AudioSystem.getClip();
            failClip.open(failAudioIn);
            url = this.getClass().getClassLoader().getResource("eat1.wav");
            AudioInputStream eatAudioIn = AudioSystem.getAudioInputStream(url);
            eatClip = AudioSystem.getClip();
            eatClip.open(eatAudioIn);
            url = this.getClass().getClassLoader().getResource("turn1.wav");
            AudioInputStream turn1AudioIn = AudioSystem.getAudioInputStream(url);
            turn1Clip = AudioSystem.getClip();
            turn1Clip.open(turn1AudioIn);
            url = this.getClass().getClassLoader().getResource("turn2.wav");
            AudioInputStream turn2AudioIn = AudioSystem.getAudioInputStream(url);
            turn2Clip = AudioSystem.getClip();
            turn2Clip.open(turn2AudioIn);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to play background music of pause it.
     * @param play /.
     */
    public static void pauseResumeBackground(boolean play) {
        if (play && !muteAudio) {
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            backgroundClip.stop();
        }
    }

    /**
     * Plays sound when eating apple.
     */
    public static void playEatSound() {
        if (eatClip == null || muteAudio) {
            return;
        }
        eatClip.setFramePosition(0);
        eatClip.start();
    }

    /**
     * Plays sound upon death.
     */
    public static void playFailSound() {
        if (failClip == null || muteAudio) {
            return;
        }
        failClip.setFramePosition(0);
        failClip.start();
    }

    /**
     * Plays a special sound for a north and south turn.
     */
    public static void playTurn1Sound() {
        if (turn1Clip == null || muteAudio) {
            return;
        }
        turn1Clip.setFramePosition(0);
        turn1Clip.start();
    }

    /**
     * Plays a special sound for a west and east turn.
     */
    public static void playTurn2Sound() {
        if (turn2Clip == null || muteAudio) {
            return;
        }
        turn2Clip.setFramePosition(0);
        turn2Clip.start();
    }

    public static boolean getGameMusicPlaying() {
        return gameMusicPlaying;
    }

    public static void setGameMusicPlaying(boolean shouldMusicPlay) {
        gameMusicPlaying = shouldMusicPlay;
    }
}
