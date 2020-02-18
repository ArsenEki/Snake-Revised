package gui;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.AvoidDuplicateLiterals",
        "PMD.DataflowAnomalyAnalysis", "PMD.MissingSerialVersionUID",
        "PMD.AssignmentToNonFinalStatic", "PMD.AvoidLiteralsInIfCondition", "PMD.NullAssignment"})
public class GameClock extends Thread {

    private static GameClock clock;

    private GameClock(Runnable run) {
        super(run);
    }

    /**
     * This method will create an instance of the game clokc if on doesn't already exist.
     * @param run component to run from the new thread
     * @return the game clock object or null if one already exists.
     */
    public static GameClock getInstance(Runnable run) {
        if (clock == null) {
            clock = new GameClock(run);
        }
        return clock;
    }

    /**
     * Thus method will reset the instance of the game clock.
     * @return null
     */
    public static  GameClock resetInstance() {
        clock = null;
        return clock;
    }
}


//package gui;
//
//import board.Location;
//import board.level.Level;
//
//import java.util.TimerTask;
//
//@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.NullAssignment"})
//public class GameClock extends TimerTask {
//
//    private static GameClock clock = null;
//
//    protected Level gameLevel;
//
//    private GameClock(Level level) {
//        super();
//        gameLevel = level;
//    }
//
//    /**
//     * Thius method will make sure onyl one instance of the Game clock exists.
//     * @param level the level to be run on.
//     * @return the instance of the game clock
//     */
//    public static GameClock getInstance(Level level) {
//        if (clock == null) {
//            clock = new GameClock(level);
//        }
//        return clock;
//    }
//
//    /**
//     * Thus method will reset the instance of the game clock.
//     * @return null
//     */
//    public static GameClock resetInstance() {
//        clock = null;
//        return clock;
//    }
//
//    @Override
//    public void run() {
//        // This order is way better for reaction time!!!!
//        gameLevel.update();
//        Location newTail = gameLevel.getBody().getLast();
//
//    }
//}
