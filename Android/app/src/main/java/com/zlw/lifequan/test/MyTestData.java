package com.zlw.lifequan.test;

import com.google.common.collect.Lists;
import com.zlw.lifequan.bean.VCircleBean;

import java.util.List;

/**
 * Created by zlw on 2017/2/7.
 */

public class MyTestData {

    public static List<VCircleBean> list = Lists.newArrayList();


    public static List<VCircleBean> getVCircleList() {
        list.add(new VCircleBean(1, "1", "1"));
        list.add(new VCircleBean(2, "2", "2"));
        list.add(new VCircleBean(3, "3", "3"));
        list.add(new VCircleBean(4, "4", "4"));
        return list;
    }
}
