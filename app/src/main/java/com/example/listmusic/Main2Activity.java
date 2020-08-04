package com.example.listmusic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ListAdapter;



import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ImageView image;
    ImageButton btnStop,btnPlay,btnPrev,btnNext;
    TextView txtName,txtSinger , tgdau,tgcuoi;
    SeekBar seekBar;
    ArrayList<Music> arrayList;
    ListView listView;
    CustomMusicAdapter adapter;
    MediaPlayer mediaPlayer;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setControl();
        khoitao();
        setEvent();

    }

    private void setControl() {
        image = (ImageView)findViewById(R.id.Imagerhinh);
        txtName  = (TextView)findViewById(R.id.Name);
        txtSinger = (TextView)findViewById(R.id.Singer);
        tgdau = (TextView)findViewById(R.id.tv_dau);
        tgcuoi = (TextView)findViewById(R.id.tv_cuoi);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        btnPrev = (ImageButton)findViewById(R.id.Prev);
        btnStop = (ImageButton)findViewById(R.id.Stop);
        btnPlay = (ImageButton)findViewById(R.id.Play);
        btnNext = (ImageButton)findViewById(R.id.Next);
        listView = (ListView)findViewById(R.id.lstdanhsach);
    }
    private void khoitao() {

        arrayList = new ArrayList<>();
        arrayList.add(new Music("Âm Thầm Bên Em","Sơn Tùng M-TP",R.drawable.sep1,R.raw.am_tham_ben_em));
        arrayList.add(new Music("Em của ngày hôm qua","Sơn Tùng M-TP",R.drawable.emcuangayhomqua,R.raw.em_cua_ngay_hom_qua));
        arrayList.add(new Music("Không phải dạng vừa đâu","Sơn Tùng M-TP",R.drawable.khong,R.raw.khong_phai_dang_vua_dau));

        listView = findViewById(R.id.lstdanhsach);
        adapter = new CustomMusicAdapter(this, R.layout.list_view, arrayList);
        try {
            listView.setAdapter(adapter);
        }catch(NullPointerException ignored){
        }


    }
    private void play(){
        mediaPlayer = MediaPlayer.create(Main2Activity.this,arrayList.get(position).getFile());
        txtName.setText(arrayList.get(position).getTenbaihat());
        txtSinger.setText(arrayList.get(position).getSinger());
        image.setImageResource(arrayList.get(position).getHinh());
    }
    private void setEvent() {

        //khoi tao bai hat
        play();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.ic_play);
                }
                else{
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.ic_play);
                }
                setTime();
                updateTime();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.ic_play);
                //khoi tao lai bai hat
                play();
                setTime();
                updateTime();
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(position < 0 )
                {
                    position = arrayList.size() - 1;
                }
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                }
                //khoi tao lai bai hat
                play();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.ic_pause);
                setTime();
                updateTime();
            }
        });
    }
    private void setTime(){
        SimpleDateFormat dinhgio = new SimpleDateFormat("mm:ss");
        tgcuoi.setText(dinhgio.format(mediaPlayer.getDuration()) + "");

        //lay tong thoi gian 1 bai hat
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void updateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhgio = new SimpleDateFormat("mm:ss");
                tgdau.setText(dinhgio.format(mediaPlayer.getCurrentPosition()) + "");
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        position++;
                        if(position > arrayList.size() -1)
                        {
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying())
                        {
                            mediaPlayer.stop();
                        }
                        //khoi tao lai bai hat
                        mediaPlayer = MediaPlayer.create(Main2Activity.this,arrayList.get(position).getFile());
                        txtName.setText(arrayList.get(position).getTenbaihat());
                        txtSinger.setText(arrayList.get(position).getSinger());
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.ic_pause);
                        setTime();
                        updateTime();
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);
    }
}
