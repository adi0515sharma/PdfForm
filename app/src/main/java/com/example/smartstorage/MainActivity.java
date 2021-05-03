package com.example.smartstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    LinearLayout sd_card,in_storage;
    LinearLayout google_drive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sd_card=(LinearLayout)findViewById(R.id.sd_storage);
        in_storage=(LinearLayout)findViewById(R.id.internal_storage);
        google_drive=(LinearLayout)findViewById(R.id.google_drive);
        sd_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=getExternalSdCard();
                Log.e("file name",file.getName());
            }
        });
        in_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        google_drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Google_drive.class);
                startActivity(i);
            }
        });
    }
    public File getExternalSdCard() {
        File externalStorage = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            File storage = new File("/storage");

            if(storage.exists()) {
                File[] files = storage.listFiles();


                for (File file : files) {
                    if (file.exists()) {
                        Log.e(file.getName(),file.getPath());
                        try {
                            if (Environment.isExternalStorageRemovable(file)) {
                                externalStorage = file;
                            }
                        } catch (Exception e) {
                            Log.e("TAG", e.toString());
                        }
                    }
                }
            }
        } else {
            // do one of many old methods
            // I believe Doomsknight's method is the best option here
        }

        return externalStorage;
    }
}
