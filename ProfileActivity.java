package com.example.olabo.androidphp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private TextView textViewUsername, textViewUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
       // textViewUserEmail = (TextView) findViewById(R.id.textViewUseremail);
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        //textViewUserEmail.setText(SharedPrefManager.getInstance(this).getUserEmail());
        textViewUsername.setText(SharedPrefManager.getInstance(this).getUsername());

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(10f, 0));
        yvalues.add(new Entry(10f, 1));
        yvalues.add(new Entry(10f, 2));
        yvalues.add(new Entry(10f, 3));
        yvalues.add(new Entry(10f, 4));
        yvalues.add(new Entry(10f, 5));
        yvalues.add(new Entry(10f, 6));

        PieDataSet dataSet = new PieDataSet(yvalues, "Days of the Week");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Monday");
        xVals.add("Tuesday");
        xVals.add("Wednesday");
        xVals.add("Thursday");
        xVals.add("Friday");
        xVals.add("Saturday");
        xVals.add("Saturday");

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.setDescription("This is Pie Chart");
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setOnChartValueSelectedListener(this);
        pieChart.animateXY(1400, 1400);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void gotoHeartRate(View view){
        Intent i = new Intent(ProfileActivity.this,HeartRateActivity.class);

        ProfileActivity.this.startActivity(i);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
        return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                + ", DataSet index: " + dataSetIndex);

    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
        }

    }

