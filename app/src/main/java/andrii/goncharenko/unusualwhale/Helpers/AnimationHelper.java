package andrii.goncharenko.unusualwhale.Helpers;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 17.03.2015.
 */
public class AnimationHelper {

    private final int START_IMAGE_INDEX = 0;

    private Context context;

    private Drawable image;

    private List<Drawable> images = new ArrayList<Drawable>();

    private int currentFrame = START_IMAGE_INDEX;

    private int arrayIndex;

    private int oneImageRepeatCount;
    private int repeatCounter;

    private int height;
    private int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public AnimationHelper(Context context, int arrayIndex, int oneImageRepeatCount) {
        this.context = context;
        this.arrayIndex = arrayIndex;
        this.oneImageRepeatCount = oneImageRepeatCount;
        repeatCounter = oneImageRepeatCount;
        initImages();
    }

    private void initImages() {
        TypedArray arr = context.getResources().obtainTypedArray(arrayIndex);
        for (int i = 0; i < arr.length(); i++)
            images.add(context.getResources().getDrawable(arr.getResourceId(i, -1)));
        if (images.size() > 0)
            setAnimationSize();
        arr.recycle();
    }

    public void draw(Canvas canvas, int x, int y) {
        if (currentFrame == images.size())
            currentFrame = START_IMAGE_INDEX;
        image = images.get(currentFrame);
        image.setBounds(x, y, x + image.getMinimumWidth(), y + image.getMinimumHeight());

        image.draw(canvas);
        repeatCounter--;
        if (repeatCounter == 0) {
            currentFrame++;
            repeatCounter = oneImageRepeatCount;
        }
    }

    private void setAnimationSize() {
        width = images.get(START_IMAGE_INDEX).getMinimumWidth();
        height = images.get(START_IMAGE_INDEX).getMinimumHeight();
    }

}
