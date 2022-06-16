package com.example.masterdemowear.screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.masterdemowear.BuildConfig;
import com.example.masterdemowear.R;
import com.example.masterdemowear.Storage;
import com.example.masterdemowear.api.NetworkService;
import com.example.masterdemowear.common.App;
import com.example.masterdemowear.common.Device;
import com.example.masterdemowear.common.KeyDevice;
import com.example.masterdemowear.databinding.FragmentLaunchScreenBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaunchScreenFragment extends Fragment {

    FragmentLaunchScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       binding = FragmentLaunchScreenBinding.inflate(inflater, container, false);

        NetworkService.getInstance()
                .getApi()
                .registerApp(new App(BuildConfig.APPLICATION_ID, "Competitor-1"))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        if (response.code() == 201) {
                            NetworkService
                                    .getInstance()
                                    .getApi()
                                    .registerDevice(new Device(Storage.getInstance(getContext()).getUUID(), BuildConfig.APPLICATION_ID, "Android Wear Emulator"))
                                    .enqueue(new Callback<KeyDevice>() {
                                        @Override
                                        public void onResponse(Call<KeyDevice> call, Response<KeyDevice> response) {
                                            Storage.getInstance(getContext()).setKeyDevice(response.body().getKeyDevice());
                                            String answer = response.body().toString();
                                            Executors.newFixedThreadPool(1).execute(() -> {
                                                getActivity().runOnUiThread
                                                        (() -> {
                                                            Navigation.findNavController(getView()).navigate(R.id.action_launchScreenFragment_to_loginFragment2);
                                                        });
                                            });
                                        }

                                        @Override
                                        public void onFailure(Call<KeyDevice> call, Throwable t) {

                                        }
                                    });
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("APP_REG", t.toString());
                        Toast.makeText(getContext(), "Error register application", Toast.LENGTH_SHORT).show();
                    }
                });

        return binding.getRoot();
    }
}