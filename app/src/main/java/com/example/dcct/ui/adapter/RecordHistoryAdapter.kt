package com.example.dcct.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dcct.R
import com.example.dcct.bean.Record

class RecordHistoryAdapter(private val mRecordList: List<Record>) : RecyclerView.Adapter<RecordHistoryAdapter.ViewHolder>() {
    private var mClickListener: OnItemClickListener? = null
    fun setOnClickItems(listener: OnItemClickListener?) {
        mClickListener = listener
    }

    interface OnItemClickListener {
        fun onClickItem(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_gauging_history, parent, false)
        val holder = ViewHolder(itemView)
        itemView.setOnClickListener { v: View? -> mClickListener!!.onClickItem(holder.adapterPosition) }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = mRecordList[position]
        //        holder.imageLeft.setImageResource(R.drawable.medicine_logo);
//        if (record.getGaugingKind() == 0){
//            holder.imageRight.setImageResource(R.drawable.food_logo);
//        }else {
//            holder.imageRight.setImageResource(R.drawable.medicine_logo);
//        }
        holder.thingNames.text = String.format("%s", record.queryName)
        holder.gaugingTime.text = record.queryTime
    }

    override fun getItemCount(): Int {
        return mRecordList.size
    }

    inner class ViewHolder(private var recordHistoryView: View) : RecyclerView.ViewHolder(recordHistoryView) {
        var imageLeft: ImageView = recordHistoryView.findViewById(R.id.thing1)
        var imageRight: ImageView = recordHistoryView.findViewById(R.id.thing2)
        var thingNames: TextView = recordHistoryView.findViewById(R.id.thingNames)
        var gaugingTime: TextView = recordHistoryView.findViewById(R.id.gaugingTime)

    }

}