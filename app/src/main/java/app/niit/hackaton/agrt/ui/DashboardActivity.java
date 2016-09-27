package app.niit.hackaton.agrt.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.util.Constants;

public class DashboardActivity extends FragmentActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new DashboardFragment()).commit();
    }

    public void onActivityResult(int requestCode,int resultCode,Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if (scanningResult != null) {
            String scanFormat = scanningResult.getFormatName();
            String scanContent = scanningResult.getContents();


            Toast toast = Toast.makeText(
                    this,
                    scanFormat,Toast.LENGTH_SHORT
            );

            toast.show();

            Intent submitIntent = new Intent(this.getApplicationContext(),AssetSubmitActivity.class);
            submitIntent.putExtra(Constants.SCAN_CODE,scanContent);
            submitIntent.putExtra(Constants.SCAN_FORMAT,scanFormat);
            startActivity(submitIntent);
        } else {
            Toast toast = Toast.makeText(
                    this,
                    "No scan data received!",Toast.LENGTH_SHORT
            );
            toast.show();
        }
    }
}
