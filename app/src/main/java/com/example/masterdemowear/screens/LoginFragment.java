package com.example.masterdemowear.screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.masterdemowear.R;
import com.example.masterdemowear.Storage;
import com.example.masterdemowear.api.NetworkService;
import com.example.masterdemowear.common.UserLoginAnswer;
import com.example.masterdemowear.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.DigestUtils;

import org.apache.commons.validator.routines.EmailValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;

    Storage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        storage = Storage.getInstance(getContext());

        binding.btn.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            if (EmailValidator.getInstance().isValid(email) && !password.equals("")) {
                NetworkService
                        .getInstance()
                        .getApi()
                        .loginUser(email, password, storage.getUUID())
                        .enqueue(new Callback<UserLoginAnswer>() {
                            @Override
                            public void onResponse(Call<UserLoginAnswer> call, Response<UserLoginAnswer> response) {
                                if (response.code() == 201) {
                                    Storage.getInstance(getContext()).setToken(response.body().getToken());
                                    Navigation.findNavController(view).navigate(R.id.action_loginFragment2_to_roomsFeagment);
                                }
                                else {
                                    Toast.makeText(getContext(), "Неверный email или пароль", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UserLoginAnswer> call, Throwable t) {

                            }
                        });
            }
        });


        return binding.getRoot();
    }
}