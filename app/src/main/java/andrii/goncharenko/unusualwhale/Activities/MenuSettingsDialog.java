package andrii.goncharenko.unusualwhale.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andrii.goncharenko.unusualwhale.R;
import andrii.goncharenko.unusualwhale.Settings.GameSettings;

/**
 * Created by Y50-70 on 20.06.2015.
 */
public class MenuSettingsDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_menu_settings, null));
        return builder.create();
    }

}