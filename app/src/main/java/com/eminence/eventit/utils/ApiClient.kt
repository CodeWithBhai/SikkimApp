package com.eminence.eventit.utils


import com.eminence.eventit.interfaces.ServiceInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit_user: Retrofit? = null
    private var retrofit_partner: Retrofit? = null

    fun getRetrofitUser(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit_user = Retrofit.Builder()
            .baseUrl(Constants.USERBASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return retrofit_user
    }

    fun getRetrofitPartner(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit_partner = Retrofit.Builder()
            .baseUrl(Constants.PARTNERBASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return retrofit_partner
    }

    val instence_user: ServiceInterface? = getRetrofitUser()?.create(ServiceInterface::class.java)
   // val instence_partner: PartnerServiceInterface = getRetrofitPartner()!!.create(PartnerServiceInterface::class.java)

}