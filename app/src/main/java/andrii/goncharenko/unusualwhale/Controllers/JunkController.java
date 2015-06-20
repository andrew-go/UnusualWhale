package andrii.goncharenko.unusualwhale.Controllers;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import andrii.goncharenko.unusualwhale.Models.Junk;
import andrii.goncharenko.unusualwhale.Settings.DeviceSettings;
import andrii.goncharenko.unusualwhale.Threads.GameStatusThread;

/**
 * Created by Andrey on 19.03.2015.
 */
public class JunkController {

    private static JunkController instance;

    public static JunkController Instance() {
        return instance == null ? instance = new JunkController() : instance;
    }

    Random random = new Random();

    public List<Junk> junkItems = Collections.synchronizedList(new ArrayList<Junk>());

    public void createJunkItems() {
        if (isNewJunkChanceMatched())
            junkItems.add(new Junk(getRandomHorizontalPosition())) ;
    }

    public void lowerJunkItems() {
        for (Junk junk : junkItems)
            junk.lower();
    }

    private int getCenterHorizontalPoint(Junk junk) {
        return junk.getXPosition() + GameController.Instance().view.getJunkImage().getMinimumWidth()/2;
    }

    private int getCenterVerticalPoint(Junk junk) {
        return junk.getYPosition() + GameController.Instance().view.getJunkImage().getMinimumHeight()/2;
    }

    public void deleteUnderJunkItems() {
        synchronized (junkItems) {
            for (Iterator<Junk> item = junkItems.listIterator(); item.hasNext(); ) {
                Junk junk = item.next();
                if (junk.getYPosition() > DeviceSettings.height)
                    item.remove();
                else if (getCenterHorizontalPoint(junk) > WhaleController.Instance().getXPosition()
                        && getCenterHorizontalPoint(junk) < (WhaleController.Instance().getXPosition() + GameController.Instance().view.getWhaleImage().getMinimumWidth())
                        && getCenterVerticalPoint(junk) > WhaleController.Instance().getYPosition()
                        && getCenterVerticalPoint(junk) < (WhaleController.Instance().getYPosition() + GameController.Instance().view.getWhaleImage().getMinimumHeight())) {
                    item.remove();
                    GameController.Instance().damageOperation();
                    if (--WhaleController.Instance().heartsCount == 0) {
                        GameController.Instance().gameStatus = GameStatusThread.eGameStatus.gameOver;
                    }
                }
            }
        }
    }

    public void drawJunkItems(Canvas canvas, Drawable junkImage) {
        synchronized (junkItems) {
            for (Iterator<Junk> item = junkItems.listIterator(); item.hasNext(); ) {
                Junk junk = item.next();
                junkImage.setBounds(
                        junk.getXPosition(),
                        junk.getYPosition(),
                        junk.getXPosition() + junkImage.getMinimumWidth(),
                        junk.getYPosition() + junkImage.getMinimumHeight());
                junkImage.draw(canvas);
            }
        }
    }

    private boolean isNewJunkChanceMatched() {
        return random.nextInt(200) == 1;
    }

    private int getRandomHorizontalPosition() {
        return random.nextInt(DeviceSettings.width);
    }

    public void clear() {
        instance = null;
    }

}
