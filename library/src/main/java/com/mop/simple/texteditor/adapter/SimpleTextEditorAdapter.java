package com.mop.simple.texteditor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.blankj.utilcode.utils.ScreenUtils;
import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.entity.SimpleTextBean;
import com.mop.simple.texteditor.holder.AbstractRichTextViewHolder;
import com.mop.simple.texteditor.holder.SimpleTextEditorViewHolder;
import com.mop.simple.texteditor.holder.SimpleTextImageViewHolder;
import com.mop.simple.texteditor.util.ImageUtil;

/**
 * Created by Administrator on 2018/2/1.
 */

public class SimpleTextEditorAdapter extends AbstractRichTextViewAdapter<SimpleTextBean> {

    private OnImageDeleteListener onImageDeleteListener;

    @Override
    public AbstractRichTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        switch (viewType) {
            case SimpleTextBean.CONTENT:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.view_simple_text_editor, parent, false);
                return new SimpleTextEditorViewHolder(view);

            case SimpleTextBean.IMAGE:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.view_simple_image, parent, false);

                SimpleTextImageViewHolder textImageViewHolder = new SimpleTextImageViewHolder(view);

                int width = ScreenUtils.getScreenWidth(context);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width / 3, width / 3);
                textImageViewHolder.itemView.setLayoutParams(params);
//                textImageViewHolder.img.setLayoutParams(params);
                textImageViewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return textImageViewHolder;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public void onBindViewHolder(AbstractRichTextViewHolder holder, final int position) {

        if (holder == null) {
            return;
        }

        final Context context = holder.itemView.getContext();
        int type = getItemViewType(position);
        final SimpleTextBean bean = getItem(position);

        switch (type) {
            case SimpleTextBean.CONTENT:
                SimpleTextEditorViewHolder textEditorViewHolder = (SimpleTextEditorViewHolder) holder;
                textEditorViewHolder.setSimpleTextBean(bean);
                textEditorViewHolder.edContent.setText(bean.getContent());
                break;

            case SimpleTextBean.IMAGE:
                final SimpleTextImageViewHolder richImageViewHolder = (SimpleTextImageViewHolder) holder;
                richImageViewHolder.imgRemove.setVisibility(View.VISIBLE);
                richImageViewHolder.imgRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onImageDeleteListener != null) {
                            onImageDeleteListener.imageDelete(v, position);
                        }
                        getDatas().remove(position);
                        notifyDataSetChanged();
                    }
                });

                ImageUtil.loadImg(context, bean.getImgUrl(), R.mipmap.ic_picture,
                        richImageViewHolder.img);
                break;
            default:
                break;
        }
    }

    private void refreshData() {

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

    public void setOnImageDeleteListener(OnImageDeleteListener onImageDeleteListener) {
        this.onImageDeleteListener = onImageDeleteListener;
    }

    public interface OnImageDeleteListener {
        void imageDelete(View view, int position);
    }
}
