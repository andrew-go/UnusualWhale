package andrii.goncharenko.unusualwhale.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import andrii.goncharenko.unusualwhale.Dialogs.MenuSettingsDialog;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
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
        MenuSettingsDialog menuSettingsDialog = new MenuSettingsDialog();
        menuSettingsDialog.show(getFragmentManager(), "menuSettingsDialog");
    }

    public void btAboutClick(View view) {

    }

    public void btSoundClick(View view) {
        changeMusicState(view);
    }
}
