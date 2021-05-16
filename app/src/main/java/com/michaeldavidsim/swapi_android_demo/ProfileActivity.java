package com.michaeldavidsim.swapi_android_demo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.michaeldavidsim.swapi_android_demo.models.People;
import com.michaeldavidsim.swapi_android_demo.utils.FetchPeopleDataTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProfileActivity extends AppCompatActivity {
    TextView name, planet, films;
    String planetName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // should return pos if -1 that means something went wrong
        int pos = getIntent().getIntExtra("id", -1);
        People person = FetchPeopleDataTask.people.get(pos);

        name = findViewById(R.id.profileName);
        planet = findViewById(R.id.planet);
        films = findViewById(R.id.numOfFilms);

        new FetchPlanet().execute(person.homeWorldUrl);
        name.setText(person.name);
        films.setText(String.valueOf(person.films));
    }

    class FetchPlanet extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... s) {
            OkHttpClient httpClient = new OkHttpClient();
            Request req = new Request.Builder()
                    .url(s[0])
                    .build();
            try {
                Response res = httpClient.newCall(req).execute();
                if(res.code() == 200) {
                    JSONObject json = new JSONObject(res.body().string());
                    planetName = json.getString("name");
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            planet.setText(planetName);
        }
    }
}
