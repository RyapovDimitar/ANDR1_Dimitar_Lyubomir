package com.example.andr1.fontysappliation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
                Intent gradesIntent = new Intent(MainActivity.this,
                        GradesActivity.class);
                startActivity(gradesIntent);
                return true;
            case R.id.menu_schedule:
                showMsg("Schedule selected");
                Intent scheduleIntent = new Intent(MainActivity.this,
                        GradesActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.menu_main_menu:
                showMsg("Schedule selected");
                Intent mainMenuIntent = new Intent(MainActivity.this,
                        MainActivity.class);
                startActivity(mainMenuIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMsg(String msg) {
        Toast toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
        toast.show();
    }
}
