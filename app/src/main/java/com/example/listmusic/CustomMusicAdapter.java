package com.example.listmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomMusicAdapter extends ArrayAdapter<Music> {

    Context context;
    int resource;
    List<Music> musicList;


    public CustomMusicAdapter(@NonNull Context context, int resource, List<Music> musicList) {
        super(context, resource,musicList);

        this.context = context;
        this.resource = resource;
        this.musicList = musicList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_view,null);

        TextView txtName = view.findViewById(R.id.Name);
        TextView txtSinger = view.findViewById(R.id.Singer);
        ImageView imageView = view.findViewById(R.id.Imagerhinh);

        Music music = musicList.get(position);

        txtName.setText(music.getTenbaihat());
        txtSinger.setText(music.getSinger());
        imageView.setImageDrawable(context.getResources().getDrawable(music.getHinh()));

        return  view;
    }
}
