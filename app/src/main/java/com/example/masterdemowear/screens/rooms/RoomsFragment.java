package com.example.masterdemowear.screens.rooms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdemowear.R;
import com.example.masterdemowear.Storage;
import com.example.masterdemowear.api.NetworkService;
import com.example.masterdemowear.common.GetAllRoomsResponse;
import com.example.masterdemowear.databinding.FragmentRoomsFeagmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomsFragment extends Fragment {


    FragmentRoomsFeagmentBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRoomsFeagmentBinding.inflate(inflater, container, false);

        binding.rw.setLayoutManager(new GridLayoutManager(getContext(),2));

        Storage storage = Storage.getInstance(getContext());

        NetworkService
                .getInstance()
                .getApi()
                .getAllRooms(storage.getToken(), storage.getUUID())
                .enqueue(new Callback<GetAllRoomsResponse>() {
                    @Override
                    public void onResponse(Call<GetAllRoomsResponse> call, Response<GetAllRoomsResponse> response) {

                        binding.rw.setAdapter(new RoomsAdapter(response.body().getItems(), getContext()));

                    }

                    @Override
                    public void onFailure(Call<GetAllRoomsResponse> call, Throwable t) {

                    }
                });


        return binding.getRoot();
    }
}