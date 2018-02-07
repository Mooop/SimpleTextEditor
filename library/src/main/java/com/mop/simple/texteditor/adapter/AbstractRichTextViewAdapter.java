package com.mop.simple.texteditor.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mop.simple.texteditor.holder.AbstractRichTextViewHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/1.
 */

public abstract class AbstractRichTextViewAdapter<T> extends RecyclerView.Adapter<AbstractRichTextViewHolder> {

    protected ArrayList<T> datas;
    private OnItemClickListener onItemClickListener;

    public void addDatas(ArrayList<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public ArrayList<T> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(AbstractRichTextViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.itemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public T getItem(int position) {
        return datas.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void itemClick(View view, int position);
    }
}
