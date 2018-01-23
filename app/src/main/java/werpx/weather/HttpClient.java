package werpx.weather;

import okhttp3.OkHttpClient;

/**
 * Created by bono on 1/23/2018.
 */
public class HttpClient {
    private static OkHttpClient client;
    private HttpClient(){}
    public static OkHttpClient getInstance(){
        if(client==null)
            client = new OkHttpClient();
        return client;
    }
}
