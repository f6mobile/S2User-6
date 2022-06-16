package com.example.masterdemowear.api;


import com.example.masterdemowear.common.App;
import com.example.masterdemowear.common.Device;
import com.example.masterdemowear.common.GetAllRoomsResponse;
import com.example.masterdemowear.common.KeyDevice;
import com.example.masterdemowear.common.UserAnswer;
import com.example.masterdemowear.common.UserLoginAnswer;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SmartHomeAPI {
    @POST("/app")
    Call<String> registerApp(@Body App app);

    @POST("/mobile")
    Call<KeyDevice> registerDevice(@Body Device device);
    @PATCH("/mobile")
    Call<KeyDevice> updateKey(@Query("uuid") String uuid);

    @POST("/user")
    Call<UserAnswer> regUser(@Header("email") String email,
                             @Header("name") String name,
                             @Header("password") String password,
                             @Header("uuid") String uuid,
                             @Header("hash") String hash
    );
    @OPTIONS("/user")
    Call<UserLoginAnswer> loginUser(@Header("email") String email,
                                    @Header("password") String password,
                                    @Header("uuid") String uuid
    );

    @GET("/rooms")
    Call<GetAllRoomsResponse> getAllRooms(@Header("token") String token,
                                          @Header("uuid") String uuid);

    @POST("/rooms")
    Call<String> addRoom(@Header("name") String name,
                         @Header("type") String type,
                         @Header("token") String token,
                         @Header("uuid") String uuid,
                         @Header("hash") String hash
    );

}
