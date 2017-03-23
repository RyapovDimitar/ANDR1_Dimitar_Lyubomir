package com.example.andr1.fontysappliation;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 16.3.2017 г..
 */



public class AsyncSchedule extends AsyncTask<String, Void, List<ScheduleElement>> {
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
            this.readScheduleArray(jsonReader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ScheduleElement> readScheduleArray(JsonReader jsonReader) throws IOException {
        List<ScheduleElement> scheduleElements = new ArrayList<ScheduleElement>();

        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            scheduleElements.add(readScheduleElement(jsonReader));
        }
        jsonReader.endArray();
        return scheduleElements;
    }
    public ScheduleElement readScheduleElement(JsonReader jsonReader) throws IOException {
        ScheduleElement se = null;

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            if (name.equals("room")) {
                se.room = jsonReader.nextString();
            } else if (name.equals("subject")) {
                se.subject = jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        return se;
    }
    @Override
    protected void onPostExecute(List<ScheduleElement> result){
        super.onPostExecute(result);
    }
}
