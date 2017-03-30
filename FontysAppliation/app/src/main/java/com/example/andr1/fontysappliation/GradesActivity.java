package com.example.andr1.fontysappliation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 23.2.2017 г..
 */

public class GradesActivity extends MainActivity implements  TokenFragment.OnFragmentInteractionListener{

        private ListView lv;
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

    public void setData(List<GradesElement> se){
        lv = (ListView) findViewById(R.id.listView);
        DataListAdapterGrades dataListAdapter = new DataListAdapterGrades(getApplicationContext(),
                se.toArray(new GradesElement[se.size()]));
        lv.setAdapter(dataListAdapter);
    }
    @Override
    public void onFragmentInteraction(String token) {
        AsyncGrades task = new AsyncGrades(this);
        task.execute(token);
        //showMsg(token);
    }

    public void showMsg(String msg) {
        Toast toast = Toast.makeText(GradesActivity.this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, toast.getXOffset() / 2, toast.getYOffset() / 2);
        toast.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_grades:
                Intent gradesIntent = new Intent(GradesActivity.this,
                        GradesActivity.class);
                startActivity(gradesIntent);
                return true;
            case R.id.menu_schedule:
                Intent scheduleIntent = new Intent(GradesActivity.this,
                        ScheduleActivity.class);
                startActivity(scheduleIntent);
                return true;
            case R.id.menu_main_menu:
                Intent mainMenuIntent = new Intent(GradesActivity.this,
                        MainActivity.class);
                startActivity(mainMenuIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

class AsyncGrades extends AsyncTask<String, Void, List<GradesElement>> {

    private GradesActivity a;
    protected AsyncGrades(GradesActivity act){
        a=act;
    }
    @Override
    protected List<GradesElement> doInBackground(String... params) {
        String myString = params[0];
        try {
            URL url = new URL("https://api.fhict.nl/grades/me");
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
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GradesElement> readScheduleArray(JsonReader jsonReader) throws IOException, ParseException {
        List<GradesElement> gradeElements = new ArrayList<GradesElement>();
        if(jsonReader.peek() == JsonToken.BEGIN_ARRAY){
            jsonReader.beginArray();
            while (jsonReader.hasNext()){
            if(jsonReader.peek() == JsonToken.BEGIN_OBJECT){
                jsonReader.beginObject();
                GradesElement se = new GradesElement();
                while (jsonReader.hasNext()) {
                                    String name2 = jsonReader.nextName();
                                    if (name2.equals("itemCode")&&(jsonReader.peek()==JsonToken.STRING)) {
                                        se.itemCode = jsonReader.nextString();
                                    } else if (name2.equals("date")&&(jsonReader.peek()==JsonToken.STRING)) {
                                        se.date = jsonReader.nextString();
                                    } else if(name2.equals("grade")&&(jsonReader.peek()==JsonToken.NUMBER)){
                                        se.grade = jsonReader.nextDouble()+"";
                                    }
                                    else{
                                        jsonReader.skipValue();
                                    }
                                }
                                jsonReader.endObject();
                gradeElements.add(new GradesElement(se.date, se.itemCode, se.grade));
                }}
                jsonReader.endArray();
            return gradeElements;
                        }
        return null;
        }

    @Override
    protected void onPostExecute(List<GradesElement> result){
        //super.onPostExecute(result);
        a.setData(result);
    }
}
