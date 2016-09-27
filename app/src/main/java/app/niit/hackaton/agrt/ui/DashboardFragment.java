package app.niit.hackaton.agrt.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import app.niit.hackaton.agrt.R;


public class DashboardFragment extends Fragment {

    private Button mLatestUpdates;
    private Button mAssetNfcScan;
    private Button mAssetQrAndBarcodeScan;
    private Button mCreateRole;
    private Button mCreateOrginisation;
    private Button mCreateProfile;
    private static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard,null);
        mLatestUpdates = (Button)rootView.findViewById(R.id.btn_asset_updates);
        mAssetNfcScan = (Button)rootView.findViewById(R.id.btn_nfc_scan);
        mAssetQrAndBarcodeScan = (Button)rootView.findViewById(R.id.btn_qr_barcode_scan);
        mCreateRole = (Button)rootView.findViewById(R.id.btn_role);
        mCreateOrginisation = (Button)rootView.findViewById(R.id.btn_organisations);
        mCreateProfile = (Button)rootView.findViewById(R.id.btn_profile);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        mLatestUpdates.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doShowLatestUpdates();
                    }
                }
        );

        mAssetNfcScan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doRegisterAssetThroughNfc();
                    }
                }
        );

        mAssetQrAndBarcodeScan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doRegisterAssetThroughBarcode();
                    }
                }
        );

        mCreateRole.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doCreateRole();
                    }
                }
        );

        mCreateOrginisation.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doCreateOrganisation();
                    }
                }
        );

        mCreateProfile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doCreateProfile();
                    }
                }
        );
    }

    private void doShowLatestUpdates() {
        startActivity(new Intent(getActivity().getApplicationContext(),LatestUpdatesActivity.class));
        getActivity().finish();
    }

    private void doRegisterAssetThroughNfc() {
        startActivity(new Intent(getActivity().getApplicationContext(),AssetSubmitActivity.class));
    }

    private void doRegisterAssetThroughBarcode() {
        IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
        scanIntegrator.initiateScan();
    }

    private void doCreateRole() {
        startActivity(new Intent(getActivity().getApplicationContext(), RoleActivity.class));
    }

    private void doCreateOrganisation() {
        startActivity(new Intent(getActivity().getApplicationContext(), OrganisationSubmitActivity.class));
    }

    private void doCreateProfile() {
        startActivity(new Intent(getActivity().getApplicationContext(), CreateProfileActivity.class));
    }

    //product barcode mode
    private void scanBarCode() {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
        }
    }

    //product qr code mode
    private void scanQRCode() {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
        }
    }




}
