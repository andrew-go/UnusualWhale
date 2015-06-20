package andrii.goncharenko.unusualwhale.Dialogs;

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
import android.widget.ImageButton;

import andrii.goncharenko.unusualwhale.R;
import andrii.goncharenko.unusualwhale.Settings.GameSettings;

public class MenuSettingsDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_menu_settings, null);
        builder.setView(view);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.btSound);
        imageButton.setBackground(GameSettings.Instance().isMusicOn
                ? getActivity().getDrawable(R.drawable.sound_icon_on)
                : getActivity().getDrawable(R.drawable.sound_icon_off));
        return builder.create();
    }

}