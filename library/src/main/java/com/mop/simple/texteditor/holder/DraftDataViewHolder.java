package com.mop.simple.texteditor.holder;

import android.support.text.emoji.widget.EmojiAppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.mop.simple.texteditor.R;


/**
 * Created by Administrator on 2018/2/5.
 */

public class DraftDataViewHolder extends AbstractRichTextViewHolder {

    public EmojiAppCompatTextView tvTitle;
    public EmojiAppCompatTextView tvSummary;
    public TextView tvTime;

    public DraftDataViewHolder(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvSummary = itemView.findViewById(R.id.tvSummary);
        tvTime = itemView.findViewById(R.id.tvTime);
    }
}
