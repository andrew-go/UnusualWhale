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
        for (int i = 0; i < 5; i++)
            if (random.nextInt(1000) == 1)
                junkItems.add(new Junk(random.nextInt(DeviceSettings.width), 10)) ;
    }

    public void lowerJunkItems() {
        for (Junk junk : junkItems)
            junk.lower();
    }

    public void deleteUnderJunkItems() {
        synchronized (junkItems) {
            for (Iterator<Junk> item = junkItems.listIterator(); item.hasNext(); ) {
                Junk junk = item.next();
                if (junk.getYPosition() > DeviceSettings.height)
                    item.remove();
                else if (junk.getXPosition() + 100 > WhaleController.Instance().getXPosition()
                        && junk.getXPosition() + 100 < (WhaleController.Instance().getXPosition() + 228)
                        && junk.getYPosition() + 50 > WhaleController.Instance().getYPosition()
                        && junk.getYPosition() + 50 < (WhaleController.Instance().getYPosition() + 194)) {
                    GameController.Instance().gameStatus = GameStatusThread.eGameStatus.gameOver;
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

    public void clear() {
        instance = null;
    }

}
