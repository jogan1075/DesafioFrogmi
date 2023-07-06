package com.jmc.desafiofrogmi.di


import com.jmc.desafiofrogmi.BuildConfig
import com.jmc.desafiofrogmi.BuildConfig.AUTHORIZATION
import com.jmc.desafiofrogmi.data.DataRepository
import com.jmc.desafiofrogmi.data.remote.RemoteImpl
import com.jmc.desafiofrogmi.data.remote.service.ApiService
import com.jmc.desafiofrogmi.data.repository.Remote
import com.jmc.desafiofrogmi.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(500, TimeUnit.MILLISECONDS)
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $AUTHORIZATION")
                    .addHeader("X-Company-Uuid", BuildConfig.XCOMPANYUUID)
                    .build()
                chain.proceed(request)
            }).build()


        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.frogmi.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    internal fun provideRemoteImpl(remoteImpl: RemoteImpl): Remote = remoteImpl

    @Provides
    internal fun bindDataRepository(dataRepository: DataRepository): Repository =
        dataRepository



}