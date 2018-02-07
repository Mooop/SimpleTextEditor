package com.mop.simple.texteditor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.entity.SimpleTextBean;
import com.mop.simple.texteditor.holder.AbstractRichTextViewHolder;
import com.mop.simple.texteditor.holder.SimpleTextImageViewHolder;
import com.mop.simple.texteditor.holder.SimpleTextViewHolder;
import com.mop.simple.texteditor.util.ImageUtil;

/**
 * Created by Administrator on 2018/2/1.
 */

public class SimpleTextViewAdapter extends AbstractRichTextViewAdapter<SimpleTextBean> {

    @Override
    public AbstractRichTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        switch (viewType) {
            case SimpleTextBean.CONTENT:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.view_simple_text, parent, false);
                return new SimpleTextViewHolder(view);

            case SimpleTextBean.IMAGE:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.view_simple_image, parent, false);
                return new SimpleTextImageViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(AbstractRichTextViewHolder holder, int position) {

        if (holder == null) {
            return;
        }

        Context context = holder.itemView.getContext();

        int type = getItemViewType(position);
        SimpleTextBean bean = getItem(position);

        switch (type) {
            case SimpleTextBean.CONTENT:
                SimpleTextViewHolder richTextViewHolder = (SimpleTextViewHolder) holder;
                richTextViewHolder.tvContent.setText(bean.getContent());
                break;

            case SimpleTextBean.IMAGE:
                SimpleTextImageViewHolder richImageViewHolder = (SimpleTextImageViewHolder) holder;
                ImageUtil.loadIntoUseFitWidth(context, bean.getImgUrl(), R.mipmap.ic_picture,
                        richImageViewHolder.img);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        SimpleTextBean simpleTextBean = getItem(position);
        int type;

        switch (simpleTextBean.getType()) {
            case SimpleTextBean.CONTENT:
                type = SimpleTextBean.CONTENT;
                break;
            case SimpleTextBean.IMAGE:
                type = SimpleTextBean.IMAGE;
                break;
            default:
                type = 0;
                break;
        }
        return type;
    }
}
