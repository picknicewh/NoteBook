package com.wh.kotlinnotebook.common;

/**
 * 作者： wh
 * 时间：  2017/8/21
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class Consist {
    public static final int HOME = 0;
    public static final int SECRET = 1;
    public static final int INFROMATION = 2;
    public static final int ABOUT = 3;

    /**进入日历详情来自----》首页的每日一语*/
    public static final  int FROM_HOME=0;
    /**进入日历详情来自----》首页的每日必读*/
    public static final  int FROM_HOME_READ=3;
    /**进入日历详情来自----》每日一语列表页*/
    public static final int FROM_DETIAL_LIST= 1;

    /*** 添加请求*/
    public static final int REQUEST_ADD=0x01;
    /*** 编辑请求*/
    public static final int REQUEST_EDIT=0x02;
}
