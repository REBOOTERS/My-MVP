package home.smart.fly.proxy;

import android.text.TextUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import home.smart.fly.proxy.annotations.Param;
import home.smart.fly.proxy.annotations.URL;
import home.smart.fly.proxy.impl.Request;
import home.smart.fly.proxy.impl.SimpleNetExecutor;
import home.smart.fly.proxy.interfaces.INetExecutor;
import home.smart.fly.proxy.interfaces.IRequest;

/**
 * @author: Rookie
 * @date: 2018-09-10
 * @desc 自己动动手实现类似 Retrofit 的东东
 */
public class ApiGenerator {
    private static HashMap<Class, Object> sApiCache = new HashMap<>();
    private static INetExecutor sNetExecutor;


    public static <T> T generatorApi(Class<T> apiInterface) {
        if (apiInterface == null || !apiInterface.isInterface()) {
            throw new RuntimeException("big error");
        }
        synchronized (ApiGenerator.class) {
            Object api = sApiCache.get(apiInterface);
            if (api == null) {
                api = Proxy.newProxyInstance(apiInterface.getClassLoader(),
                        new Class[]{apiInterface},
                        new Handler<>(apiInterface));
                sApiCache.put(apiInterface, api);
            }
            return (T) api;
        }
    }

    private static <T> IRequest assembleRequest(Method method, Object[] args, Class<T> apiInterface) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Object> parms = null;

        if (apiInterface.isAnnotationPresent(URL.class)) {
            String url = apiInterface.getAnnotation(URL.class).value();
            if (!TextUtils.isEmpty(url)) {
                stringBuilder.append(url);
            }
        }

        if (method.isAnnotationPresent(URL.class)) {
            String url = method.getAnnotation(URL.class).value();
            if (!TextUtils.isEmpty(url)) {
                stringBuilder.append(url);
            }
        }


        int count = 0;
        for (Annotation[] annotations : method.getParameterAnnotations()) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Param) {
                    String param = ((Param) annotation).value();
                    if (!TextUtils.isEmpty(param)) {
                        if (parms == null) {
                            parms = new HashMap<>();
                        }
                        parms.put(param, args[count]);
                    }
                    break;
                }
            }
            count++;
        }
        return new Request(stringBuilder.toString(), parms, method.getReturnType());
    }

    private static class Handler<T> implements InvocationHandler {

        private Class<T> apiInterface;

        public Handler(Class<T> apiInterface) {
            this.apiInterface = apiInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            IRequest request = assembleRequest(method, args, apiInterface);
            if (sNetExecutor == null) {
                sNetExecutor = new SimpleNetExecutor();
            }
            return sNetExecutor.executor(request);
        }
    }
}
