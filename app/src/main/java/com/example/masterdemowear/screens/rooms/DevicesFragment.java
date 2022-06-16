package com.example.masterdemowear.screens.rooms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdemowear.R;
import com.example.masterdemowear.databinding.FragmentDevicesBinding;


public class DevicesFragment extends Fragment {

    FragmentDevicesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDevicesBinding.inflate(inflater, container, false);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_devicesFragment_to_lightFragment);
            }
        };

        binding.light1.setOnClickListener(clickListener);
        binding.light2.setOnClickListener(clickListener);

        return binding.getRoot();
    }
}