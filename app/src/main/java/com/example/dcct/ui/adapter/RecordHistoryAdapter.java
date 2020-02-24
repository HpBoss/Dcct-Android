package com.example.dcct.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dcct.R;
import com.example.dcct.model.internet.model.Record;

import java.util.List;

public class RecordHistoryAdapter extends RecyclerView.Adapter<RecordHistoryAdapter.ViewHolder> {
    private List<Record> mRecordList ;
    private OnItemClickListener mClickListener;
    public RecordHistoryAdapter(List<Record> recordList) {
        this.mRecordList = recordList;
    }

    public void setOnClickItems(OnItemClickListener listener){
        mClickListener = listener;
    }

    public interface OnItemClickListener{
        void onClickItem(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gauging_history,parent,false);
        final ViewHolder holder = new ViewHolder(itemView);
        itemView.setOnClickListener( v -> mClickListener.onClickItem(holder.getAdapterPosition()) );
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Record record = mRecordList.get(position);
//        holder.imageLeft.setImageResource(R.drawable.medicine_logo);
//        if (record.getGaugingKind() == 0){
//            holder.imageRight.setImageResource(R.drawable.food_logo);
//        }else {
//            holder.imageRight.setImageResource(R.drawable.medicine_logo);
//        }
        holder.thingNames.setText(String.format("%s", record.getQueryName()));
        holder.gaugingTime.setText(record.getQueryTime());
    }

    @Override
    public int getItemCount() {
        return mRecordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View recordHistoryView;
        ImageView imageLeft,imageRight;
        TextView thingNames;
        TextView gaugingTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recordHistoryView = itemView;
            imageLeft = itemView.findViewById(R.id.thing1);
            imageRight = itemView.findViewById(R.id.thing2);
            thingNames = itemView.findViewById(R.id.thingNames);
            gaugingTime = itemView.findViewById(R.id.gaugingTime);
        }
    }
}
