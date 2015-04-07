package andrii.goncharenko.unusualwhale.Activities;

import android.os.Bundle;

import andrii.goncharenko.unusualwhale.Controllers.GameController;
import andrii.goncharenko.unusualwhale.R;
import andrii.goncharenko.unusualwhale.Views.GameView;


public class GameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        GameController.Instance().view = (GameView) findViewById(R.id.gameView);
        GameController.Instance().initTouchListener();
        GameController.Instance().startNewGame();
    }

}
