package com.michaeldavidsim.swapi_android_demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.michaeldavidsim.swapi_android_demo.adapters.ViewAdapter;
import com.michaeldavidsim.swapi_android_demo.utils.FetchPeopleDataTask;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rv;
    private ViewAdapter.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv = findViewById(R.id.recyclerView);

        setOnClickListener();
        ViewAdapter va = new ViewAdapter(this, listener);
        rv.setAdapter(va);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && FetchPeopleDataTask.page < 9) {
                    new FetchPeopleDataTask().execute(va);
                    Toast.makeText(HomeActivity.this,
                            "Fetching data...",
                            Toast.LENGTH_SHORT)
                            .show();
                } else if(FetchPeopleDataTask.page > 9){
                    Toast.makeText(HomeActivity.this,
                            "All characters have been loaded.",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            i.putExtra("id", position);
            Log.d("BUG", String.valueOf(position));
            startActivity(i);
        };
    }
}
