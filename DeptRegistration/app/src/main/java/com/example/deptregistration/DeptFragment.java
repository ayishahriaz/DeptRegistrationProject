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

import com.example.deptregistration.databinding.FragmentDeptBinding;


public class DeptFragment extends Fragment {


    public DeptFragment() {
        // Required empty public constructor
    }

    FragmentDeptBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentDeptBinding.inflate(inflater, container, false);
            return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Department");

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancelDept();
            }
        });

        binding.selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedID = binding.radioGroup.getCheckedRadioButtonId();
                String department = "";
                if(selectedID == R.id.radioButtonBio) {
                    department = "Bio Informatics";
                }else if(selectedID == R.id.radioButtonDS) {
                    department = "Data Science";
                }else if(selectedID == R.id.radioButtonSIS) {
                    department = "Software and Info. Systems";
                }else if(selectedID == R.id.radioButtonCS) {
                    department = "Computer Science";
                }
                else {
                    Toast.makeText(getActivity(), "Missing department!!! ", Toast.LENGTH_SHORT).show();
                }
                if (department !=  "") {
                    mListener.sendDept(department);
                }
            }
        });
    }

    DeptFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (DeptFragmentListener) context;
    }

    interface DeptFragmentListener {
        void sendDept(String dept);
        void cancelDept();
    }
}