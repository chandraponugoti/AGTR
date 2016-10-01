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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import app.niit.hackaton.agrt.AgtrApplication;
import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.dto.Organisation;
import app.niit.hackaton.agrt.dto.Role;
import app.niit.hackaton.agrt.util.Util;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrganisationSubmitFragment} interface
 * to handle interaction events.
 * Use the {@link OrganisationSubmitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganisationSubmitFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    /**
     * Declaring an ArrayAdapter to set items to ListView
     */
    ArrayAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText mOrganisationName;
    private EditText mBranch;
    private EditText mAddress;
    private Spinner mParent;
    private CheckBox mIsParent;

    //private OnFragmentInteractionListener mListener;
    private Button mSubmit;

    public OrganisationSubmitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrganisationSubmitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrganisationSubmitFragment newInstance(String param1, String param2) {
        OrganisationSubmitFragment fragment = new OrganisationSubmitFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_organisation_submit, container, false);
        mOrganisationName = (EditText)rootView.findViewById(R.id.Organistaion_Name);
        mBranch = (EditText)rootView.findViewById(R.id.Branch_Name);
        mAddress = (EditText)rootView.findViewById(R.id.Address);
        mParent = (Spinner) rootView.findViewById(R.id.Parent_Organisation);
        mIsParent = (CheckBox)rootView.findViewById(R.id.Is_Parent);
        mSubmit = (Button)rootView.findViewById(R.id.submit_organisation);
        adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_item, Util.getParentOrganisationList());
        mParent.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mParent.setVisibility(View.GONE);
        mSubmit.setOnClickListener(this);
        mIsParent.setOnClickListener(this);
        return rootView;
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.Is_Parent:
                if (checked)
                    mParent.setVisibility(View.GONE);
                else
                    mParent.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void doSubmit() {
        Organisation org = new Organisation();
        org.setOrganisationName(mOrganisationName.getText().toString());
        org.setAddress(mAddress.getText().toString());
        org.setBranch(mBranch.getText().toString());
        if(mIsParent.isChecked()) {
            org.setParentId(0L);
        } else {
            Organisation organisation = (Organisation) mParent.getSelectedItem();
            org.setParentId(organisation.getId());
        }
        long row = AgtrApplication.getDbHelper().saveOrganisation(org);
        if(row != -1){
            Toast.makeText(this.getContext(), "Organisation Creation Success", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this.getContext(), "Organisation Creation Failure", Toast.LENGTH_SHORT).show();
        }
        Role defaultRole = new Role();
        defaultRole.setOrg(Util.getOrganisation(mOrganisationName.getText().toString()));
        defaultRole.setRoleName("Admin");
        long roleRow = AgtrApplication.getDbHelper().saveRole(defaultRole);
        if (roleRow != -1) {
            Toast.makeText(this.getContext(), "Default Role Creation Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "Default Role Creation Failure", Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(getActivity().getApplicationContext(), DashboardActivity.class));
        getActivity().finish();
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

    @Override
    public void onClick(View v) {
        if(v == mSubmit) {
            doSubmit();
        }else if(v == mIsParent){
            onCheckboxClicked(v);
        }
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
