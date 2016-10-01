package app.niit.hackaton.agrt.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import app.niit.hackaton.agrt.AgtrApplication;
import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.dto.Organisation;
import app.niit.hackaton.agrt.dto.Role;
import app.niit.hackaton.agrt.dto.User;
import app.niit.hackaton.agrt.util.Util;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateProfileFragment} interface
 * to handle interaction events.
 * Use the {@link CreateProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateProfileFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayAdapter organisationAdapter;
    ArrayAdapter roleAdapter;
    Button mSubmitProfile;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mPassword;
    private EditText mAccountName;
    private EditText mNfcCode;
    private EditText mMobileNumber;
    private Spinner mUserOrganisation;
    private Spinner mUserRole;

   //private OnFragmentInteractionListener mListener;

    public CreateProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateProfileFragment newInstance(String param1, String param2) {
        CreateProfileFragment fragment = new CreateProfileFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_create_profile, container, false);
        mFirstName = (EditText) rootView.findViewById(R.id.First_Name);
        mLastName = (EditText) rootView.findViewById(R.id.Last_Name);
        mPassword = (EditText) rootView.findViewById(R.id.Password);
        mAccountName = (EditText) rootView.findViewById(R.id.Account_Name);
        mMobileNumber = (EditText) rootView.findViewById(R.id.Mobile_Number);
        mNfcCode = (EditText) rootView.findViewById(R.id.Nfc_Code);
        mUserOrganisation = (Spinner) rootView.findViewById(R.id.Org_Profile);
        mUserRole = (Spinner) rootView.findViewById(R.id.Role_Profile);
        mSubmitProfile = (Button) rootView.findViewById(R.id.submit_profile);
        organisationAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, Util.getOrganisationList());
        mUserOrganisation.setAdapter(organisationAdapter);
        organisationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUserOrganisation.setOnItemSelectedListener(this);
        mSubmitProfile.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.menu_create_profile,menu);
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
       // mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        List<Role> roles = Util.getRoles(((Organisation) mUserOrganisation.getSelectedItem()).getId());
        roleAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, roles);
        mUserRole.setAdapter(roleAdapter);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitProfile) {
            doSubmit();
        }
    }

    private void doSubmit() {
        User user = new User();
        user.setFirstName(mFirstName.getText().toString());
        user.setLastName(mLastName.getText().toString());
        user.setPassword(mPassword.getText().toString());
        user.setAccountName(mAccountName.getText().toString());
        user.setMobileNumber(mMobileNumber.getText().toString());
        user.setNfc(mNfcCode.getText().toString());
        user.setOrg((Organisation) mUserOrganisation.getSelectedItem());
        user.setRole((Role) mUserRole.getSelectedItem());
        long row = AgtrApplication.getDbHelper().saveAppUser(user);
        if (row != -1) {
            Toast.makeText(this.getContext(), "User Profile Creation Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "User Profile Creation Failure", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(getActivity().getApplicationContext(), DashboardActivity.class));
        getActivity().finish();
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
