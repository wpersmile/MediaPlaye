package com.example.wper_smile.mediaplaye;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button playBtn=(Button)findViewById(R.id.playBtn);
        final Button pauseBtn=(Button)findViewById(R.id.pauseBtn);
        final Button stopBtn=(Button)findViewById(R.id.stopBtn);

        final MediaPlayer mediaPlayer=new MediaPlayer();


        stopBtn.setEnabled(false);
        pauseBtn.setEnabled(false);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //初始化
                    mediaPlayer.reset();
                    //assets文件夹里面的文件都是保持原始的文件格式，需要用AssetManager以字节流的形式读取文件。
                    AssetManager assetManager=getAssets();
                    AssetFileDescriptor assetFileDescriptor=
                            assetManager.openFd("song.mp3");
                    //载入媒体文件
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                            assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    //准备播放
                    mediaPlayer.prepare();
                    //开始播放
                    mediaPlayer.start();
                    playBtn.setEnabled(false);
                    stopBtn.setEnabled(true);
                    pauseBtn.setEnabled(true);

                }
                catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                {
                    pauseBtn.setText("播放");
                    mediaPlayer.pause();
                }
                else {
                    pauseBtn.setText("暂停");
                    mediaPlayer.start();
                }
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.stop();
                    playBtn.setEnabled(true);
                    stopBtn.setEnabled(false);
                    pauseBtn.setEnabled(false);
                }
                else
                {
                    mediaPlayer.stop();
                    pauseBtn.setText("暂停");
                    playBtn.setEnabled(true);
                    stopBtn.setEnabled(false);
                    pauseBtn.setEnabled(false);
                }

            }
        });
    }
}
