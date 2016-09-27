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

        //playBtn.setEnabled(false);
        //pauseBtn.setEnabled(false);
        //stopBtn.setEnabled(false);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaPlayer.reset();

                    AssetManager assetManager=getAssets();
                    AssetFileDescriptor assetFileDescriptor=
                            assetManager.openFd("song.mp3");
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    pauseBtn.setEnabled(true);
                    stopBtn.setEnabled(true);
                    playBtn.setEnabled(true);
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
                    mediaPlayer.stop();
            }
        });
    }
}
