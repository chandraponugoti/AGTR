package app.niit.hackaton.agrt.ui;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import app.niit.hackaton.agrt.R;

public class CreateProfileActivity extends FragmentActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new CreateProfileFragment()).commit();
    }
}