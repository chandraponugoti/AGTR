package app.niit.hackaton.agrt.ui;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import app.niit.hackaton.agrt.R;

public class AssetSubmitActivity extends FragmentActivity {

    private AssetSubmitFragment mFragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        Bundle extras = getIntent().getExtras();
        mFragment = new AssetSubmitFragment();
        mFragment.setArguments(extras);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mFragment).commit();
    }
}
