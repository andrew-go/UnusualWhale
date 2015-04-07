package andrii.goncharenko.unusualwhale.Controllers;

import android.view.MotionEvent;

import andrii.goncharenko.unusualwhale.Settings.DeviceSettings;
import andrii.goncharenko.unusualwhale.Threads.GameStatusThread;

/**
 * Created by Andrey on 11.03.2015.
 */
public class WhaleController {

    private static WhaleController instance;

    public static WhaleController Instance() {
        return instance == null ? instance = new WhaleController() : instance;
    }

    public enum eStatus
    {
        noMove,
        movingLeft,
        movingRight,
    };

    public eStatus moveWay = eStatus.noMove;

    int xPosition = (DeviceSettings.width / 2);

    public int getXPosition() {
        return xPosition;
    }

    int yPosition = (DeviceSettings.height / 2);

    public int getYPosition() {
        return yPosition;
    }

    public void stop() {
        moveWay = eStatus.noMove;
    }

    public void setMoveWay(MotionEvent event) {
        moveWay = event.getX() >= (DeviceSettings.width / 2) ? eStatus.movingRight : eStatus.movingLeft;
    }

    public void move() {
        switch(moveWay) {
            case movingLeft:
                if (xPosition >= 20)
                    xPosition = xPosition - 20;
            break;
            case movingRight:
                if (xPosition <= (DeviceSettings.width - 236))
                    xPosition = xPosition + 20;
            break;
        }
    }

    public void lowerWhale() {
        yPosition += 1;
    }

    public void checkWhaleHeight(){
        if (yPosition > DeviceSettings.height)
            GameController.Instance().gameStatus = GameStatusThread.eGameStatus.gameOver;
    }

    public void clear() {
        instance = null;
    }

}
