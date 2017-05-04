package com.example.olabo.androidphp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity{

    private TextView textViewUsername, textViewUserEmail;
    //Part i wrote with the help of Android developer & online tutorial
    private Context con;
    private ListView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        con = getApplicationContext();
        result = (ListView)findViewById(R.id.listViewResult);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
       // textViewUserEmail = (TextView) findViewById(R.id.textViewUseremail);


        //textViewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        textViewUsername.setText(SharedPrefManager.getInstance(this).getUsername());


        //get results
        getUserResults();
    }

//Part i wrote with the help of Android developer & online tutorial

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void gotoHeartRate(View view){
        Intent i = new Intent(ProfileActivity.this,HeartRateActivity.class);

        ProfileActivity.this.startActivity(i);
    }

    private void getUserResults(){
       String url = Constants.HEART_DATA + "?userID=" + SharedPrefManager.getInstance(con).getID();
        System.out.println(url);
        StringRequest stringRequest1 = new StringRequest(

                Request.Method.GET,
                url,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        System.out.println("List view: "+response);
                        initialiseList(response);
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(
                                con,
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userID", String.valueOf(SharedPrefManager.getInstance(con).getID()));
                return params;
            }
        };
        RequestHandler.getInstance(con).addToRequestQueue(stringRequest1);
    }

    private void initialiseList(String response){
        String [] beats = response.split("//");
        ArrayList<String> forList = new ArrayList<>();
        int i = 1;
        for(String s: beats){
            if(s.length()>0){
                forList.add(s);
                i++;
            }
        }

        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,forList);
        result.setAdapter(adap);
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

            case R.id.activity_tutorial:
                SharedPrefManager.getInstance(this).Tutorials();
                finish();
                startActivity(new Intent(this,TutorialActivity.class));
                break;

            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }


    }

