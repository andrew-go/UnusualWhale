package andrii.goncharenko.unusualwhale.Settings;

/**
 * Created by Andrey on 11.03.2015.
 */
public class GameSettings {

    private static GameSettings instance;

    public static GameSettings Instance() {
        return instance == null ? instance = new GameSettings() : instance;
    }

    public boolean isMusicOn = false;
    public boolean isSoundsOn = false;

}
