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
        ivScreenBlur.setVisibility(View.VISIBLE);
        ivMenu.setVisibility(View.VISIBLE);
        btRestart.setVisibility(View.VISIBLE);
        btHome.setVisibility(View.VISIBLE);
        btSettings.setVisibility(View.VISIBLE);
    }

    public void btRestart(View view) {
        GameController.Instance().gameOver();
        GameController.Instance().startNewGame();
        ivScreenBlur.setVisibility(View.GONE);
        ivMenu.setVisibility(View.GONE);
        btRestart.setVisibility(View.GONE);
        btHome.setVisibility(View.GONE);
        btSettings.setVisibility(View.GONE);
    }

    public void btHome(View view) {
        GameController.Instance().gameOver();
        Intent intent = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(intent);
    }

    public void btSettings(View view) {
        ivSettings = (ImageView) findViewById(R.id.ivSettings);
        btSound = (ImageView) findViewById(R.id.btSound);
        btSound.setBackground(GameSettings.Instance().isMusicOn
                ? getDrawable(R.drawable.sound_icon_on)
                : getDrawable(R.drawable.sound_icon_off));
        ivSettings.setVisibility(View.VISIBLE);
        btSound.setVisibility(View.VISIBLE);
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

    public void closeMenu(View view) {
        if (ivSettings != null && ivSettings.getVisibility() == View.VISIBLE) {
            ivSettings.setVisibility(View.GONE);
            btSound.setVisibility(View.GONE);
        }
        else {
            ivScreenBlur.setVisibility(View.GONE);
            ivMenu.setVisibility(View.GONE);
            btRestart.setVisibility(View.GONE);
            btHome.setVisibility(View.GONE);
            btSettings.setVisibility(View.GONE);
            GameController.Instance().gameStatus = GameStatusThread.eGameStatus.noAction;
        }

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

}
