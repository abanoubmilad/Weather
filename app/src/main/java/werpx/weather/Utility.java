package werpx.weather;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class Utility {
    public static final String FORECAST_DATE_FORMAT="EEE yyyy/MM/dd";
    public static final String LAST_UPDATED_DATE_FORMAT="EEE yyyy/MM/dd HH:mm";

    public static  boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static void openContactUsWebPage(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://werpx.com/en/contact"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
