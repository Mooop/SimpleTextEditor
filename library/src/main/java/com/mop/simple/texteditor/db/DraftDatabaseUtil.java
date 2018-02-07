package com.mop.simple.texteditor.db;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.blankj.utilcode.utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mop.simple.texteditor.db.entity.DraftData;
import com.mop.simple.texteditor.entity.SimpleTextBean;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */

public class DraftDatabaseUtil {

    public static <TModel> long insertSinle(TModel t) {
        Class<TModel> tModelClass = (Class<TModel>) t.getClass();
        ModelAdapter<TModel> dataModelAdapter = FlowManager.getModelAdapter(tModelClass);
        return dataModelAdapter.insert(t);//插入
    }

    public static <TModel> List<TModel> getResultList(Class<TModel> tModelClass) {
        List<TModel> tModelList = SQLite.select().from(tModelClass).queryList();
        return tModelList;
    }

    public static void saveDraft(int type, String title, String content, String userName, String userId) {
        DraftData userData = new DraftData();

        userData.id = 0;
        userData.title = title;
        userData.content = content;
        userData.userName = userName;
        userData.userId = userId;
        userData.type = 0;
        userData.createTime = TimeUtils.date2String(new Date());
        userData.updateTime = TimeUtils.date2String(new Date());

        DraftDatabaseUtil.insertSinle(userData);
    }

    public static String content2String(ArrayList<SimpleTextBean> simpleTextBeans) {
        String content = new Gson().toJson(simpleTextBeans);
        return content;
    }

    public static ArrayList<SimpleTextBean> getContentList(String content) {
        ArrayList<SimpleTextBean> list = new Gson().fromJson(content,
                new TypeToken<ArrayList<SimpleTextBean>>() {
                }.getType());

        return list;
    }

    @NonNull
    public static StringBuilder formatHtml(ArrayList<SimpleTextBean> simpleTextBeans) {
        StringBuilder builder = new StringBuilder();

        for (SimpleTextBean richText : simpleTextBeans) {

            switch (richText.getType()) {
                case SimpleTextBean.CONTENT:
                    String content = richText.getContent();
                    if (!TextUtils.isEmpty(content)) {
                        content = content.replace("\n", "<br>");
                    }
                    builder.append(content);
                    break;
                case SimpleTextBean.IMAGE:
                    builder.append("<img src=\"");
                    builder.append(richText.getImgUrl());
                    builder.append("\"/>");
                    break;
                default:
                    break;
            }
        }
        return builder;
    }
}
