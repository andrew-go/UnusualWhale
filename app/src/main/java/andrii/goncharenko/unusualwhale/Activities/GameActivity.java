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

    ImageView btSettings;

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

        btSettings = (ImageView) findViewById(R.id.btSettings);

        backgroundMusic = MediaPlayer.create(this, R.raw.music_2);
        backgroundMusic.setLooping(true);
        initTextView();
    }

    public void btGameSettingsClick(View view) {
        GameController.Instance().gameStatus = GameStatusThread.eGameStatus.pause;
        GameSettingsDialog gameSettingsDialog = new GameSettingsDialog();
        gameSettingsDialog.show(getFragmentManager(), "gameSettingsDialog");
    }

    public void btRestartClick(View view) {
        GameController.Instance().gameOver();
        GameController.Instance().startNewGame();
        closeGameSettingsDialog();
        onResume();
    }

    public void btHomeClick(View view) {
        GameController.Instance().gameOver();
        Intent intent = new Intent(getBaseContext(), MenuActivity.class);
        startActivity(intent);
    }

    public void btMenuSettingsClick(View view) {
        MenuSettingsDialog menuSettingsDialog = new MenuSettingsDialog();
        menuSettingsDialog.show(getFragmentManager(), "menuSettingsDialog");
    }

    public void btSoundClick(View view) {
        changeMusicState(view);
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
