package com.example.dcct.ui.adapter

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dcct.R
import com.example.dcct.bean.CoverEntity

class CardInformationAdapter(private val listCard: List<CoverEntity>, private val mContext: Context) : RecyclerView.Adapter<CardInformationAdapter.ViewHolder>() {
    private val mColorMatrix = ColorMatrix()
    private val filter: ColorMatrixColorFilter? = null

    class ViewHolder(var cardInformationView: View) : RecyclerView.ViewHolder(cardInformationView) {
        var cardImage: ImageView = cardInformationView.findViewById(R.id.cardImage)
        var declareText: TextView = cardInformationView.findViewById(R.id.card_title)

        init {
            /*mColorMatrix.setSaturation( 0 );//设置饱和度为0，即灰色
            filter = new ColorMatrixColorFilter( mColorMatrix );
            cardImage.setColorFilter( filter );*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_ground, parent, false)
        val holder = ViewHolder(itemView)
        holder.cardInformationView.setOnClickListener { v: View? -> }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coverEntity = listCard[position]
        glideLoadImage(coverEntity.imageUrl, holder)
        holder.declareText.text = coverEntity.describes
    }

    override fun getItemCount(): Int {
        return listCard.size
    }

    private fun glideLoadImage(url: String?, holder: ViewHolder) {
        Glide.with(mContext)
                .load(url)
                .fitCenter()
                .into(holder.cardImage)
    }

}