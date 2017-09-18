package com.wh.kotlinnotebook.bean;

/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyWordVo {

    /**
     * id : 3966
     * type : wisdom
     * title : 每日一语
     * content : 希望你做的每一个决定都是为了“你想做什么”而不是“你应该做什么”。
     * date : 20170824
     * tpl : 0
     * image_small : http://img.fenfenriji.com/5F/7E/4E/Image/26A949DA-5E81-F51C-F1E5-599BE6A95A69.jpg
     * image_large : http://img.fenfenriji.com/5F/7E/4E/Image/ACC4A6F1-64AB-C44D-75EB-599BE63BDEFF.jpg
     * link : http://d.fenfenriji.com/web/act/motto/s_20170824.html
     * action : http://d.fenfenriji.com/web/act/motto/20170824.html
     * extend : {}
     */

    private int id;
    private String type;
    private String title;
    private String content;
    private String date;
    private int tpl;
    private String image_small;
    private String image_large;
    private String link;
    private String action;
    private ExtendBean extend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTpl() {
        return tpl;
    }

    public void setTpl(int tpl) {
        this.tpl = tpl;
    }

    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    public String getImage_large() {
        return image_large;
    }

    public void setImage_large(String image_large) {
        this.image_large = image_large;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ExtendBean getExtend() {
        return extend;
    }

    public void setExtend(ExtendBean extend) {
        this.extend = extend;
    }

    public static class ExtendBean {
    }
}
