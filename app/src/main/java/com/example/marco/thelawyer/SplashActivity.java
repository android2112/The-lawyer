package com.example.marco.thelawyer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread() {
        public void run() {
            try

            {
                sleep(3000);
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            } catch (
                    InterruptedException e)

            {
                e.printStackTrace();
            }


        }
        }.start();



    }
}
