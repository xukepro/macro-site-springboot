package com.xuke.macrosite.common.util;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by xuke on 2020/10/16
 */
public final class HttpClient {

    private static MediaType mediaType = MediaType.parse("application/json");

    public static Response call(OkHttpClient okHttpClient, String params, String url) throws IOException {
        RequestBody requestBody = RequestBody.create(mediaType, params);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        return response;
    }
}