package com.food.market.util.HttpUtils;

import java.util.HashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class HttpManger {
    public static Observable<ResponseTemplate<Object>> commitItsm(HashMap<String, Object> optionsMap ) {
        String parameter_controller = "controller";
        String parameter_action = "action";
        String parameter_options = "options";
        HashMap<String, Object> parameterMap = new HashMap<>();
        optionsMap.put("offset", "0");
        optionsMap.put("Request Number", "RQZ000000000000");
        optionsMap.put("offset", "0");
        optionsMap.put("limit", "10");
        parameterMap.put(parameter_controller, "itsmEntryService");
        parameterMap.put(parameter_action, "itsmCreateEntry");
        parameterMap.put(parameter_options, optionsMap);


        Observable<ResponseTemplate<Object>> observable = HttpService.getHttpService().commitItsmList(
                parameterMap
        );

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<ResponseTemplate<Object>>() {
                    @Override
                    public void call(ResponseTemplate<Object> responseTemplate) {
//                        MBOManager.checkCode(responseTemplate);
                    }
                });
    }


}
