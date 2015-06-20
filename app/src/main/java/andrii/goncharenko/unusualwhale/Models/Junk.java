package andrii.goncharenko.unusualwhale.Models;

/**
 * Created by Andrey on 19.03.2015.
 */
public class Junk extends BaseObject {

    int speed = 10;

    public Junk(int xPosition) {
        this.xPosition = xPosition;
        this.yPosition = 0;
    }

    @Override
    public void lower() {
        yPosition += speed;
    }

}
