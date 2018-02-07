package com.mop.simple.texteditor.holder;

import android.view.View;
import android.widget.ImageView;

import com.mop.simple.texteditor.R;


/**
 * Created by Administrator on 2018/2/1.
 */

public class SimpleTextImageViewHolder extends AbstractRichTextViewHolder {

    public ImageView img;
    public ImageView imgRemove;

    public SimpleTextImageViewHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        imgRemove = itemView.findViewById(R.id.imgRemove);
    }
}
