package com.mop.simple.texteditor.util;

import com.mop.simple.texteditor.entity.SimpleTextBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ItemUtil {
    public static SimpleTextBean getDefaultRichText(String s) {
        SimpleTextBean simpleTextBean = new SimpleTextBean();
        simpleTextBean.setContent(s);
        simpleTextBean.setType(SimpleTextBean.CONTENT);

        return simpleTextBean;
    }

    public static ArrayList<SimpleTextBean> getDefaultRichTextList(String s) {
        ArrayList<SimpleTextBean> list = new ArrayList<>();
        list.add(getDefaultRichText(s));
        return list;
    }
}
