package com.example.olabo.androidphp;

/**
 * Created by olabo on 30/04/2017.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse("rtsp://r1---sn-5hne6n7s.googlevideo.com/Cj0LENy73wIaNAm0GyR0F5EEzxMYDSANFC0rkgxZMOCoAUIASARg8_a5t-HFyplYigELOFZBRHN2QUNBemcM/5B737129EF262B4A7D875717C41C3ECC843C0755.B15BDD41D691C7EDB5A9E282AA000EFE252D250C/yt6/1/video.3gp")
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.activity_main:
                SharedPrefManager.getInstance(this).Placepicker();
                finish();
                startActivity(new Intent(this,LocationMainActivity.class));
                break;
            case R.id.activity_profile:
                SharedPrefManager.getInstance(this).Profile();
                finish();
                startActivity(new Intent(this,ProfileActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;

        }
        return true;
    }
}
