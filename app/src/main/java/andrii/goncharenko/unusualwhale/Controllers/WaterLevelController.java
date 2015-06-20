package andrii.goncharenko.unusualwhale.Controllers;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.List;

import andrii.goncharenko.unusualwhale.Settings.DeviceSettings;
import andrii.goncharenko.unusualwhale.Threads.GameStatusThread;

/**
 * Created by Andrey on 14.04.2015.
 */
public class WaterLevelController {

    private final int WATER_LEVEL_1 = 100;
    private final int WATER_LEVEL_2 = 200;
    private final int WATER_LEVEL_3 = 300;
    private final int WATER_LEVEL_4 = 400;
    private final int WATER_LEVEL_5 = 500;
    private final int WATER_LEVEL_6 = 600;
    private final int WATER_LEVEL_7 = 700;

    private static WaterLevelController instance;

    public static WaterLevelController Instance() {
        return instance == null ? instance = new WaterLevelController() : instance;
    }

    private int threadSlowerIndex = 5;

    public int waterLevel = WATER_LEVEL_6;

    public void drawWaterLevel(Canvas canvas, List<Drawable> waterLevelItems) {
        Drawable image = waterLevelItems.get(0);
        if (waterLevel < WATER_LEVEL_1) {
            image = waterLevelItems.get(0);
        }
        else if (waterLevel >= WATER_LEVEL_1 && waterLevel < WATER_LEVEL_2) {
            image = waterLevelItems.get(1);
        }
        else if (waterLevel >= WATER_LEVEL_2 && waterLevel < WATER_LEVEL_3) {
            image = waterLevelItems.get(2);
        }
        else if (waterLevel >= WATER_LEVEL_3 && waterLevel < WATER_LEVEL_4) {
            image = waterLevelItems.get(3);
        }
        else if (waterLevel >= WATER_LEVEL_4 && waterLevel < WATER_LEVEL_5) {
            image = waterLevelItems.get(4);
        }
        else if (waterLevel >= WATER_LEVEL_5 && waterLevel < WATER_LEVEL_6) {
            image = waterLevelItems.get(5);
        }
        else if (waterLevel >= WATER_LEVEL_6 && waterLevel < WATER_LEVEL_7) {
            image = waterLevelItems.get(6);
        }
        else if (waterLevel >= WATER_LEVEL_7) {
            image = waterLevelItems.get(7);
        }
        image.setBounds(
                (DeviceSettings.width / 2) - (image.getMinimumWidth() / 2),
                20,
                (DeviceSettings.width / 2 + image.getMinimumWidth()) - (image.getMinimumWidth() / 2),
                20 + image.getMinimumHeight());
        image.draw(canvas);
    }

    public void lowerWaterLevel() {
        threadSlowerIndex --;
        if (threadSlowerIndex == 0) {
            waterLevel--;
            if (waterLevel == 0)
                GameController.Instance().gameStatus = GameStatusThread.eGameStatus.gameOver;
            threadSlowerIndex = 5;
        }
    }

    public void clear() {
        instance = null;
    }

}
