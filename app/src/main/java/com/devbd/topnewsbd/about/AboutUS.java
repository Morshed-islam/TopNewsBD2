package com.devbd.topnewsbd.about;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.devbd.topnewsbd.MainActivity;
import com.devbd.topnewsbd.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AboutUS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        //------Add toolbar button for always going home


        // Get raw spanned text
        Resources res = getResources();
        BufferedReader input = new BufferedReader(new InputStreamReader(
                res.openRawResource(R.raw.aboutme)));
        StringBuffer sb = new StringBuffer();
        try {
            String line;
            while ((line = input.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Spanned spannedCode = Html.fromHtml(sb.toString());
        ((TextView)findViewById(R.id.aboutme)).setText(spannedCode);



    }


    //use this method for always going to home activity
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
