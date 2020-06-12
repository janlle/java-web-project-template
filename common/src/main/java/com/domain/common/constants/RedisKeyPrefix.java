package com.domain.common.constants;

/**
 * @author leone
 */
public class RedisKeyPrefix {

    public static String captcha(String uuid) {
        return "captcha:" + uuid;
    }

    public static String index(int protoId) {
        return "index:" + protoId;
    }

}
