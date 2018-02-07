package com.mop.simple.texteditor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.db.DraftDatabaseUtil;
import com.mop.simple.texteditor.db.entity.DraftData;
import com.mop.simple.texteditor.entity.SimpleTextBean;
import com.mop.simple.texteditor.holder.AbstractRichTextViewHolder;
import com.mop.simple.texteditor.holder.DraftDataViewHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/2.
 */

public class DraftBoxAdapter extends AbstractRichTextViewAdapter<DraftData> {
    @Override
    public AbstractRichTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_draft,
                parent, false);
        return new DraftDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AbstractRichTextViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        DraftData draftData = getItem(position);

        DraftDataViewHolder viewHolder = (DraftDataViewHolder) holder;
        viewHolder.tvTitle.setText(draftData.title);

        ArrayList<SimpleTextBean> list = DraftDatabaseUtil.getContentList(draftData.content);
        SimpleTextBean simpleTextBean = list.get(0);

        viewHolder.tvSummary.setText(simpleTextBean.getContent());
        viewHolder.tvTime.setText(draftData.updateTime);
    }
}
