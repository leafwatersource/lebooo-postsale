package com.api.lebooo.utils;

/**
 * @Date 2022-05-17 14:15
 */
public class ReferenceUtil {

    // ---------->前台<----------

    /**
     * 登录验证码redis存储前缀
     */
    public final static String KEY = "psCode-";
    /**
     *token 存redis存储前缀
     */
    public final static String TOKENKEY = "psToken-";

    /**
     *登录验证码redis存储前缀
     */
    public final static String KEYCODE60 = "psCode60-";

    /**
     *用户注册验证码redis存储前缀
     */
    public final static String KEYCODES60 = "psCodeS60-";

    /**
     *用户注册验证码redis存储前缀
     */
    public final static String KEYS = "psCodeS-";

    /**
     *用户登录token有效期 7天
     */
    public final static int SECONDS = 604800;

    /**
     *60秒内不重复发验证码
     */
    public final static int KEYCODE60_SECONDS = 60;

    /**
     * 短信验证码有效期30分钟
     */
    public final static int KEY_SECONDS = 1800;//60秒内不重复发验证码

    /**
     *手机号码正则
     */
    public static final String MOBILE_PATTERN = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(16[0-9])|(19[0-9]))\\d{8}$";




     // ---------->后台<----------

    /**
     * 后台token 存redis存储前缀
     */
    public final static String TOKENKEYPC = "psPcToken-";

    /**
     *7天
     */
    public final static int SECONDSPC = 604800;

    /**
     * 待寄回状态下，用户未填信息，超时前缀
     */
    public final static String SENDBACKKEY= "PCSendBack-";

    /**
     * 待寄回状态下，用户未填信息，超时时间 15天，Redis存储时间+1天
     */
    public final static int SENDBACK_SECONDS = 1296000+86400;
}
