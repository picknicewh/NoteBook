package com.wh.kotlinnotebook.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/28
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class StoryDetail {
    /**
     * body
     * image_source : meg.dai / CC BY-ND
     * title : 小时候只觉得停电很好玩，却没想过为什么大家会一起停电
     * image : https://pic3.zhimg.com/v2-59b4550c7b511c8f043e4212b2712482.jpg
     * share_url : http://daily.zhihu.com/story/9575063
     * js : []
     * ga_prefix : 081716
     * images : ["https://pic3.zhimg.com/v2-829c441e8d43618d186a4a3ea2aad2f2.jpg"]
     * type : 0
     * id : 9575063
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */
    String body;
    String image_source;
    String title;
    String image;
    String share_url;
    String ga_prefix;
    int type;
    int id;
    List<String> js;
    List<String> images;
    List<String>  css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
