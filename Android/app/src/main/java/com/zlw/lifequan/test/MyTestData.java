package com.zlw.lifequan.test;

import com.google.common.collect.Lists;
import com.zlw.lifequan.bean.UserInfo;
import com.zlw.lifequan.bean.VCircleBean;

import java.util.List;

/**
 * Created by zlw on 2017/2/7.
 */

public class MyTestData {


    //用户
    public static final String username = "zlw";
    public static final String password = "525252";

    public static String test_img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489544349&di=644e6e0e23daacf7e69903a46bffddc9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg01.taopic.com%2F150822%2F234928-150R215404322.jpg";


    public static UserInfo getUser() {
        return new UserInfo(1, "zlw", "123456", test_img,
                "2011/1/1", "user", "男", "2011/1/1", "北京"
        );
    }

    public static List<VCircleBean> getVCircleList() {
        List<VCircleBean> list = Lists.newArrayList();
        list.add(new VCircleBean(1, "title", "content", "2017/3/5", VCircleBean.VCircleType.TYPE_TEXT,
                new String[]{test_img, "http://img.taopic.com/uploads/allimg/121107/240508-12110H32P739.jpg", test_img}, 1, getUser()));
        list.add(new VCircleBean(1, "title", "content", "2017/3/5", VCircleBean.VCircleType.TYPE_TEXT,
                new String[]{test_img, test_img, "http://img.taopic.com/uploads/allimg/140817/235029-140QFP01152.jpg"}, 1, getUser()));
        list.add(new VCircleBean(1, "title", "content", "2017/3/5", VCircleBean.VCircleType.TYPE_TEXT,
                new String[]{"http://pic.nipic.com/2007-11-20/2007112013324881_2.jpg", "http://www.027art.com/feizhuliu/UploadFiles_6650/201109/2011091800154127.jpg",}, 1, getUser()));
        list.add(new VCircleBean(1, "title", "content", "2017/3/5", VCircleBean.VCircleType.TYPE_TEXT,
                new String[]{test_img}, 1, getUser()));
        list.add(new VCircleBean(1, "title", "content", "2017/3/5", VCircleBean.VCircleType.TYPE_TEXT,
                new String[]{"http://img4.imgtn.bdimg.com/it/u=3541646791,4118302664&fm=23&gp=0.jpg"}, 1, getUser()));
        list.add(new VCircleBean(1, "title", "content", "2017/3/5", VCircleBean.VCircleType.TYPE_TEXT,
                new String[]{}, 1, getUser()));
        return list;
    }

    public static VCircleBean getVCircle() {
        return new VCircleBean(1, "title", "content", "2017/3/5", VCircleBean.VCircleType.TYPE_TEXT,
                new String[]{}, 1, getUser());
    }
}
