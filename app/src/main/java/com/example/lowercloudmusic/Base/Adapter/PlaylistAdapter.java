package com.example.lowercloudmusic.Base.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lowercloudmusic.Base.myablum;
import com.example.lowercloudmusic.R;
import com.example.lowercloudmusic.util.httphelper.picloadutil.MyGlide;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private List<myablum> myablums;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myablum_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.rootview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        myablum myablum = myablums.get(position);
        MyGlide.with(holder.rootview.getContext()).load(myablum.getPicurl()).setCompress(10).setRatio(1f).into(holder.image);
        holder.track.setText(myablum.getTrackCount());
        holder.nickname.setText(myablum.getNickname());
        holder.name.setText(myablum.getName());

    }

    @Override
    public int getItemCount() {
        return myablums.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View rootview;
        ImageView image;
        TextView name;
        TextView nickname;
        TextView track;
        public ViewHolder(View view){
            super(view);
            rootview = view;
            image = view.findViewById(R.id.playlist_image);
            name = view.findViewById(R.id.myplaylist_name);
            nickname  = view.findViewById(R.id.playlist_name);
            track = view.findViewById(R.id.number_time);

        }
    }
    public PlaylistAdapter(List<myablum> myablums){
        this.myablums = myablums;
    }
}
