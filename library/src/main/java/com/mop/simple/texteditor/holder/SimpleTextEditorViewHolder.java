package com.mop.simple.texteditor.holder;

import android.support.text.emoji.widget.EmojiAppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.entity.SimpleTextBean;

/**
 * Created by Administrator on 2018/2/1.
 */

public class SimpleTextEditorViewHolder extends AbstractRichTextViewHolder {

    public EmojiAppCompatEditText edContent;
    private SimpleTextBean simpleTextBean;

    public SimpleTextEditorViewHolder(View itemView) {
        super(itemView);
        edContent = itemView.findViewById(R.id.edContent);
        edContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (simpleTextBean != null) {
                    simpleTextBean.setContent(s.toString());
                }
            }
        });
    }

    public void setSimpleTextBean(SimpleTextBean simpleTextBean) {
        this.simpleTextBean = simpleTextBean;
    }
}
