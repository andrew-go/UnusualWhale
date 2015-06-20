package andrii.goncharenko.unusualwhale.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import andrii.goncharenko.unusualwhale.R;
import andrii.goncharenko.unusualwhale.Settings.GameSettings;


public class MenuActivity extends BaseActivity {

    ImageView ivScreenBlur;
    ImageView ivSettings;
    ImageView btSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initComponents();
    }

    @Override
    public void initComponents() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        GameSettings.Instance().isMusicOn = prefs.getBoolean("music", false);
        backgroundMusic = MediaPlayer.create(this, R.raw.music_1);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void btNewGameClick(View view) {
        Intent intent = new Intent(getBaseContext(), GameActivity.class);
        startActivity(intent);
    }

    public void btSettingsClick(View view) {
        ivScreenBlur = (ImageView) findViewById(R.id.ivScreenBlur);
        ivSettings = (ImageView) findViewById(R.id.ivSettings);

        btSound = (ImageView) findViewById(R.id.btSound);
        btSound.setBackground(GameSettings.Instance().isMusicOn
                ? getDrawable(R.drawable.sound_icon_on)
                : getDrawable(R.drawable.sound_icon_off));
        ivScreenBlur.setVisibility(View.VISIBLE);
        ivSettings.setVisibility(View.VISIBLE);
        btSound.setVisibility(View.VISIBLE);
    }

    public void btAboutClick(View view) {

    }

    public void btSoundClick(View view) {
        GameSettings.Instance().isMusicOn = !GameSettings.Instance().isMusicOn;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("music", GameSettings.Instance().isMusicOn).commit();
        btSound.setBackground(GameSettings.Instance().isMusicOn
                ? getDrawable(R.drawable.sound_icon_on)
                : getDrawable(R.drawable.sound_icon_off));
        if (GameSettings.Instance().isMusicOn)
            startMusic();
        else
            stopMusic();
    }

    public void closeSettingsMenu(View view) {
        ivScreenBlur.setVisibility(View.GONE);
        ivSettings.setVisibility(View.GONE);
        btSound.setVisibility(View.GONE);
    }

}
