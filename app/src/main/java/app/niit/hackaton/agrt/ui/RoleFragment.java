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

import app.niit.hackaton.agrt.AgtrApplication;
import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.dto.Organisation;
import app.niit.hackaton.agrt.dto.Role;
import app.niit.hackaton.agrt.util.Util;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RoleFragment} interface
 * to handle interaction events.
 * Use the {@link RoleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoleFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText mRoleName;
    Spinner mOrganisation;
    Button mSubmitRole;
    /**
     * Declaring an ArrayAdapter to set items to ListView
     */
    ArrayAdapter<String> adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public RoleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoleFragment newInstance(String param1, String param2) {
        RoleFragment fragment = new RoleFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_role, container, false);
        mRoleName = (EditText) rootView.findViewById(R.id.Role_Name);
        mOrganisation = (Spinner) rootView.findViewById(R.id.Role_Organisation);
        mSubmitRole = (Button) rootView.findViewById(R.id.submit_role);
        mSubmitRole.setOnClickListener(this);
        /** Defining the ArrayAdapter to set items to Spinner Widget */
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, Util.getOrganisationList());
        /** Setting the adapter to the ListView */
        mOrganisation.setAdapter(adapter);
        /** Adding radio buttons for the spinner items */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return rootView;
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
       /* if (context instanceof OnFragmentInteractionListener) {
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
        if (v == mSubmitRole) {
            doSubmit();
        }
    }


    private void doSubmit() {
        final Role role = new Role();
        Organisation organisation = AgtrApplication.getDbHelper().getOrganisationIdByName(mOrganisation.getSelectedItem().toString());
        role.setOrg(organisation);
        role.setRoleName(mRoleName.getText().toString());
        long row = AgtrApplication.getDbHelper().saveRole(role);
        if (row != -1) {
            Toast.makeText(this.getContext(), "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "failure", Toast.LENGTH_SHORT).show();
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
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}