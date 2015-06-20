package andrii.goncharenko.unusualwhale.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import andrii.goncharenko.unusualwhale.R;

/**
 * Created by Y50-70 on 20.06.2015.
 */
public class GameSettingsDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_game_settings, null));
        return builder.create();
    }

}
