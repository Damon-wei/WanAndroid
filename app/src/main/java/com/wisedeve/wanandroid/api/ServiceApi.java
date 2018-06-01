package com.wisedeve.wanandroid.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.Converter;
import com.lzy.okrx2.adapter.ObservableBody;
import com.wisedeve.wanandroid.model.ResponseData;
import com.wisedeve.wanandroid.model.UserBean;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import okhttp3.Response;

/**
 * Description：玩Android 开放API   http://www.wanandroid.com/blog/show/2
 * Created time：18-6-1 上午10:15
 * author：wisedeve
 * email：wisedeve@163.com
 */
public class ServiceApi {

    public static final String BASE_URL = "http://wanandroid.com/";
    public static final String loginUrl = BASE_URL + "user/login";
    public static final String registerUrl = BASE_URL + "user/register";
    private static Gson gson = new Gson();

    public static Observable<ResponseData<UserBean>> login(String username, String password){
        return OkGo.<ResponseData<UserBean>>post(loginUrl)
                .params("username",username)
                .params("password",password)
                .converter(new Converter<ResponseData<UserBean>>() {
                    @Override
                    public ResponseData<UserBean> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<UserBean>>() {}.getType();
                        return gson.fromJson(response.body().string(),type);
                    }
                })
                .adapt(new ObservableBody<ResponseData<UserBean>>());
    }

    public static Observable<ResponseData<UserBean>> register(String username, String password,String repassword){
        return OkGo.<ResponseData<UserBean>>post(registerUrl)
                .params("username",username)
                .params("password",password)
                .params("repassword",repassword)
                .converter(new Converter<ResponseData<UserBean>>() {
                    @Override
                    public ResponseData<UserBean> convertResponse(Response response) throws Throwable {
                        Type type = new TypeToken<ResponseData<UserBean>>() {}.getType();
                        return gson.fromJson(response.body().string(),type);
                    }
                })
                .adapt(new ObservableBody<>());
    }
}
