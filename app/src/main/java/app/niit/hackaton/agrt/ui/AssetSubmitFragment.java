package app.niit.hackaton.agrt.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import app.niit.hackaton.agrt.AgtrApplication;
import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.dto.Asset;
import app.niit.hackaton.agrt.dto.AssetRegister;
import app.niit.hackaton.agrt.dto.Organisation;
import app.niit.hackaton.agrt.dto.Status;
import app.niit.hackaton.agrt.util.Constants;
import app.niit.hackaton.agrt.util.Util;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssetSubmitFragment} interface
 * to handle interaction events.
 * Use the {@link AssetSubmitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssetSubmitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String provider;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LocationManager locationManager;
    private double latitude = 0, longitude = 0;

    private String mScanCode, mScanFormat;
    private View mRootView;
    private EditText mScanCodeET, mScanFormatET, mAssetNameET, mAssetDescrET, mOwnerNameET, mEmployeeNameET;
    private Spinner mOrganisation;
    private ToggleButton mInOutButton;
    private Button mSubmitAssetBtn;

    //private OnFragmentInteractionListener mListener;

    public AssetSubmitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssetSubmitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssetSubmitFragment newInstance(String param1, String param2) {
        AssetSubmitFragment fragment = new AssetSubmitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (null != bundle) {
            if (bundle.getString(Constants.SCAN_CODE) == null && bundle.getString(Constants.SCAN_FORMAT) == null) {
                NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(getActivity());
                if (nfcAdapter == null) {
                    Toast.makeText(getActivity(), "NFC NOT supported on this devices!", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                } else if (!nfcAdapter.isEnabled()) {
                    Toast.makeText(getActivity(), "NFC NOT Enabled!", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                Tag tag = bundle.getParcelable(NfcAdapter.EXTRA_TAG);
                byte[] tagId = tag.getId();
                String tagCode = "";
                for (int i = 0; i < tagId.length; i++) {
                    tagCode += Integer.toHexString(tagId[i] & 0xFF) + " ";
                }
                mScanCode = tagCode;
                mScanFormat = "NFC_TAG";
            } else {
                mScanCode = bundle.getString(Constants.SCAN_CODE);
                mScanFormat = bundle.getString(Constants.SCAN_FORMAT);
            }
        }

        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_asset_submit, null);
        mSubmitAssetBtn = (Button) mRootView.findViewById(R.id.submit_asset);
        mOrganisation = (Spinner) mRootView.findViewById(R.id.Organisation);
        mInOutButton = (ToggleButton) mRootView.findViewById(R.id.In_Out);
        mAssetNameET = (EditText) mRootView.findViewById(R.id.Asset_Name);
        mAssetDescrET = (EditText) mRootView.findViewById(R.id.Asset_Description);
        mOwnerNameET = (EditText) mRootView.findViewById(R.id.Asset_Owner);
        mEmployeeNameET = (EditText) mRootView.findViewById(R.id.employee_name);
        mScanCodeET = (EditText) mRootView.findViewById(R.id.Scan_Code);
        mScanFormatET = (EditText) mRootView.findViewById(R.id.Scan_Type);

        // If the barcode already exists, then show only Asset Registry fields
        final Asset asset = AgtrApplication.getDbHelper().getAssetByScanCodeAndType(mScanCode, mScanFormat);
        if (asset == null) {
            showAssetDetails();
            prefillData();
        } else
            hideAssetDetails();

        mSubmitAssetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    submitAsset(asset);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return mRootView;
    }

    private void prefillData() {
        mScanCodeET.setText(mScanCode);
        mScanFormatET.setText(mScanFormat);
        try {
            ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, Util.getOrganisationList());
            mOrganisation.setAdapter(adapter);
        } catch (Exception e) {
            // Handle the exception in case no orgainisation is created by the user
            Toast.makeText(getActivity(), "Create Organisation First!", Toast.LENGTH_SHORT);
        }
    }

    private void hideAssetDetails() {
        mAssetNameET.setVisibility(View.GONE);
        mAssetDescrET.setVisibility(View.GONE);
        mScanCodeET.setVisibility(View.GONE);
        mScanFormatET.setVisibility(View.GONE);
        mOwnerNameET.setVisibility(View.GONE);
        mOrganisation.setVisibility(View.GONE);
    }

    private void showAssetDetails() {
        mAssetNameET.setVisibility(View.VISIBLE);
        mAssetDescrET.setVisibility(View.VISIBLE);
        mScanCodeET.setVisibility(View.VISIBLE);
        mScanFormatET.setVisibility(View.VISIBLE);
        mOwnerNameET.setVisibility(View.VISIBLE);
        mOrganisation.setVisibility(View.VISIBLE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    /**
     * Provides the complete location name using the current lat, lng values
     *
     * @return Location name
     * @throws IOException
     */
    private String getLocationName() throws IOException {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        return getFullAddress(addresses.get(0));
    }

    /**
     * Concatenates all the address lines of the given address
     *
     * @param address
     * @return
     */
    private String getFullAddress(Address address) {
        String addrs = address.getAddressLine(0);
        for (int i = 1; i < address.getMaxAddressLineIndex(); i++) {
            addrs = addrs + ", " + address.getAddressLine(i);
        }
        return addrs;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *
     * @param asset
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }*/
    private void submitAsset(Asset asset) throws IOException {
        //Save asset in the database, if not exist
        if (asset == null) {
            asset = new Asset();
            Organisation organisation = (Organisation) mOrganisation.getSelectedItem();
            asset.setOrg(organisation);
            asset.setAssetName(mAssetNameET.getText().toString());
            asset.setAssetDescription(mAssetDescrET.getText().toString());
            asset.setAssetOwner(mOwnerNameET.getText().toString());
            asset.setScanCode(mScanCodeET.getText().toString());
            asset.setScanType(mScanFormatET.getText().toString());
            long assetRow = AgtrApplication.getDbHelper().saveAsset(asset);
            if (assetRow != -1) {
                Toast.makeText(this.getContext(), "Asset saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getContext(), "Asset save failed", Toast.LENGTH_SHORT).show();
            }
        }

        //TODO : Set actual location name and date values
        long date = 0;

        getCurrentLocation();
        //Stay on the same screen if the lat, lng are not available and let the user enable location settings
        if (latitude != 0 || longitude != 0.0) {
            AssetRegister assetRegister = new AssetRegister();
            assetRegister.setAsset(Util.getAssetByScanCodeAndType(mScanCode, mScanFormat));
            assetRegister.setEmpName(mEmployeeNameET.getText().toString());
            assetRegister.setLatitude(latitude);
            assetRegister.setLongitude(longitude);
            assetRegister.setLocation(getLocationName());
            assetRegister.setRegisterDate(date);
            if (mInOutButton.isChecked())
                assetRegister.setStatus(Status.IN);
            else
                assetRegister.setStatus(Status.OUT);

            long assetRegisteryRow = AgtrApplication.getDbHelper().saveAssetRegister(assetRegister);
            if (assetRegisteryRow != -1) {
                Toast.makeText(this.getContext(), "Asset registry saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getContext(), "Asset registry save failed", Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(getActivity().getApplicationContext(), DashboardActivity.class));
            getActivity().finish();
        }
    }

    /**
     * Gets the current location ,shows an alert if no location provider available
     */
    private void getCurrentLocation() {
        showProviderAlert();
        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();
        //Getting the name of best provider
        provider = locationManager.getBestProvider(criteria, true);
        // Getting Current Location
        try {
            Location location = locationManager.getLastKnownLocation(provider);
            //Use NETWORK_PROVIDER, if GPS is not working
            if (location == null)
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            //Update location if not null
            if (location != null) {
                onLocationChanged(location);
            }
        } catch (SecurityException e) {

        }
    }

    public void onLocationChanged(Location location) {
        // Get latitude of the current location
        latitude = location.getLatitude();

        // Get longitude of the current location
        longitude = location.getLongitude();
    }

    /**
     * Checks for the available location providers and shows an alert to enable the provider, if no provider found
     */
    private void showProviderAlert() {
        boolean gps_enabled = false;
        boolean network_enabled = false;
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
        }

        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            //Setting Dialog Title
            alertDialog.setTitle(R.string.GPSAlertDialogTitle);

            //Setting Dialog Message
            alertDialog.setMessage(R.string.GPSAlertDialogMessage);

            //On Pressing Setting button
            alertDialog.setPositiveButton(R.string.action_settings, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    getActivity().startActivity(intent);
                }
            });

            //On pressing cancel button
            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }
    }
}
