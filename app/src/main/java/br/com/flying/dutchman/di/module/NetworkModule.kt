package br.com.flying.dutchman.di.module

import android.content.Context
import br.com.flying.dutchman.BuildConfig
import br.com.flying.dutchman.data.remote.TmdbApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val DATE_FORMAT = "dd-MM-yyyy'T'HH:mm:ss"
        private const val NAMED_REST_SERVER_URL = "NAMED_REST_SERVER_URL"
        private const val NAMED_INTERCEPTOR_HEADER = "NAMED_INTERCEPTOR_HEADER"
        private const val HEADER_KEY_AUTHORIZATION = "Authorization"
        private const val CACHE_HTTP = "http_cache"
        private const val CACHE_SIZE: Long = 15 * 1024 * 1024
        private const val NAMED_INTERCEPTOR_CACHE_CONTROL = "NAMED_INTERCEPTOR_CACHE_CONTROL"


    }

    @Provides
    @Singleton
    @Named(NAMED_REST_SERVER_URL)
    fun provideRestServer() = BuildConfig.API_SERVER

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
//        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setDateFormat(DATE_FORMAT)
        .disableHtmlEscaping()
        .create()

    @Provides
    @Singleton
    fun provideGSONConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideHttpLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    )

    @Provides
    @Singleton
    @Named(NAMED_INTERCEPTOR_HEADER)
    fun provideHeaderInterceptor(context: Context) = Interceptor { chain ->
        val requestOriginal = chain.request()
        val requestOriginalUrl = requestOriginal.url()

        val newRequest = requestOriginal.newBuilder()
//        newRequest.header(
//            HEADER_KEY_AUTHORIZATION,
//            "token "
//        )
//            .url(requestOriginal.url().newBuilder().build())


        newRequest.url(
            requestOriginalUrl.newBuilder().addQueryParameter(
                "api_key",
                BuildConfig.API_KEY
            ).build()
        )

        return@Interceptor chain.proceed(newRequest.build())
    }

    @Provides
    @Singleton
    @Named(NAMED_INTERCEPTOR_CACHE_CONTROL)
    fun provideCacheControlInterceptor(context: Context) = Interceptor { chain ->

        val request = chain.request()
        val response = chain.proceed(request)

        if (response.cacheResponse() != null && response.cacheControl().isPublic) {
            response

        } else {
            response
                .newBuilder()
                .headers(response.headers())
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideCache(fileCache: File) = Cache(fileCache, CACHE_SIZE)

    @Provides
    @Singleton
    fun provideFileCache(context: Context) = File(context.cacheDir, CACHE_HTTP)


    @Provides
    @Singleton
    fun provideRetrofitRest(
        @Named(NAMED_REST_SERVER_URL) serverUrl: String,
        @Named(NAMED_INTERCEPTOR_HEADER) interceptorHeader: Interceptor,
        @Named(NAMED_INTERCEPTOR_CACHE_CONTROL) cacheControlInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cache: Cache,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(serverUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(gsonConverterFactory)
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptorHeader)
                .addInterceptor(cacheControlInterceptor)
                .cache(cache)
                .connectTimeout(timeout(), TimeUnit.SECONDS)
                .readTimeout(timeout(), TimeUnit.SECONDS)
                .writeTimeout(timeout(), TimeUnit.SECONDS)
                .build()
        )
        .build()

    private fun timeout(): Long = 30

    @Provides
    @Singleton
    fun provideSkyscannerApi(retrofit: Retrofit): TmdbApiService =
        retrofit.create(TmdbApiService::class.java)

}