package com.embeddedlapps.practicavideo;

import android.content.res.AssetFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
public class MainActivity extends Activity {
    private VideoView video;
    private MediaController ctlr;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_main);
        Button show=(Button)findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ctlr.show();
            }}
        );
        video=(VideoView)findViewById(R.id.video
        );

        video.setVideoPath("android.resource://" +getPackageName()+"/" +R.raw.documentariesandyou);
        ctlr=new MediaController(this);
        ctlr.setMediaPlayer(video);
        video.setMediaController(ctlr);
        video.requestFocus();
    }
}