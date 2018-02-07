package com.mop.simple.texteditor.entity;

/**
 * Created by Administrator on 2018/2/1.
 */

public class SimpleTextBean {

    public static final int CONTENT = 0;
    public static final int IMAGE = 1;

    /**
     * 内容
     */
    private String content;
    /**
     * 图片路径
     */
    private String imgUrl;
    /**
     * 类型 0：文字 1：图片
     */
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
