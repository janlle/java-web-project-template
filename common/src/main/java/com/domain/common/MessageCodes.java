package com.domain.common;


public interface MessageCodes {

    String AUTH_TOKEN = "auth.token.wrong";//token错误或已过期,请重新登录
    String AUTH_TOKEN_EMPTY = "auth.token.empty";//token为空
    String AUTH_ACCOUNT_PASSWORD_WRONG = "auth.account.password.wrong";//账号或密码错误
    String ACCOUNT_HAS_DISABLED = "account.has.disabled";//账号已被禁用
    String AUTH_PERMISSION = "auth.permission";//权限不足
    String REQUEST_ARGUMENT = "request.argument";//请求参数错误或者参数为空
    String INTERNAL_SERVER_ERROR = "server.internal";//未知错误
    String ACCOUNT_ALREADY_EXIST = "account.already.exist";//账号已经存在


}
