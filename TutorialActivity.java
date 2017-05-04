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
        Uri uri = Uri.parse("rtsp://r9---sn-5hnedn7y.googlevideo.com/Cj0LENy73wIaNAmvRsEdwm_1ChMYDSANFC1BvARZMOCoAUIASARg8_a5t-HFyplYigELOFZBRHN2QUNBemcM/C2B7518CA13B656220B52B410E447B21E7C37D1E.4EC2D93A15117806D88758717CC04A4FDE8BE2B7/yt6/1/video.3gp");
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