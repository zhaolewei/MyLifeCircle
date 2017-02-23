package com.zlw.lifequan.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;


/**
 */
public class JsonUtil {


    public static final JSONObject toJSONObject(Object object) {
        if (null != object) {
            try {
                return JSON.parseObject(JSON.toJSONString(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final String toJSONString(Object object) {
        if (null != object) {
            try {
                return JSON.toJSONString(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final String toPrerryJSONString(Object object) {
        if (null != object) {
            try {
                return JSON.toJSONString(object,
                        SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullNumberAsZero,
                        SerializerFeature.WriteEnumUsingToString,
                        SerializerFeature.WriteNullBooleanAsFalse,
                        SerializerFeature.WriteNullListAsEmpty,
                        SerializerFeature.WriteNullStringAsEmpty,
                        SerializerFeature.WriteMapNullValue
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static final JSONObject parseObject(String text) {
        if (!TextUtils.isEmpty(text)) {
            try {
                return JSON.parseObject(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final <T> T parseObject(String text, Class<T> clazz) {
        if (!TextUtils.isEmpty(text)) {
            try {
                return JSON.parseObject(text, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static final <T> T parseObject(String text, TypeReference<T> type) {
        if (!TextUtils.isEmpty(text)) {
            try {
                return JSON.parseObject(text, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final JSONArray parseArray(String text) {
        if (!TextUtils.isEmpty(text)) {
            try {
                return JSON.parseArray(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final <T> List<T> parseArray(String text, Class<T> clazz) {
        if (!TextUtils.isEmpty(text)) {
            try {
                return JSON.parseArray(text, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //===========================================================

    public static final int getIntValue(JSONObject object, String key) {
        if (null != object && !TextUtils.isEmpty(key)) {
            try {
                return object.getIntValue(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static final long getLongValue(JSONObject object, String key) {
        if (null != object && !TextUtils.isEmpty(key)) {
            try {
                return object.getLongValue(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0L;
    }

    public static final boolean getBooleanValue(JSONObject object, String key) {
        if (null != object && !TextUtils.isEmpty(key)) {
            try {
                return object.getBooleanValue(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static final String getStringValue(JSONObject object, String key) {
        if (null != object && !TextUtils.isEmpty(key)) {
            try {
                return object.getString(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final JSONObject getJSONObject(JSONObject object, String key) {
        if (null != object && !TextUtils.isEmpty(key)) {
            try {
                return object.getJSONObject(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static final JSONArray getJSONArray(JSONObject object, String key) {
        if (null != object && !TextUtils.isEmpty(key)) {
            try {
                return object.getJSONArray(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}

