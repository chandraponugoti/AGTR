package app.niit.hackaton.agrt.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
            mScanCode = bundle.getString(Constants.SCAN_CODE);
            mScanFormat = bundle.getString(Constants.SCAN_FORMAT);
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
                submitAsset(asset);
            }
        });
        return mRootView;
    }

    private void prefillData() {
        mScanCodeET.setText(mScanCode);
        mScanFormatET.setText(mScanFormat);
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, Util.getOrganisationList());
            /** Setting the adapter to the ListView */
            mOrganisation.setAdapter(adapter);
        } catch (Exception e) {
            // Handle the exception in case no orgainisation is created by the user
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
    private void submitAsset(Asset asset) {
        //Save asset in the database, if not exist
        if (asset == null) {
            asset = new Asset();
            Organisation organisation = AgtrApplication.getDbHelper().getOrganisationIdByName(mOrganisation.getSelectedItem().toString());
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

        //TODO : Set actual lat, lng, date and location values
        long lat = 0, lng = 0, date = 0;
        // check if GPS enabled
//                GPSTracker gpsTracker = new GPSTracker(getActivity());
//
//                if (gpsTracker.getIsGPSTrackingEnabled()) {
//                    String lat = String.valueOf(gpsTracker.latitude);
//                    String lng = String.valueOf(gpsTracker.latitude);
//                }
        AssetRegister assetRegister = new AssetRegister();
        assetRegister.setAsset(asset);
        assetRegister.setEmpName(mEmployeeNameET.getText().toString());
        assetRegister.setLatitude(lat);
        assetRegister.setLongitude(lng);
        assetRegister.setLocation("Bangalore");
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
