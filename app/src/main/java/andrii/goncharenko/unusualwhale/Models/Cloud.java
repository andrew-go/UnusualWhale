package andrii.goncharenko.unusualwhale.Models;

/**
 * Created by Andrey on 18.03.2015.
 */
public class Cloud extends BaseObject {

    public int imageIndex;

    public Cloud(int xPosition, int imageIndex) {
        this.xPosition = xPosition;
        this.yPosition = 0;
        this.imageIndex = imageIndex;
    }

    @Override
    public void lower() {
        yPosition += ((imageIndex + 1) * 2);
    }
}
