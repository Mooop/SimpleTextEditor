package com.mop.simple.texteditor.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.entity.SimpleTextBean;
import com.mop.simple.texteditor.util.ItemUtil;
import com.mop.simple.texteditor.widget.SimpleTextEditorView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

import static android.app.Activity.RESULT_OK;

/**
 */
public class SimpleTextViewFragment extends Fragment {

    private SimpleTextEditorView simpleTextEditorView;

    public SimpleTextViewFragment() {
        // Required empty public constructor
    }

    public static SimpleTextViewFragment newInstance() {
        SimpleTextViewFragment fragment = new SimpleTextViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_text_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        simpleTextEditorView = view.findViewById(R.id.simpleRichTextView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == 1) {
                    //处理调用系统图库
                    Logger.d("处理调用系统图库");
                } else if (requestCode == PhotoPicker.REQUEST_CODE) {
                    //异步方式插入图片
                    Logger.d("异步方式插入图片");
                    ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);

                    ArrayList<SimpleTextBean> list = new ArrayList<>();
                    for (String s : photos) {
                        SimpleTextBean simpleTextBean = new SimpleTextBean();
                        simpleTextBean.setType(SimpleTextBean.IMAGE);
                        simpleTextBean.setImgUrl(s);
                        simpleTextBean.setContent("[图片]");
                        list.add(simpleTextBean);
                    }
                    list.add(ItemUtil.getDefaultRichText(""));
                    simpleTextEditorView.addData(list);
                }
            }
        }
    }
}
