package br.com.example.appmovieinfo.di

import br.com.example.appmovieinfo.model.MovieFullCastHttp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModuleFullCast {
    @Provides
    @Singleton
    @Named("Full")
    fun providerRetrofitFullCast(): MovieFullCastHttp {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client_logging: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.URL_IMDB)
            .client(client_logging)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieFullCastHttp::class.java)
    }

}