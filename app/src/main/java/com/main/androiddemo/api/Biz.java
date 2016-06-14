package com.main.androiddemo.api;

import com.main.androiddemo.configs.ConfigServer;

import java.util.HashMap;

/**
 * <br/> Description:所有的网络请求
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2016/6/14 0014
 * <br/> @Copyright: Copyright (c) 2016 Shenzhen Duidian Technology Co., Ltd. All rights reserved.
 */
public class Biz {

    public static HashMap<String, String> getPostHeadMap(String method) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(ConfigServer.SERVER_METHOD_KEY, method);
        map.put(ConfigServer.SERVER_TYPE_KEY, ConfigServer.SERVER_TYPE_VALUE);//app_key 10
        map.put(ConfigServer.SERVER_VESRTION_KEY, ConfigServer.SERVER_VESRTION_VAULE);//v 1.0
        //		map.put(ConfigServer.SERVER_UPDATE_KEY, ConfigServer.SERVER_UPDATE_VAULE);//app_v 2.0
        return map;
    }


}
