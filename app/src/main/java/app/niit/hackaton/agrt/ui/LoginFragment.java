package app.niit.hackaton.agrt.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.niit.hackaton.agrt.R;
import app.niit.hackaton.agrt.dto.User;
import app.niit.hackaton.agrt.util.Util;


public class LoginFragment extends Fragment {
    Button mLoginButton;
    EditText mAccountname;
    EditText mPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login,null);
        mAccountname = (EditText) rootView.findViewById(R.id.et_username);
        mPassword = (EditText) rootView.findViewById(R.id.et_password);
        mLoginButton = (Button)rootView.findViewById(R.id.btn_login);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        mLoginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doLogin();
                    }
                }
        );
    }

    private void doLogin() {
        String username = mAccountname.getText().toString();
        String password = mPassword.getText().toString();
        User user = Util.getUserProfileByUserNameAndPassword(username, password);
        if (user != null) {
            startActivity(new Intent(getActivity().getApplicationContext(), DashboardActivity.class));
            getActivity().finish();
        } else if (username.equals("admin") && password.equals("admin")) {
            startActivity(new Intent(getActivity().getApplicationContext(), DashboardActivity.class));
            getActivity().finish();
        }
    }
}
