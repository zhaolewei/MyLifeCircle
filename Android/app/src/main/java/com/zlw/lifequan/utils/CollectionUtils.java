package com.zlw.lifequan.utils;

import java.util.Collection;

/**
 * Created by xiaoming on 16/8/29.
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection collection) {
        if (collection == null) {
            return true;
        }
        return collection.isEmpty();
    }
}
