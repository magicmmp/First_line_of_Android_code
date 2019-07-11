package com.example.download_use_service_10_6;

/**
 * Created by pss on 2019/7/11.
 */

public interface DownloadListener
{
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
