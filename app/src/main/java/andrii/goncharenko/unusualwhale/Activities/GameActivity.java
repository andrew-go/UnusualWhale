package andrii.goncharenko.unusualwhale.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import andrii.goncharenko.unusualwhale.Controllers.GameController;
import andrii.goncharenko.unusualwhale.Dialogs.GameSettingsDialog;
import andrii.goncharenko.unusualwhale.Dialogs.MenuSettingsDialog;
import andrii.goncharenko.unusualwhale.R;
import andrii.goncharenko.unusualwhale.Settings.GameSettings;
import andrii.goncharenko.unusualwhale.Threads.GameStatusThread;
import andrii.goncharenko.unusualwhale.Views.GameView;


public class GameActivity extends BaseActivity {

    ImageView ivScreenBlur;
    ImageView ivMenu;
    ImageView btRestart;
    ImageView btHome;
    ImageView btSettings;
    ImageView ivSettings;
    ImageView btSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    public void initComponents() {
        GameController.Instance().activity = this;
        GameController.Instance().view = (GameView) findViewById(R.id.gameView);
        GameController.Instance().initTouchListener();
        GameController.Instance().startNewGame();

        ivScreenBlur = (ImageView) findViewById(R.id.ivScreenBlur);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        btRestart = (ImageView) findViewById(R.id.btRestart);
        btHome = (ImageView) findViewById(R.id.btHome);
        btSettings = (ImageView) findViewById(R.id.btSettings);

        backgroundMusic = MediaPlayer.create(this, R.raw.music_2);
        backgroundMusic.setLooping(true);
        initTextView();
    }

    public void btGameMenu(View view) {
        GameController.Instance().gameStatus = GameStatusThread.eGameStatus.pause;
        GameSettingsDialog gameSettingsDialog = new GameSettingsDialog();
        gameSettingsDialog.show(getFragmentManager(), "gameSettingsDialog");
    }

    public void btRestart(View view) {
        GameController.Instance().gameOver();
        GameController.Instance().startNewGame();
        closeGameSettingsDialog();
        onResume();
    }

    public void btHome(View view) {
        GameController.Instance().gameOver();
        Intent intent = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(intent);
    }

    public void btSettingsMenu(View view) {
        MenuSettingsDialog menuSettingsDialog = new MenuSettingsDialog();
        menuSettingsDialog.show(getFragmentManager(), "menuSettingsDialog");
    }

    public void btSoundClick(View view) {
        GameSettings.Instance().isMusicOn = !GameSettings.Instance().isMusicOn;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("music", GameSettings.Instance().isMusicOn).commit();
        view.setBackground(GameSettings.Instance().isMusicOn
                ? getDrawable(R.drawable.sound_icon_on)
                : getDrawable(R.drawable.sound_icon_off));
        if (GameSettings.Instance().isMusicOn)
            startMusic();
        else
            stopMusic();
    }

    public void initTextView() {
        TextView textView = (TextView) findViewById(R.id.tvScore);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "agency_fb.ttf");
        textView.setTextColor(Color.parseColor("#FFFFCC"));
        textView.setTypeface(face, Typeface.BOLD);
        textView.setTextSize(30);
        textView.setShadowLayer(10, 7, 7, Color.BLACK);
        textView.setWidth(250);
    }

    private void closeGameSettingsDialog() {
        ((GameSettingsDialog)getFragmentManager().findFragmentByTag("gameSettingsDialog")).dismiss();
    }

}
