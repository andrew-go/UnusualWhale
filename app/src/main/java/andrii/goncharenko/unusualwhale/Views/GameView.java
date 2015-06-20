package andrii.goncharenko.unusualwhale.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import andrii.goncharenko.unusualwhale.Controllers.CloudController;
import andrii.goncharenko.unusualwhale.Controllers.GameController;
import andrii.goncharenko.unusualwhale.Controllers.JunkController;
import andrii.goncharenko.unusualwhale.Controllers.WaterDropController;
import andrii.goncharenko.unusualwhale.Controllers.WaterLevelController;
import andrii.goncharenko.unusualwhale.Controllers.WhaleController;
import andrii.goncharenko.unusualwhale.R;
import andrii.goncharenko.unusualwhale.Settings.DeviceSettings;

/**
 * Created by Andrey on 17.03.2015.
 */
public class GameView extends BaseView {

    private Drawable backgroundImage;
    private Drawable waterDropImage;
    private Drawable gameOverImage;
    private Drawable junkImage;
    private Drawable lifeImage;
    private Drawable emptyLifeImage;
    private List<Drawable> cloudImages;
    private Animation whaleAnimation;
    private Animation waterPillarAnimation;
    private List<Drawable> waterLevelImages;

    public GameView(Context context) {
        super(context);
        initImages();
        initAnimations();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initImages();
        initAnimations();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initImages();
        initAnimations();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch(GameController.Instance().gameStatus) {
            case gameOver:
                drawBackGround(canvas);
                drawGameOver(canvas);
            return;
            default:
                drawBackGround(canvas);
                drawClouds(canvas);
                drawWaterDrops(canvas);
                drawWaterPillarAnimation(canvas);
                drawWhaleAnimation(canvas);
                drawJunkItems(canvas);
                drawLifeHearts(canvas);
                drawWaterLevel(canvas);
        }

    }

    private void drawBackGround(Canvas canvas) {
        backgroundImage.draw(canvas);
    }

    private void drawWaterPillarAnimation(Canvas canvas) {
        waterPillarAnimation.draw(
                canvas,
                WhaleController.Instance().getXPosition() + (225/2) + 26,
                WhaleController.Instance().getYPosition() + 122);
    }

    private void drawWhaleAnimation(Canvas canvas) {
        whaleAnimation.draw(
                canvas,
                WhaleController.Instance().getXPosition(),
                WhaleController.Instance().getYPosition());
    }

    private void drawWaterDrops(Canvas canvas) { //ArrayList in usage
        WaterDropController.Instance().drawDrops(canvas, waterDropImage);
    }

    private void drawClouds(Canvas canvas) { //ArrayList in usage
        CloudController.Instance().drawClouds(canvas, cloudImages);
    }

    private void drawJunkItems(Canvas canvas) { //ArrayList in usage
        JunkController.Instance().drawJunkItems(canvas, junkImage);
    }

    private void drawLifeHearts(Canvas canvas) {
        WhaleController.Instance().drawLifeHearts(canvas, lifeImage, emptyLifeImage);
    }

    private void drawWaterLevel(Canvas canvas) {
        WaterLevelController.Instance().drawWaterLevel(canvas, waterLevelImages);
    }

    private void drawGameOver(Canvas canvas) {
        gameOverImage.draw(canvas);
    }

    private void initImages() {
        if (backgroundImage == null) {
            backgroundImage = context.getResources().getDrawable(R.drawable.game_background);
            backgroundImage.setBounds(0, 0, DeviceSettings.width, DeviceSettings.height);
        }
        if (waterDropImage == null) {
            waterDropImage = context.getResources().getDrawable(R.drawable.water_drop);
        }
        if (cloudImages == null) {
            TypedArray arr = context.getResources().obtainTypedArray(R.array.clouds);
            cloudImages = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++)
                cloudImages.add(context.getResources().getDrawable(arr.getResourceId(i, -1)));
            arr.recycle();
        }
        if (gameOverImage == null) {
            gameOverImage = context.getResources().getDrawable(R.drawable.game_over_text);
            gameOverImage.setBounds(0, 0, DeviceSettings.width, DeviceSettings.height);
        }
        if (junkImage == null) {
            junkImage = context.getResources().getDrawable(R.drawable.junk_0);
        }
        if (lifeImage == null) {
            lifeImage = context.getResources().getDrawable(R.drawable.life);
        }
        if (emptyLifeImage == null) {
            emptyLifeImage = context.getResources().getDrawable(R.drawable.empty_life);
        }
        if (waterLevelImages == null) {
            TypedArray arr = context.getResources().obtainTypedArray(R.array.water_level);
            waterLevelImages = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++)
                waterLevelImages.add(context.getResources().getDrawable(arr.getResourceId(i, -1)));
            arr.recycle();
        }
    }

    private void initAnimations() {
        if (whaleAnimation == null) {
            whaleAnimation = new Animation(context, R.array.whale_animations, 0);
        }
        if (waterPillarAnimation == null) {
            waterPillarAnimation = new Animation(context, R.array.water_pillar_animations, 5);
        }
    }



}
