package andrii.goncharenko.unusualwhale.Models;

/**
 * Created by Andrey on 17.03.2015.
 */
public class WaterDrop extends BaseObject{

    public WaterDrop(int xPosition, int speed) {
        this.xPosition = xPosition;
        this.yPosition = 0;
        this.speed = speed;
    }

    @Override
    public void lower () {
        yPosition += speed;
    }

}
