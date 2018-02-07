package com.mop.simple.texteditor.holder;

import android.support.text.emoji.widget.EmojiAppCompatTextView;
import android.view.View;

import com.mop.simple.texteditor.R;


/**
 * Created by Administrator on 2018/2/1.
 */

public class SimpleTextViewHolder extends AbstractRichTextViewHolder {

    public EmojiAppCompatTextView tvContent;

    public SimpleTextViewHolder(View itemView) {
        super(itemView);
        tvContent = itemView.findViewById(R.id.tvContent);
    }
}
