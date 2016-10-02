package app.niit.hackaton.agrt.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.base.SessionManager;
import app.niit.hackaton.agrt.util.Util;


public class DashboardFragment extends Fragment {
    private static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    // Session Manager Class
    SessionManager session;
    private Button mLatestUpdates;
    private Button mAssetNfcScan;
    private Button mAssetQrAndBarcodeScan;
    private Button mCreateRole;
    private Button mCreateOrginisation;
    private Button mCreateProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Session class instance
        session = new SessionManager(getActivity().getApplicationContext());
        session.checkLogin();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard,null);
        mLatestUpdates = (Button)rootView.findViewById(R.id.btn_asset_updates);
        mAssetNfcScan = (Button)rootView.findViewById(R.id.btn_nfc_scan);
        mAssetQrAndBarcodeScan = (Button)rootView.findViewById(R.id.btn_qr_barcode_scan);
        mCreateRole = (Button)rootView.findViewById(R.id.btn_role);
        mCreateOrginisation = (Button)rootView.findViewById(R.id.btn_organisations);
        mCreateProfile = (Button)rootView.findViewById(R.id.btn_profile);
        if (!(boolean) session.getUserDetails().get(SessionManager.KEY_ADMIN)) {
            mCreateRole.setVisibility(View.GONE);
            mCreateOrginisation.setVisibility(View.GONE);
            mCreateProfile.setVisibility(View.GONE);
        }
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
        if (Util.isOrganisationCreated()) {
            startActivity(new Intent(getActivity().getApplicationContext(), LatestUpdatesActivity.class));
        } else {
            Toast.makeText(getActivity(), "Create Organisation", Toast.LENGTH_SHORT).show();
        }
    }

    private void doRegisterAssetThroughNfc() {
        if (Util.isOrganisationCreated()) {
            startActivity(new Intent(getActivity().getApplicationContext(), AssetSubmitActivity.class));
        } else {
            Toast.makeText(getActivity(), "Create Organisation", Toast.LENGTH_SHORT).show();
        }
    }

    private void doRegisterAssetThroughBarcode() {
        if (Util.isOrganisationCreated()) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
            scanIntegrator.initiateScan();
        } else {
            Toast.makeText(getActivity(), "Create Organisation", Toast.LENGTH_SHORT).show();
        }
    }

    private void doCreateRole() {
        if (Util.isOrganisationCreated()) {
            startActivity(new Intent(getActivity().getApplicationContext(), RoleActivity.class));
        } else {
            Toast.makeText(getActivity(), "Create Organisation", Toast.LENGTH_SHORT).show();
        }
    }

    private void doCreateOrganisation() {
        startActivity(new Intent(getActivity().getApplicationContext(), OrganisationSubmitActivity.class));
    }

    private void doCreateProfile() {
        if (Util.isOrganisationCreated()) {
            startActivity(new Intent(getActivity().getApplicationContext(), CreateProfileActivity.class));
        } else {
            Toast.makeText(getActivity(), "Create Organisation", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            session.logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
