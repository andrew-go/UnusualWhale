package andrii.goncharenko.unusualwhale.Controllers;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import andrii.goncharenko.unusualwhale.Models.Cloud;
import andrii.goncharenko.unusualwhale.Settings.DeviceSettings;

/**
 * Created by Andrey on 18.03.2015.
 */
public class CloudController {

    private static CloudController instance;

    public static CloudController Instance() {
        return instance == null ? instance = new CloudController() : instance;
    }

    Random random = new Random();

    public List<Cloud> clouds = Collections.synchronizedList(new ArrayList<Cloud>());

    public void createClouds() {
        for (int i = 0; i < 5; i++)
            if (random.nextInt(250) == 1)
                clouds.add(new Cloud(random.nextInt(DeviceSettings.width), random.nextInt(4))) ;
    }

    public void lowerClouds() {
        for (Cloud cloud : clouds)
            cloud.lower();
    }

    public void deleteUnderScreenClouds() {
        synchronized (clouds) {
            for (Iterator<Cloud> item = clouds.listIterator(); item.hasNext(); ) {
                Cloud cloud = item.next();
                if (cloud.getYPosition() > DeviceSettings.height)
                    item.remove();
            }
        }
    }



    public void drawClouds(Canvas canvas, List<Drawable> cloudImages) {
        synchronized (clouds) {
            for (Iterator<Cloud> item = clouds.listIterator(); item.hasNext(); ) {
                Cloud cloud = item.next();
                cloudImages.get(cloud.imageIndex).setBounds(
                        cloud.getXPosition(),
                        cloud.getYPosition(),
                        cloud.getXPosition() + cloudImages.get(cloud.imageIndex).getMinimumWidth(),
                        cloud.getYPosition() + cloudImages.get(cloud.imageIndex).getMinimumHeight());
                cloudImages.get(cloud.imageIndex).draw(canvas);
            }
        }
    }

    public void clear() {
        instance = null;
    }

}
