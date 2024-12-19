package com.example.spongebob;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;
    private List<DataModel> filteredList;
    private Context context;
    public CustomeAdapter(ArrayList<DataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.filteredList = new ArrayList<>(dataSet);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel item = filteredList.get(position);
        holder.textViewName.setText(item.getName());
        holder.textViewVersion.setText(item.getDescription());
        holder.imageView.setImageResource(item.getImage());
        holder.itemView.setOnClickListener(v -> showPopup(item));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
    private void showPopup(DataModel item) {
        View popupView = LayoutInflater.from(context).inflate(R.layout.popup_layout, null);

        ImageView imageView = popupView.findViewById(R.id.popupImageView);
        TextView textView = popupView.findViewById(R.id.popupTextView);

        imageView.setImageResource(item.getImage());
        textView.setText("Name: " + item.getName() + "\n\nDescription: " + item.getDescription());
        textView.setTextColor(Color.BLACK);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(popupView)
                .setCancelable(true)
                .create();

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        popupView.setBackgroundColor(Color.parseColor("#E6FFFFFF"));
        dialog.show();
    }

    public void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(dataSet);
        } else {
            text = text.toLowerCase();
            for (DataModel item : dataSet) {
                if (item.getName().toLowerCase().contains(text)) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewVersion;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textView);
            this.textViewVersion = itemView.findViewById(R.id.textView2);
            this.imageView = itemView.findViewById(R.id.imageView);
        }
    }
}