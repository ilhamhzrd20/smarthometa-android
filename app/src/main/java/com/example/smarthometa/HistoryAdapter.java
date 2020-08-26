package com.example.smarthometa;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private ArrayList<History> dataList;

    public HistoryAdapter(ArrayList<History> dataList) {
        this.dataList = dataList;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.HistoryViewHolder holder, int position) {
        holder.txtTime.setText(String.valueOf(dataList.get(position).getTime()));
        holder.txtMessage.setText(String.valueOf(dataList.get(position).getStatus()));
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTime, txtMessage;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            txtTime = (TextView) itemView.findViewById(R.id.txt_time);
            txtMessage = (TextView) itemView.findViewById(R.id.txt_message);
        }
    }
}
