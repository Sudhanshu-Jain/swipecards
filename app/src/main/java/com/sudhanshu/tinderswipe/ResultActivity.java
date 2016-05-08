package com.sudhanshu.tinderswipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    public String [] key = {"right","left","left","right","left"};
    public ArrayList<String> answersGiven = new ArrayList<>();
    public int correctAnswers =0;
    TextView num_correctAnswers;
    TextView num_incorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        num_correctAnswers = (TextView)findViewById(R.id.textView4);
        num_incorrectAnswers = (TextView)findViewById(R.id.textView5);
        Intent intent = getIntent();
        answersGiven = intent.getStringArrayListExtra("answer-key");
        for(int i=0;i<key.length;i++){
            if(answersGiven.get(i).equals(key[i]))
                correctAnswers++;
        }
        num_correctAnswers.setText(Integer.toString(correctAnswers));
        num_incorrectAnswers.setText(Integer.toString(answersGiven.size()-correctAnswers));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
