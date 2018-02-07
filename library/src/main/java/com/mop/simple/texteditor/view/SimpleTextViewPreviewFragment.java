package com.mop.simple.texteditor.view;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.adapter.SimpleTextViewAdapter;
import com.mop.simple.texteditor.db.DraftDatabaseUtil;
import com.mop.simple.texteditor.db.entity.DraftData;

/**
 */
public class SimpleTextViewPreviewFragment extends Fragment {

    private XRecyclerView xRecyclerView;
    private DraftData data;
    private SimpleTextViewAdapter simpleRichTextViewAdapter;

    private WebView webView;

    public SimpleTextViewPreviewFragment() {
        // Required empty public constructor
    }

    public static SimpleTextViewPreviewFragment newInstance(DraftData data) {
        SimpleTextViewPreviewFragment fragment = new SimpleTextViewPreviewFragment();
        Bundle args = new Bundle();
        args.putParcelable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getParcelable("data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_text_view_preview,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        xRecyclerView = view.findViewById(R.id.xRecyclerView);
        webView = view.findViewById(R.id.webView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setPullRefreshEnabled(false);
        xRecyclerView.setLoadingMoreEnabled(false);

        simpleRichTextViewAdapter = new SimpleTextViewAdapter();
        xRecyclerView.setAdapter(simpleRichTextViewAdapter);

        xRecyclerView.addHeaderView(initHeaderView(data.title));
        simpleRichTextViewAdapter.setDatas(DraftDatabaseUtil.getContentList(data.content));
    }

    private View initHeaderView(String title) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_simple_text, xRecyclerView, false);
        TextView tvTitle = view.findViewById(R.id.tvContent);
        tvTitle.setText(title);
        tvTitle.setTextColor(getResources().getColor(android.R.color.black));
        tvTitle.setTextSize(24);
        tvTitle.setTypeface(tvTitle.getTypeface(), Typeface.BOLD);
        return view;
    }
}
