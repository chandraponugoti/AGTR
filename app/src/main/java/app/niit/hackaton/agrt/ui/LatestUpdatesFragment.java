package app.niit.hackaton.agrt.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.adapter.CustomAdapter;
import app.niit.hackaton.agrt.dto.Asset;
import app.niit.hackaton.agrt.dto.AssetRegister;
import app.niit.hackaton.agrt.util.Util;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LatestUpdatesFragment} interface
 * to handle interaction events.
 * Use the {@link LatestUpdatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LatestUpdatesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView mAssetView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;

    public LatestUpdatesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LatestUpdatesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LatestUpdatesFragment newInstance(String param1, String param2) {
        LatestUpdatesFragment fragment = new LatestUpdatesFragment();
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
        View viewRoot = inflater.inflate(R.layout.fragment_latest_updates, container, false);
        mAssetView = (ListView) viewRoot.findViewById(R.id.assets);
        mAssetView.setAdapter(new CustomAdapter(this.getContext(), getAssets()));
        return viewRoot;
    }

    public List<AssetRegister> getAssets() {
        if (Util.getAssetRegistryList() != null && !Util.getAssetRegistryList().isEmpty()) {
            return Util.getAssetRegistryList();
        } else {
            List assets = new ArrayList();
            AssetRegister ar = new AssetRegister();
            Asset a = new Asset();
            a.setAssetName("mobile");
            a.setAssetOwner("tom");
            ar.setLocation("abcd");
            ar.setAsset(a);
            AssetRegister ar1 = new AssetRegister();
            Asset a1 = new Asset();
            a1.setAssetName("tab");
            a1.setAssetOwner("hank");
            ar1.setLocation("gpsde");
            ar1.setAsset(a1);
            AssetRegister ar2 = new AssetRegister();
            Asset a2 = new Asset();
            a2.setAssetName("tv");
            a2.setAssetOwner("gim");
            ar2.setLocation("hishdhilsodjaodsjoad");
            ar2.setAsset(a2);
            assets.add(ar);
            assets.add(ar1);
            assets.add(ar2);
            return assets;
        }
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
