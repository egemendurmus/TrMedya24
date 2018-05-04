package egemen.trmedya24.api;

import java.util.concurrent.TimeUnit;

import egemen.trmedya24.AppGlobals;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = AppGlobals.URL;
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

       //todo servis çalışmadığı için 2sn verdim
        OkHttpClient.Builder clients = new OkHttpClient.Builder();
        clients.addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(2, TimeUnit.SECONDS)
                .writeTimeout(2, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clients.build())
                .build();


        return retrofit;
    }
}