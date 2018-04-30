package com.nexu.projet.miashs.nexu;

import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import android.support.annotation.RestrictTo;
public class EventDay {
    private Calendar mDay;
    private int mImageResource;
    private boolean mIsDisabled;

    /**
     * @param day Calendar object which represents a date of the event
     */
    public EventDay(Calendar day) {
        mDay = day;
    }


    /**
     * @param day           Calendar object which represents a date of the event
     * @param imageResource Resource of an image which will be displayed in a day cell
     */
    public EventDay(Calendar day, int imageResource) {
        mDay = day;
        mImageResource = imageResource;
    }


    /**
     * @return An image resource which will be displayed in the day row
     */
    public int getImageResource() {
        return mImageResource;
    }


    /**
     * @return Calendar object which represents a date of current event
     */
    public Calendar getCalendar() {
        return mDay;
    }


    /**
     * @return Boolean value if day is not disabled
     */
    public boolean isEnabled() {
        return !mIsDisabled;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public void setEnabled(boolean enabled) {
        mIsDisabled = enabled;
    }
}
