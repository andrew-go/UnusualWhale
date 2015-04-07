package andrii.goncharenko.unusualwhale.Models;

/**
 * Created by Andrey on 17.03.2015.
 */
public class WaterDrop {

    public WaterDrop(int xPosition, int speed) {
        this.xPosition = xPosition;
        this.yPosition = 0;
        this.speed = speed;
    }

    int xPosition;

    public int getXPosition() {
        return xPosition;
    }

    int yPosition;

    public int getYPosition() {
        return yPosition;
    }

    int speed;

    public void lower () {
        yPosition += speed;
    }

}
