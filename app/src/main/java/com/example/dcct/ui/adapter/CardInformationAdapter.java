package com.example.dcct.ui.adapter;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dcct.R;
import com.example.dcct.bean.CoverEntity;

import java.util.List;

public class CardInformationAdapter extends RecyclerView.Adapter<CardInformationAdapter.ViewHolder> {
    private List<CoverEntity> listCard;
    private Context mContext;
    private ColorMatrix mColorMatrix = new ColorMatrix();
    private ColorMatrixColorFilter filter;

    public CardInformationAdapter(List<CoverEntity> listCard, Context context) {
        this.listCard = listCard;
        mContext = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View cardInformationView;
        ImageView cardImage;
        TextView declareText;

        ViewHolder(@NonNull View itemView) {
            super( itemView );
            cardInformationView = itemView;
            cardImage = itemView.findViewById( R.id.cardImage );
            declareText = itemView.findViewById( R.id.card_title );
            /*mColorMatrix.setSaturation( 0 );//设置饱和度为0，即灰色
            filter = new ColorMatrixColorFilter( mColorMatrix );
            cardImage.setColorFilter( filter );*/
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_fragment_ground, parent, false );
        final ViewHolder holder = new ViewHolder( itemView );
        holder.cardInformationView.setOnClickListener( v -> {
            //点击卡片触发的事件
        } );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoverEntity coverEntity = listCard.get( position );
        glideLoadImage( coverEntity.getImageUrl(), holder );
        holder.declareText.setText( coverEntity.getDescribes() );
    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }

    private void glideLoadImage(String url, ViewHolder holder) {
        Glide.with( mContext )
                .load( url )
                .fitCenter()
                .into( holder.cardImage );
    }

}
