package com.xwaydesigns.morbamosquetrust;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AudioFileActivity extends AppCompatActivity {

    private ImageView play_pause;
    private TextView current_time_text,total_duration_text;
    private SeekBar play_bar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_file);

        play_pause = findViewById(R.id.playpause);
        current_time_text = findViewById(R.id.current_time_text);
        total_duration_text = findViewById(R.id.total_duration_text);
        play_bar = findViewById(R.id.surah_seekbar);

        mediaPlayer = new MediaPlayer();
        play_bar.setMax(100);

        //----------------------------------------------------------------//
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(mediaPlayer.isPlaying())
                {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    play_pause.setImageResource(R.drawable.ic_play);
                }
                else
                {
                    mediaPlayer.start();
                    play_pause.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
                }
            }
        });
        //----------------------------------------------------------------//
        prepareMediaPlayer();
        //----------------------------------------------------------------------//

        play_bar.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                SeekBar bar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration()/100) * bar.getProgress();
                mediaPlayer.seekTo(playPosition);
                current_time_text.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        //-----------------------------------------------------------------------//

        //-------------------------------------------------------------------------//
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i)
            {
                play_bar.setSecondaryProgress(i);
            }
        });
        //--------------------------------------------------------------------------//

        //----------------------------------------------------------------------------//
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                play_bar.setProgress(0);
                play_pause.setImageResource(R.drawable.ic_play);
                current_time_text.setText(R.string.zero);
                total_duration_text.setText(R.string.zero);
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });

        //-----------------------------------------------------------------------------//

    }

    private void prepareMediaPlayer()
    {
        try
        {
            mediaPlayer.setDataSource("http://infinityandroid.com/music/good_times.mp3");
            mediaPlayer.prepare();
            total_duration_text.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private Runnable updater = new Runnable(){
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            current_time_text.setText(milliSecondsToTimer(currentDuration));
        }
    };

    private void updateSeekBar()
    {
        if(mediaPlayer.isPlaying())
        {
            play_bar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100 ));
            handler.postDelayed(updater,1000);
        }
    }

    private String milliSecondsToTimer(long milliSeconds)
    {
        String timerString = " ";
        String secondString;

        int hours = (int)(milliSeconds/(1000 * 60 * 60));
        int minutes = (int)(milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int)((milliSeconds % (1000 * 60 * 60)) %(1000 * 60)/1000);

        if(hours > 0)
        {
            timerString = hours + ":";
        }

        if(seconds < 10)
        {
            secondString = "0" + seconds;
        }
        else
        {
            secondString = "" + seconds ;
        }

        timerString = timerString + minutes + ":" + secondString;
        return timerString;
    }

    @Override
    protected void onPause() {
        super.onPause();
      //  mediaPlayer.pause();
        mediaPlayer.stop();
        play_pause.setImageResource(R.drawable.ic_play);
        updateSeekBar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // mediaPlayer.pause();
        mediaPlayer.stop();
        play_pause.setImageResource(R.drawable.ic_play);
        updateSeekBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        play_pause.setImageResource(R.drawable.ic_play);
        updateSeekBar();
    }

}//Activity End