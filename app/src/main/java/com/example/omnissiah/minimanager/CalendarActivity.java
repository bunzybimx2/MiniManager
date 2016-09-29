package com.example.omnissiah.minimanager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.provider.CalendarContract.Events;

import java.util.Calendar;

/**
 * Created by Omnissiah on 3/7/2016.
 */
public class CalendarActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    public void onAddEventClicked(View view){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(Events.TITLE,"Birthday");
        intent.putExtra(Events.DESCRIPTION, "Sample desc.");
        intent.putExtra(Events.EVENT_LOCATION, "My Guest House");
        intent.putExtra(Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
    }
}
