package com.michaeldavidsim.swapi_android_demo.utils;

import android.os.AsyncTask;
import android.widget.Toast;

import com.michaeldavidsim.swapi_android_demo.adapters.ViewAdapter;
import com.michaeldavidsim.swapi_android_demo.models.People;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Made a separate class because it seemed like the easiest way to access this from other classes
public class FetchPeopleDataTask extends AsyncTask<ViewAdapter, Void, Void> {
    public static int page = 1;
    public static ArrayList<People> people = new ArrayList<>();
    ViewAdapter va;

    protected Void doInBackground(ViewAdapter... va) {
        this.va = va[0];

        OkHttpClient httpClient = new OkHttpClient();
        String url = "https://swapi.dev/api/people/?page=" + page;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            String data = response.body().string();
            JSONObject jsonObj = new JSONObject(data);
            JSONArray results = jsonObj.getJSONArray("results");

            // populate the list
            for(int i = 0; i < results.length(); i++) {
                JSONObject person = results.getJSONObject(i);
                JSONArray films = person.getJSONArray("films");

                // add 's' to url to make it https vs http
                StringBuilder sb = new StringBuilder(person.getString("homeworld"));
                sb.insert(4, 's');


                people.add(new People(
                        person.getString("name"),
                        person.getString("birth_year"),
                        person.getString("height"),
                        sb.toString(),
                        person.getString("mass"),
                        films.length()
                ));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        page++;
        ViewAdapter.people = people;
        va.notifyDataSetChanged();
    }
}