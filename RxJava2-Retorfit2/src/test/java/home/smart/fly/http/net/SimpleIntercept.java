package home.smart.fly.http.net;

import androidx.annotation.NonNull;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 08-01-2019
 */
public class SimpleIntercept implements Interceptor {

    private static final String APPLICATION_JSON = "application/json";

    private String expectedResult;
    private int code;

    public SimpleIntercept(@NonNull String expectedResult, int code) {
        this.expectedResult = expectedResult;
        this.code = code;
    }

    @Override
    public Response intercept(Chain chain) {
        Response response = new Response.Builder()
                .code(code)
                .message(expectedResult)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(MediaType.parse(APPLICATION_JSON), expectedResult))
                .addHeader("content-type", APPLICATION_JSON)
                .build();
        return response;
    }
}
