package com.example.noratel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener, MainFragment.MainFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView,new LoginFragment())
                .commit();
    }

    @Override
    public void authSuccessful() {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new MainFragment())
                .commit();
    }
}