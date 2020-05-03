package com.example.lowercloudmusic.util.httphelper;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class Httputil {
    private static class Holder{
        private final static Httputil INSTANCE = new Httputil();

    }

    public static Httputil getInstance(){
        return Holder.INSTANCE;
    }

    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime = 30;
    private TimeUnit timeUnit = TimeUnit.MINUTES;
    private ThreadPoolExecutor executor;
    private Httputil() {
        corePoolSize = Runtime.getRuntime().availableProcessors()*2+1;
        maxPoolSize = 30;
        executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                timeUnit,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
    public void execute(final String url, final Callback callBack) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                start(url, callBack);
            }
        });
    }
    public void executefile(final String url, final Callback callBack, final String targetFileAbsPath) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                startfile(url, callBack,targetFileAbsPath);
            }
        });
    }

    private void startfile(String urlStr, Callback callBack, String targetFileAbsPath) {
        int count;
        File targetFile = new File(targetFileAbsPath);
        try {
            boolean n = targetFile.createNewFile();
            Log.d(TAG, "Create new file: " + n + ", " + targetFile);
        } catch (IOException e) {
        }
        try {
            URL url;
            url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.connect();
            int contentLength = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream(targetFileAbsPath);
            byte[] buffer = new byte[1024];
            long total = 0;
            while ((count = input.read(buffer)) != -1) {
                total += count;
                Log.d(TAG, String.format(Locale.CHINA, "Download progress: %.2f%%", 100 * (total / (double) contentLength)));
                output.write(buffer, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
            callBack.onFailed(e);
        }
    }

    private void start(String url, Callback callBack) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL u = new URL(url);
            httpURLConnection = (HttpURLConnection) u.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            //获取响应文本
            callBack.onResponse(sb.toString());
            is.close();
            reader.close();
        } catch (IOException e) {
            callBack.onFailed(e);
        } finally {
            if (null != httpURLConnection) {
                try {
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
