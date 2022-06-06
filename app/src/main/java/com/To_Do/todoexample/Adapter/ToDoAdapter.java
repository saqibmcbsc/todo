package com.To_Do.todoexample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.To_Do.todoexample.Constants;
import com.To_Do.todoexample.R;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.holder> {

    ArrayList<String> list;
    Context context;

    public ToDoAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclertododesign,parent,false);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.textwashing.setText(list.get(position));

        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                Constants.saveArrayList(list,"task",context);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder {

        TextView textwashing;
        ImageView close;

        public holder(@NonNull View itemView) {
            super(itemView);

            textwashing = itemView.findViewById(R.id.textwashing);
            close = itemView.findViewById(R.id.close);
        }
    }
}
