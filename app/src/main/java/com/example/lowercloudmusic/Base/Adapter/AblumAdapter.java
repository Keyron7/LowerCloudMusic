package com.example.lowercloudmusic.Base.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lowercloudmusic.Base.Ablum;
import com.example.lowercloudmusic.R;
import com.example.lowercloudmusic.util.httphelper.picloadutil.MyGlide;

import java.util.List;

public class AblumAdapter extends RecyclerView.Adapter<AblumAdapter.ViewHolder> {

    private List<Ablum> ablumList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View rootview;
        ImageView ablum;
        public ViewHolder(View view){
            super(view);;
            rootview = view;
            ablum = (ImageView) view.findViewById(R.id.ablum);
        }
    }
    public  AblumAdapter(List<Ablum> ablums){
        this.ablumList=ablums;
    }

    @NonNull
    @Override
    public AblumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.ablum_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.rootview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AblumAdapter.ViewHolder holder, int position) {
        Ablum ablum = ablumList.get(position);
        MyGlide.with(holder.rootview.getContext()).load(ablum.getImageUrl()).setRatio(1f).setCompress(10).into(holder.ablum);
    }

    @Override
    public int getItemCount() {
        return ablumList.size();
    }
}
