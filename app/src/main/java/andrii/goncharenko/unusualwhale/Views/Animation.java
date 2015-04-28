package andrii.goncharenko.unusualwhale.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 17.03.2015.
 */
public class Animation {

    private Context context;

    private Drawable image;

    private List<Drawable> images = new ArrayList<Drawable>();

    private int currentFrame = 0;

    private int arrayIndex;

    private int oneImageRepeatCount;
    private int repeatCounter;

    public Animation(Context context, int arrayIndex, int oneImageRepeatCount) {
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
        arr.recycle();
    }

    public void draw(Canvas canvas, int x, int y) {
        if (currentFrame == images.size())
            currentFrame = 0;
        image = images.get(currentFrame);
        image.setBounds(x, y, x + image.getMinimumWidth(), y + image.getMinimumHeight());

        image.draw(canvas);
        repeatCounter--;
        if (repeatCounter == 0) {
            currentFrame++;
            repeatCounter = oneImageRepeatCount;
        }
    }



}
