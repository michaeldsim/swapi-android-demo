package com.michaeldavidsim.swapi_android_demo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michaeldavidsim.swapi_android_demo.models.People;
import com.michaeldavidsim.swapi_android_demo.R;
import com.michaeldavidsim.swapi_android_demo.utils.FetchPeopleDataTask;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {
    public static ArrayList<People> people = new ArrayList<>();
    private OnClickListener listener;
    Context context;

    public ViewAdapter(Context ct, OnClickListener listener) {
        this.listener = listener;
        context = ct;
        //        On creation of ViewAdapter, populate the people database with a GET request
        //        to Star Wars people endpoint
        new FetchPeopleDataTask().execute(this);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.MyViewHolder holder, int position) {
        holder.name.setText(people.get(position).name);
        holder.height.setText(people.get(position).height);
        holder.weight.setText(people.get(position).mass);
        holder.birthYear.setText(people.get(position).birthYear);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public interface OnClickListener {
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, height, weight, birthYear;
        ImageView portrait;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            portrait = itemView.findViewById(R.id.portrait);
            name = itemView.findViewById(R.id.name);
            height = itemView.findViewById(R.id.height2);
            weight = itemView.findViewById(R.id.weight2);
            birthYear = itemView.findViewById(R.id.birthyear2);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}

