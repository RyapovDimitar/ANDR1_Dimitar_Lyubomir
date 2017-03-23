package com.example.andr1.fontysappliation;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.JsonToken;
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
import java.util.ArrayList;
import java.util.List;

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
        //showMsg(token);
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

class AsyncSchedule2 extends AsyncTask<String, Void, List<ScheduleElement>> {

    private ScheduleActivity a;
    protected AsyncSchedule2(ScheduleActivity act){
        a=act;
    }
    @Override
    protected List<ScheduleElement> doInBackground(String... params) {
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
            return this.readScheduleArray(jsonReader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ScheduleElement> readScheduleArray(JsonReader jsonReader) throws IOException {
        List<ScheduleElement> scheduleElements = new ArrayList<ScheduleElement>();
        if(jsonReader.peek() == JsonToken.BEGIN_OBJECT){
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                if (name.equals("data")&&(jsonReader.peek() == JsonToken.BEGIN_ARRAY)) {
                    ScheduleElement se = new ScheduleElement();
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()){
                        if(jsonReader.peek()==JsonToken.BEGIN_OBJECT){

                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String name2 = jsonReader.nextName();
                        if (name2.equals("room")&&(jsonReader.peek()==JsonToken.STRING)) {
                            se.room = jsonReader.nextString();
                        } else if (name2.equals("subject")&&(jsonReader.peek()==JsonToken.STRING)) {
                            se.subject = jsonReader.nextString();
                        }
                        else{
                            jsonReader.skipValue();
                        }
                    }
                            jsonReader.endObject();
                    scheduleElements.add(se);

                        }
                    }
                    jsonReader.endArray();
                }
                else{
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();}
        return scheduleElements;
    }
    @Override
    protected void onPostExecute(List<ScheduleElement> result){
        super.onPostExecute(result);
    }
}
