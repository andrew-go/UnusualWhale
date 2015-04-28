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

    private static WaterLevelController instance;

    public static WaterLevelController Instance() {
        return instance == null ? instance = new WaterLevelController() : instance;
    }

    private int threadSlower = 5;

    public int waterLevel = 600;

    public void drawWaterLevel(Canvas canvas, List<Drawable> waterLevelItems) {
        Drawable image = waterLevelItems.get(0);
        if (waterLevel < 100) {
            image = waterLevelItems.get(0);
        }
        else if (waterLevel >= 100 && waterLevel < 200) {
            image = waterLevelItems.get(1);
        }
        else if (waterLevel >= 200 && waterLevel < 300) {
            image = waterLevelItems.get(2);
        }
        else if (waterLevel >= 300 && waterLevel < 400) {
            image = waterLevelItems.get(3);
        }
        else if (waterLevel >= 400 && waterLevel < 500) {
            image = waterLevelItems.get(4);
        }
        else if (waterLevel >= 500 && waterLevel < 600) {
            image = waterLevelItems.get(5);
        }
        else if (waterLevel >= 600 && waterLevel < 700) {
            image = waterLevelItems.get(6);
        }
        else if (waterLevel >= 700) {
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
        threadSlower --;
        if (threadSlower == 0) {
            waterLevel--;
            if (waterLevel == 0)
                GameController.Instance().gameStatus = GameStatusThread.eGameStatus.gameOver;
            threadSlower = 5;
        }
    }

    public void clear() {
        instance = null;
    }

}
