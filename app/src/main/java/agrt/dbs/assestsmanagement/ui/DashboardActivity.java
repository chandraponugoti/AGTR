package agrt.dbs.assestsmanagement.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import agrt.dbs.assestsmanagement.R;

public class DashboardActivity extends FragmentActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new DashboardFragment()).commit();
    }
}
