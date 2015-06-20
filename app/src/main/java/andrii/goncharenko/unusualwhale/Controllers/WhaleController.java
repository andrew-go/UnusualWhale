package andrii.goncharenko.unusualwhale.Controllers;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import andrii.goncharenko.unusualwhale.Settings.DeviceSettings;
import andrii.goncharenko.unusualwhale.Threads.GameStatusThread;

/**
 * Created by Andrey on 11.03.2015.
 */
public class WhaleController {

    private final int WHALE_STEP = 20;

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

    public int heartsCount = 3;

    public eStatus moveWay = eStatus.noMove;

    int xPosition = (DeviceSettings.width / 2);

    public int getXPosition() {
        return xPosition;
    }

    int yPosition = (int)(DeviceSettings.height / 1.5);

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
                if (xPosition >= WHALE_STEP)
                    xPosition = xPosition - WHALE_STEP;
            break;
            case movingRight:
                if (xPosition <= (DeviceSettings.width - GameController.Instance().view.getWhaleImage().getMinimumWidth()))
                    xPosition = xPosition + WHALE_STEP;
            break;
        }
    }

    public void checkWhaleHeight(){
        if (yPosition > DeviceSettings.height)
            GameController.Instance().gameStatus = GameStatusThread.eGameStatus.gameOver;
    }

    public void clear() {
        instance = null;
    }

    public void drawWhale(Canvas canvas, Drawable whaleImage) {
        whaleImage.setBounds(xPosition, yPosition, xPosition + whaleImage.getMinimumWidth(), yPosition + whaleImage.getMinimumHeight());
        whaleImage.draw(canvas);
    }

    public void drawLifeHearts(Canvas canvas, Drawable lifeImage, Drawable emptyLifeImage) {
        Drawable image;
        for (int i = 0; i < 3; i++) {
            image = (heartsCount > i) ? lifeImage : emptyLifeImage;
            image.setBounds(
                    (i * (image.getMinimumWidth() + 10)),
                    20,
                    (i * (image.getMinimumWidth() + 10)) + image.getMinimumWidth(),
                    20 + image.getMinimumHeight());
            image.draw(canvas);
        }
    }

}
