package andrii.goncharenko.unusualwhale.Controllers;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import andrii.goncharenko.unusualwhale.Activities.GameActivity;
import andrii.goncharenko.unusualwhale.R;
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

    private int threadSlowerIndex = 50;

    public int score = 0;

    public GameActivity activity;

    public GameController() {

    }

    public void initThreads() {
        initDrawThread();
        initGameStatusThread();
    }

    private void initDrawThread() {
        drawThread = new DrawThread(view);
        drawThread.start();
    }

    private void initGameStatusThread() {
        gameStatusThread = new GameStatusThread();
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
        WaterLevelController.Instance().clear();
    }

    public void startNewGame() {
        gameStatus = GameStatusThread.eGameStatus.noAction;
        initThreads();
    }

    final Handler mHandler = new Handler();

    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            riseScore();
        }
    };

    public void riseScore() {
        threadSlowerIndex--;
        if (threadSlowerIndex == 0) {
            score++;
            TextView tvScore = (TextView) activity.findViewById(R.id.tvScore);
            tvScore.setText(String.valueOf(score));
            threadSlowerIndex = 50;
        }
    }

    public void riseScoreOperation() {

        Thread t = new Thread() {
            public void run() {
                mHandler.post(mUpdateResults);
            }
        };
        t.start();
    }

    public void damageOperation() {

        Thread thread = new Thread() {
            public void run() {
                try {
                    mHandler.post(mShowDamageScreen);
                    sleep(200);
                    mHandler.post(mHideDamageScreen);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    final Runnable mShowDamageScreen = new Runnable() {
        public void run() {
            ImageView ivDamageScreen = (ImageView) activity.findViewById(R.id.ivDamageScreen);
            ivDamageScreen.setVisibility(View.VISIBLE);
        }
    };

    final Runnable mHideDamageScreen = new Runnable() {
        public void run() {
            ImageView ivDamageScreen = (ImageView) activity.findViewById(R.id.ivDamageScreen);
            ivDamageScreen.setVisibility(View.GONE);
        }
    };

}
