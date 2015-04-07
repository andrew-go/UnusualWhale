package andrii.goncharenko.unusualwhale.Controllers;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import andrii.goncharenko.unusualwhale.Models.WaterDrop;
import andrii.goncharenko.unusualwhale.Settings.DeviceSettings;

/**
 * Created by Andrey on 17.03.2015.
 */
public class WaterDropController {

    private static WaterDropController instance;

    public static WaterDropController Instance() {
        return instance == null ? instance = new WaterDropController() : instance;
    }

    Random random = new Random();

    public List<WaterDrop> waterDrops = Collections.synchronizedList(new ArrayList<WaterDrop>());

    private Iterator<WaterDrop> iterator;

    public void createWaterDrops() {
        for (int i = 0; i < 5; i++)
            if (random.nextInt(100) == 1)
                waterDrops.add(new WaterDrop(random.nextInt(DeviceSettings.width), random.nextInt(10) + 10)) ;
    }

    public void lowerWaterDrops() {
        for (WaterDrop waterDrop : waterDrops)
            waterDrop.lower();
    }

    public void deleteCollectedWaterDrops() {
        synchronized (waterDrops) {
            for (Iterator<WaterDrop> item = waterDrops.listIterator(); item.hasNext(); ) {
                WaterDrop waterDrop = item.next();
                if (waterDrop.getYPosition() > DeviceSettings.height)
                    item.remove();
                else if (waterDrop.getXPosition() > WhaleController.Instance().getXPosition()
                        && waterDrop.getXPosition() < (WhaleController.Instance().getXPosition() + 228)
                        && waterDrop.getYPosition() > WhaleController.Instance().getYPosition()
                        && waterDrop.getYPosition() < (WhaleController.Instance().getYPosition() + 194)) {
                    WhaleController.Instance().yPosition -= 25;
                    item.remove();
                }
            }
        }
    }

    public void drawDrops(Canvas canvas, Drawable waterDropImage) {
        synchronized (waterDrops) {
            for (Iterator<WaterDrop> item = waterDrops.listIterator(); item.hasNext(); ) {
                WaterDrop waterDrop = item.next();
                waterDropImage.setBounds(
                        waterDrop.getXPosition(),
                        waterDrop.getYPosition(),
                        waterDrop.getXPosition() + waterDropImage.getMinimumWidth(),
                        waterDrop.getYPosition() + waterDropImage.getMinimumHeight());
                waterDropImage.draw(canvas);
            }
        }
    }

    public void clear() {
        instance = null;
    }

}
