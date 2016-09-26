package app.niit.hackaton.agrt.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.niit.hackaton.agrt.R;

public class AssetSubmitActivity extends FragmentActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new AssetSubmitFragment()).commit();
    }
}
