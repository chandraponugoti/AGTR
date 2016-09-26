package app.niit.hackaton.agrt.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import agrt.dbs.assestsmanagement.R;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();
    }
}
