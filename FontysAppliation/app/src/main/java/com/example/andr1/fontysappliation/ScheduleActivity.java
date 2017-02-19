package com.example.andr1.fontysappliation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by az on 19.2.2017 г..
 */

public class ScheduleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_grades:
                showMsg("Grades selected");
                Intent myIntent = new Intent(ScheduleActivity.this,
                        MainActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.menu_schedule:
                showMsg("Schedule selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMsg(String msg) {
        Toast toast = Toast.makeText(ScheduleActivity.this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
        toast.show();
    }
}
