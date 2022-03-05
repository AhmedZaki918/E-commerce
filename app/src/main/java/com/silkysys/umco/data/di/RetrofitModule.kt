package com.silkysys.umco.data.di

import com.silkysys.umco.data.local.Constants
import com.silkysys.umco.data.local.UserPreferences
import com.silkysys.umco.data.network.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideClient(userPreferences: UserPreferences): APIService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(80, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header(
                        "Authorization",
                        "Bearer ${userPreferences.read(Constants.USER_TOKEN) ?: ""}"
                    )
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }
            .build()

        // Build a retrofit
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}