package andrii.goncharenko.unusualwhale.Activities;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;

import java.io.IOException;

import andrii.goncharenko.unusualwhale.R;
import andrii.goncharenko.unusualwhale.Settings.GameSettings;

public class BaseActivity  extends FragmentActivity {

    protected MediaPlayer backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        startMusic();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopMusic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundMusic.reset();
    }

    public void initComponents() {

    }

    public void changeMusicState() {
        GameSettings.Instance().isMusicOn = !GameSettings.Instance().isMusicOn;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("music", GameSettings.Instance().isMusicOn).commit();
    }

    public void startMusic() {
        if (backgroundMusic == null || backgroundMusic.isPlaying() || !GameSettings.Instance().isMusicOn)
            return;
        backgroundMusic.start();
    }

    public void stopMusic() {
        if (backgroundMusic == null || !backgroundMusic.isPlaying() || GameSettings.Instance().isMusicOn)
            return;
        backgroundMusic.pause();
    }

}
