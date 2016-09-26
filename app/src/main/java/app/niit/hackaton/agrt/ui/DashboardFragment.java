package app.niit.hackaton.agrt.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.niit.hackaton.agrt.R;


public class DashboardFragment extends Fragment {

    Button mLatestUpdates;
    Button mAssetNfcScan;
    Button mAssetBarcodeScan;
    Button mAssetQrScan;
    Button mCreateOrginisation;
    Button mCreateProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard,null);
        mLatestUpdates = (Button)rootView.findViewById(R.id.btn_asset_updates);
        mAssetNfcScan = (Button)rootView.findViewById(R.id.btn_nfc_scan);
        mAssetBarcodeScan = (Button)rootView.findViewById(R.id.btn_barcode_scan);
        mAssetQrScan = (Button)rootView.findViewById(R.id.btn_qr_scan);
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

        mAssetBarcodeScan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doRegisterAssetThroughBarcode();
                    }
                }
        );

        mAssetQrScan.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doRegisterAssetThroughQr();
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
        startActivity(new Intent(getActivity().getApplicationContext(), LatestUpdatesActivity.class));
        getActivity().finish();
    }

    private void doRegisterAssetThroughNfc() {
        startActivity(new Intent(getActivity().getApplicationContext(), AssetSubmitActivity.class));
        getActivity().finish();
    }

    private void doRegisterAssetThroughBarcode() {
        startActivity(new Intent(getActivity().getApplicationContext(), AssetSubmitActivity.class));
        getActivity().finish();
    }

    private void doRegisterAssetThroughQr() {
        startActivity(new Intent(getActivity().getApplicationContext(), AssetSubmitActivity.class));
        getActivity().finish();
    }

    private void doCreateOrganisation() {
        startActivity(new Intent(getActivity().getApplicationContext(), OrganisationSubmitActivity.class));
        getActivity().finish();
    }

    private void doCreateProfile() {
        startActivity(new Intent(getActivity().getApplicationContext(), CreateProfileActivity.class));
        getActivity().finish();
    }

}
