package com.example.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Map<String, String>> list;
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public RecyclerViewAdapter(List<Map<String, String>> list) {
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, String number);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.information, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Map<String, String> map = list.get(position);
        holder.Name.setText(map.get("name"));
        holder.Number.setText(map.get("number"));
        holder.Type.setText(map.get("type"));
        holder.Time.setText(map.get("duration"));
        holder.Date.setText(map.get("date"));
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = map.get("number");
                    mOnItemClickListener.onItemClick(holder.itemView, a);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Number;
        TextView Type;
        TextView Time;
        TextView Date;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Number = itemView.findViewById(R.id.Number);
            Type = itemView.findViewById(R.id.Type);
            Time = itemView.findViewById(R.id.Time);
            Date = itemView.findViewById(R.id.Date);

        }
    }
}
