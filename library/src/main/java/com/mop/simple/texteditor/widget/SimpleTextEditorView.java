package com.mop.simple.texteditor.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.blankj.utilcode.utils.ToastUtils;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.adapter.SimpleTextEditorAdapter;
import com.mop.simple.texteditor.db.DraftDatabaseUtil;
import com.mop.simple.texteditor.db.entity.DraftData;
import com.mop.simple.texteditor.entity.SimpleTextBean;
import com.mop.simple.texteditor.util.ItemUtil;
import com.mop.simple.texteditor.view.SimpleTextViewPreviewActivity;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by Administrator on 2018/2/2.
 */

public class SimpleTextEditorView extends FrameLayout {

    private EditText edTitle;
    private XRecyclerView xRecyclerView;
    private ImageView img;
    private ImageView imgSave;
    private ImageView imgPreview;
    private SimpleTextEditorAdapter editorAdapter;

    public SimpleTextEditorView(@NonNull Context context) {
        super(context);
        init();
    }

    public SimpleTextEditorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext())
                .inflate(R.layout.view_simple_texteditor, this, true);

        edTitle = findViewById(R.id.edTitle);
        xRecyclerView = findViewById(R.id.xRecyclerView);
        img = findViewById(R.id.img);
        imgSave = findViewById(R.id.imgSave);
        imgPreview = findViewById(R.id.imgPreview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setPullRefreshEnabled(false);
        xRecyclerView.setLoadingMoreEnabled(false);

        editorAdapter = new SimpleTextEditorAdapter();
        xRecyclerView.setAdapter(editorAdapter);
        editorAdapter.setDatas(ItemUtil.getDefaultRichTextList(""));

        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(3)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start((Activity) getContext(), PhotoPicker.REQUEST_CODE);
            }
        });

        imgSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = edTitle.getText().toString();
                String content = DraftDatabaseUtil.content2String(editorAdapter.getDatas());
                String userName = "";
                String userId = "";
                DraftDatabaseUtil.saveDraft(0, title, content, userName, userId);
                ToastUtils.showShortToast(v.getContext(), "保存成功");
            }
        });

        imgPreview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DraftData draftData = new DraftData();
                draftData.title = edTitle.getText().toString();
                draftData.content = new Gson().toJson(refreshData(editorAdapter.getDatas()));

                Intent intent = new Intent(v.getContext(), SimpleTextViewPreviewActivity.class);
                intent.putExtra("data", draftData);
                v.getContext().startActivity(intent);
            }
        });
    }

    private ArrayList<SimpleTextBean> refreshData(ArrayList<SimpleTextBean> simpleTextBeans) {
        ArrayList<SimpleTextBean> list = new ArrayList<>();
        for (SimpleTextBean bean : simpleTextBeans) {
            switch (bean.getType()) {
                case SimpleTextBean.CONTENT:
                    if (TextUtils.isEmpty(bean.getContent())) {
                        list.add(bean);
                    }
                    break;
                default:
                    break;
            }
        }
        simpleTextBeans.removeAll(list);
        return simpleTextBeans;
    }

    public void setData(ArrayList<SimpleTextBean> data) {
        editorAdapter.setDatas(data);
    }

    public void addData(ArrayList<SimpleTextBean> data) {
        if (editorAdapter.getItemCount() == 1) {
            if (TextUtils.isEmpty(editorAdapter.getItem(0).getContent())) {
                editorAdapter.getDatas().clear();
            }
        }
        editorAdapter.addDatas(data);
    }

    public boolean isFilled() {
        return (!TextUtils.isEmpty(edTitle.getText().toString()))
                && (editorAdapter.getDatas().size() > 0);
    }

    public void scrollToPosition(final int position) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                xRecyclerView.scrollToPosition(position);
            }
        }, 300);
    }

    public void setImgOnClickListener(OnClickListener onClickListener) {
        img.setOnClickListener(onClickListener);
    }

    public void setImgSaveOnClickListener(OnClickListener onClickListener) {
        imgSave.setOnClickListener(onClickListener);
    }
}
