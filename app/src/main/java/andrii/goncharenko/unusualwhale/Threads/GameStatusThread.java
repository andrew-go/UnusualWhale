package andrii.goncharenko.unusualwhale.Threads;

import andrii.goncharenko.unusualwhale.Controllers.CloudController;
import andrii.goncharenko.unusualwhale.Controllers.GameController;
import andrii.goncharenko.unusualwhale.Controllers.JunkController;
import andrii.goncharenko.unusualwhale.Controllers.WaterDropController;
import andrii.goncharenko.unusualwhale.Controllers.WaterLevelController;
import andrii.goncharenko.unusualwhale.Controllers.WhaleController;

/**
 * Created by Andrey on 17.03.2015.
 */
public class GameStatusThread extends Thread {

    /**Enums**/
    public enum eGameStatus
    {
        noAction,
        moving,
        stopMoving,
        pause,
        gameOver
    };

    /**Members**/

    boolean run = true;

    int sleepDuration = 10;

    /**Constructors**/

    public GameStatusThread() {

    }

    /** Public Methods **/

    public void stopThread() {
        run = false;
    }

    public void run() {
        while(run) {
            try {
                switch (GameController.Instance().gameStatus) {
                    case pause:
                        break;
                    case noAction:
                        checkOnGameOver();
                        deleteGameObjects();
                        createGameObjects();
                        sleep(sleepDuration);
                        lowerGameObjects();
                        GameController.Instance().riseScoreOperation();
                        break;
                    case moving:
                        checkOnGameOver();
                        move();
                        deleteGameObjects();
                        createGameObjects();
                        sleep(sleepDuration);
                        lowerGameObjects();
                        GameController.Instance().riseScoreOperation();
                        break;
                    case gameOver:
                        gameOver();
                        break;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createGameObjects() {
        WaterDropController.Instance().createWaterDrops();
        CloudController.Instance().createClouds();
        JunkController.Instance().createJunkItems();
    }

    private void deleteGameObjects() {
        WaterDropController.Instance().deleteCollectedWaterDrops();
        CloudController.Instance().deleteUnderScreenClouds();
        JunkController.Instance().deleteUnderJunkItems();
    }

    private void lowerGameObjects() {
        WaterLevelController.Instance().lowerWaterLevel();
        WaterDropController.Instance().lowerWaterDrops();
        CloudController.Instance().lowerClouds();
        JunkController.Instance().lowerJunkItems();
    }

    private void move() {
        WhaleController.Instance().move();
    }

    private void checkOnGameOver() {
        WhaleController.Instance().checkWhaleHeight();
    }

    private void gameOver() {
        GameController.Instance().gameOver();
    }

}
