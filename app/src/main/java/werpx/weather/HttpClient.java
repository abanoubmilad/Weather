package werpx.weather;

import okhttp3.OkHttpClient;

public class HttpClient {
    private static OkHttpClient client;
    private HttpClient(){}
    public static OkHttpClient getInstance(){
        if(client==null)
            client = new OkHttpClient();
        return client;
    }
}
