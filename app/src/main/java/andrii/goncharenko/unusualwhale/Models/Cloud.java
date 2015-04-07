package andrii.goncharenko.unusualwhale.Models;

/**
 * Created by Andrey on 18.03.2015.
 */
public class Cloud {

    public int imageIndex;

    public Cloud(int xPosition, int imageIndex) {
        this.xPosition = xPosition;
        this.yPosition = 0;
        this.imageIndex = imageIndex;
    }

    int xPosition;

    public int getXPosition() {
        return xPosition;
    }

    int yPosition;

    public int getYPosition() {
        return yPosition;
    }

    public void lower () {
        yPosition += ((imageIndex + 1) * 2);
    }

}
