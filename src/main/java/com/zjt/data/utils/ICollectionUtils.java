package com.zjt.data.utils;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * Collection 相关的工具类
 *
 * @author zjt
 * @date 2021-09-04
 */
public class ICollectionUtils {

    // collection 空判断
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    // collection 非空判断
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    // map 空判断
    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    // map 非空判断
    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }

}
