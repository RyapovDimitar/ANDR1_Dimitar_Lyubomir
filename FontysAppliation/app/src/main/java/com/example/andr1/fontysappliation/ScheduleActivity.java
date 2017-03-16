package com.example.andr1.fontysappliation;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by az on 19.2.2017 г..
 */

public class ScheduleActivity extends AppCompatActivity
implements  TokenFragment.OnFragmentInteractionListener{
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }

    @Override
    public void onFragmentInteraction(String token) {
        AsyncSchedule2 task = new AsyncSchedule2(this);
        task.execute(token);
        showMsg(token);
    }

    public void showMsg(String msg) {
        Toast toast = Toast.makeText(ScheduleActivity.this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
        toast.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_grades:
                Intent gradesIntent = new Intent(ScheduleActivity.this,
                        GradesActivity.class);
                startActivity(gradesIntent);
                return true;
            case R.id.menu_schedule:
                Intent scheduleIntent = new Intent(ScheduleActivity.this,
                        ScheduleActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.menu_main_menu:
                Intent mainMenuIntent = new Intent(ScheduleActivity.this,
                        MainActivity.class);
                startActivity(mainMenuIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

class AsyncSchedule2 extends AsyncTask<String, Void, String> {

    private ScheduleActivity a;
    protected AsyncSchedule2(ScheduleActivity act){
        a=act;
    }
    @Override
    protected String doInBackground(String... params) {
        String myString = params[0];
        try {
            URL url = new URL("https://api.fhict.nl/schedule/me");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + myString);
            connection.connect();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            JsonReader jsonReader = new JsonReader(isr);
            return "sucess";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myString;
    }

    @Override
    protected void onPostExecute(String result){
        //super.onPostExecute(result);
        a.showMsg(result);
        //Log.d("tokenRes", result);
    }
}
