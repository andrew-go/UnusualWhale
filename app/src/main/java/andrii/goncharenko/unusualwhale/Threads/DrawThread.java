package andrii.goncharenko.unusualwhale.Threads;

import android.view.View;

import andrii.goncharenko.unusualwhale.Controllers.GameController;

/**
 * Created by Andrey on 17.03.2015.
 */
public class DrawThread extends Thread {

    /** Members **/

    View view;
    boolean run = true;
    int sleepDuration = 100;

    /** Constructors **/

    public DrawThread(View view) {
        this.view = view;
    }

    public DrawThread(View view, int sleepDuration) {
        this.view = view;
        this.sleepDuration = sleepDuration;
    }

    /** Public Methods **/

    public void stopThread() {
        run = false;
    }

    public void run() {
        while(run) {
            if (GameController.Instance().gameStatus == GameStatusThread.eGameStatus.pause)
                continue;
            try {
                sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            view.postInvalidate();
        }
    }
}
