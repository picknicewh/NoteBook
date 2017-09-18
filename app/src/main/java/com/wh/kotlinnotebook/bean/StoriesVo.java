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
public class StoriesVo  {
    /**
     * type : 0
     * id : 7399166
     * title : 粤语究竟是「语言」还是「方言」？
     * images : ["http://pic3.zhimg.com/ca912fc8405360ada01a61f7c78e474a.jpg"]
     * multipic : true
     */
    private int  type;
    private  int id;
    private String  title;
    private boolean isMultipic;
    private List<String> images;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMultipic() {
        return isMultipic;
    }

    public void setMultipic(boolean multipic) {
        isMultipic = multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
