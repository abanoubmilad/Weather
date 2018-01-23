package werpx.weather;


public interface CustomCallback {
    void onFailure(String failureMessage);

    void onSuccess(Object result);
}

