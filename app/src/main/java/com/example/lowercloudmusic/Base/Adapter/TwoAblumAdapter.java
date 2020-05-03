package com.example.lowercloudmusic.Base.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lowercloudmusic.Base.Recommend;
import com.example.lowercloudmusic.R;
import com.example.lowercloudmusic.util.httphelper.picloadutil.MyGlide;

import java.util.List;

public class TwoAblumAdapter extends RecyclerView.Adapter<TwoAblumAdapter.ViewHolder> {
    private List<Recommend> mList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        TextView name;
        TextView song;
        View rootView;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            song = view.findViewById(R.id.playlist_name);
            name = view.findViewById(R.id.playlist_writer);
            pic = view.findViewById(R.id.rec_imag);
        }
    }

    public TwoAblumAdapter (List<Recommend>list){
        mList = list;
    }

    @NonNull//三重写
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend,parent,false);
        final ViewHolder holder= new ViewHolder(view);
        holder.rootView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recommend recommend=mList.get(position);
        MyGlide.with(holder.rootView.getContext()).load(recommend.getImageurl()).setRatio(1f).setCompress(10).into(holder.pic);
        holder.name.setText(recommend.getSongname());
        holder.song.setText(recommend.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
