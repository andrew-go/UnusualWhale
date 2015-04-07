package andrii.goncharenko.unusualwhale.Controllers;

import android.view.MotionEvent;
import android.view.View;

import andrii.goncharenko.unusualwhale.Threads.DrawThread;
import andrii.goncharenko.unusualwhale.Threads.GameStatusThread;
import andrii.goncharenko.unusualwhale.Views.GameView;

/**
 * Created by Andrey on 17.03.2015.
 */
public class GameController {

    private static GameController instance;

    public static GameController Instance() {
        return instance == null ? instance = new GameController() : instance;
    }

    public GameView view;

    public DrawThread drawThread;

    public GameStatusThread gameStatusThread;

    public GameStatusThread.eGameStatus gameStatus;

    public GameController() {

    }

    public void initThreads() {
        initDrawThread();
        initGameStatusThread();
    }

    private void initDrawThread() {
        drawThread = new DrawThread(view, 5);
        drawThread.start();
    }

    private void initGameStatusThread() {
        gameStatusThread = new GameStatusThread(10);
        gameStatusThread.start();
    }

    public void initTouchListener() {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        return actionDown(event);
                    case MotionEvent.ACTION_MOVE:
                        return actionMove(event);
                    case MotionEvent.ACTION_UP:
                        return actionUp(event);
                }
                return false;
            }
        });
    }

    private boolean actionDown(MotionEvent event) {
        switch(gameStatus) {
            case noAction:
                WhaleController.Instance().setMoveWay(event);
                gameStatus = GameStatusThread.eGameStatus.moving;
            return true;
            case gameOver:
                startNewGame();
            return true;
        }
        return true;
    }

    private boolean actionMove(MotionEvent event) {
        switch(gameStatus) {
            case moving:
                WhaleController.Instance().setMoveWay(event);
                return true;
        }
        return true;
    }

    private boolean actionUp(MotionEvent event) {
        switch(gameStatus) {
            case moving:
                WhaleController.Instance().stop();
                gameStatus = GameStatusThread.eGameStatus.noAction;
                return true;
        }
        return true;
    }

    public void gameOver() {
        gameStatusThread.stopThread();
        drawThread.stopThread();
        CloudController.Instance().clear();
        WaterDropController.Instance().clear();
        WhaleController.Instance().clear();
        JunkController.Instance().clear();
    }

    public void startNewGame() {
        gameStatus = GameStatusThread.eGameStatus.noAction;
        initThreads();
    }

}
