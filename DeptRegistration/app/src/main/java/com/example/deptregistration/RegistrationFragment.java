package com.example.deptregistration;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.deptregistration.databinding.FragmentRegistrationBinding;


public class RegistrationFragment extends Fragment {

    //set up this variable to be used to send dept in here to mainActivity's "sendDept" method
String selectedDept = null;
public void setSelectedDept(String dept) {
    this.selectedDept = dept;
}
    public RegistrationFragment() {
        // Required empty public constructor
    }


    FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Registration");

        binding.SelectDeptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToDept();
            }
        });

        binding.SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.editTextName.getText().toString();
                String email = binding.editTextEmail.getText().toString();
                String id = binding.editTextID.getText().toString();

                if(name.isEmpty()) {
                    Toast.makeText(getActivity(), "Missing name!!! ", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(getActivity(), "Missing email!!! ", Toast.LENGTH_SHORT).show();
                } else if(id.isEmpty()) {
                    Toast.makeText(getActivity(), "Missing ID!!! ", Toast.LENGTH_SHORT).show();
                } else if(selectedDept == null ) {
                    Toast.makeText(getActivity(), "Missing department!!! ", Toast.LENGTH_SHORT).show();
                } else {
                    Profile profile = new Profile(name, email, id, selectedDept);
                    mListener.goToProfile(profile);
                }
            }
        });
        //clear the text view in the display or set it to the department that was selected
        if(selectedDept == null) {
            binding.textViewSelectedDept.setText("");
        } else {
            binding.textViewSelectedDept.setText(selectedDept);
        }

    }

    RegistrationFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (RegistrationFragmentListener) context;
    }

    interface RegistrationFragmentListener {
        void goToDept();
        void goToProfile(Profile profile);
    }
}