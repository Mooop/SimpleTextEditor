package com.mop.simple.texteditor.util;

import com.mop.simple.texteditor.entity.SimpleTextBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/2/5.
 */

public class SimpleTextViewUtil {

    public static String filterImgLabel(String content) {
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        //匹配img标签的正则表达式
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(content);
        content = m_html.replaceAll("[图片]"); // 过滤html标签
        return content;
    }

    public static ArrayList<SimpleTextBean> convertList(List<String> strings) {

        ArrayList<SimpleTextBean> list = new ArrayList<>();
        Pattern pattern = Pattern.compile("<img.*?src=\\\"(.*?)\\\".*?>");

        for (String targetStr : strings) {
            SimpleTextBean simpleTextBean = new SimpleTextBean();
            Matcher matcher = pattern.matcher(targetStr);
            if (matcher.find()) {
                targetStr = getHtmlImageSrcList(targetStr).get(0);
                simpleTextBean.setType(SimpleTextBean.IMAGE);
                simpleTextBean.setImgUrl(targetStr);
            } else {
                simpleTextBean.setType(SimpleTextBean.CONTENT);
                simpleTextBean.setContent(targetStr);
            }
            list.add(simpleTextBean);
        }
        return list;
    }

    /**
     * 获取HTML文件里面的IMG标签的SRC地址
     *
     * @param htmlText 带html格式的文本
     */
    public static List<String> getHtmlImageSrcList(String htmlText) {
        List<String> imgSrc = new ArrayList<String>();
        Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(htmlText);
        while (m.find()) {
            imgSrc.add(m.group(1));
        }
        return imgSrc;
    }
}
