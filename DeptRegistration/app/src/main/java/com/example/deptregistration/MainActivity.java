package com.example.deptregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener, RegistrationFragment.RegistrationFragmentListener, DeptFragment.DeptFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new MainFragment())
                .commit();
    }

    @Override
    public void goToRegistration() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new RegistrationFragment(), "registration-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToDept() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new DeptFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToProfile(Profile profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendDept(String dept) {
        //find registration fragment from backstack
        RegistrationFragment fragment = (RegistrationFragment) getSupportFragmentManager().findFragmentByTag("registration-fragment");
        //send selected dept to registration fragment
        if(fragment != null) {
            fragment.setSelectedDept(dept);
        }
        //pop the back stack
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelDept() {
        getSupportFragmentManager().popBackStack(); //go back to previous fragment
    }
}