package com.example.qrapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String contents="vaobhav";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final String str1="https://docs.google.com/forms/d/e/1FAIpQLSc6ZiiIAiZsmM0bFGhKtkzqG3Ad80EAf5tau6EjEit9EKLsNA/formResponse?usp=pp_url&entry.1778953173=";
        final String str2="&entry.489934517=";
        final String str3="&entry.1520593281=";
        final String str4="&submit=Submit";


        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                contents = data.getStringExtra("SCAN_RESULT");

                Toast.makeText(MainActivity.this,contents,Toast.LENGTH_SHORT).show();




                String url = (str1+contents+str2+"cricket"+str3+"IITBBS"+str4);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
                Toast.makeText(MainActivity.this,"2"+contents,Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       TextView qrs=(TextView) findViewById(R.id.qr);

        qrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

                    startActivityForResult(intent, 0);

                } catch (Exception e) {

                    Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
                    startActivity(marketIntent);

                }


            }
        });
    }
}
