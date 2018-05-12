package com.nexu.projet.miashs.nexu;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;
import android.view.View;

import java.util.Calendar;

public class TimePickerFragment2 extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        // Do something with the time chosen by the user
        TextView heureFin = getActivity().findViewById(R.id.hfin);
        TextView minuteFin = getActivity().findViewById(R.id.mfin);
        heureFin.setText(hour+"");
        minuteFin.setText(minute+"");
    }
}