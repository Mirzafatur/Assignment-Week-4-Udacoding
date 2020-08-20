package com.example.mahasiswaappmirzafaturrachman.network

import com.example.mahasiswaappmirzafaturrachman.model.action.ResponseAction
import com.example.mahasiswaappmirzafaturrachman.model.data.ResponseMahasiswa
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //getData
    @GET("getData.php")
    fun getData(): Call<ResponseMahasiswa>

    //getDataById
    @GET("getData.php")
    fun getDataById(@Query("id") id: String) : Call<ResponseMahasiswa>

    //insert
    @FormUrlEncoded
    @POST("insertData.php")
    fun insertData( @Field("nama") nama: String,
                    @Field("nohp") nohp: String,
                    @Field("alamat") alamat: String) : Call<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("updateData.php")
    fun updateData( @Field("id") id: String,
                    @Field("nama") nama: String,
                    @Field("nohp") nohp: String,
                    @Field("alamat") alamat: String) : Call<ResponseAction>

    @FormUrlEncoded
    @POST("deleteData.php")
    fun deleteData(@Field("id") id: String) : Call<ResponseAction>

}