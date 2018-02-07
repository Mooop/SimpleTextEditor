package com.mop.simple.texteditor.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.adapter.AbstractRichTextViewAdapter;
import com.mop.simple.texteditor.adapter.DraftBoxAdapter;
import com.mop.simple.texteditor.db.DraftDatabaseUtil;
import com.mop.simple.texteditor.db.entity.DraftData;

import java.util.ArrayList;

/**
 *
 */
public class DraftBoxFragment extends Fragment {

    private XRecyclerView xRecyclerView;
    private DraftBoxAdapter draftBoxAdapter;

    public DraftBoxFragment() {
        // Required empty public constructor
    }

    public static DraftBoxFragment newInstance() {
        DraftBoxFragment fragment = new DraftBoxFragment();
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
        return inflater.inflate(R.layout.fragment_draft_box, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        xRecyclerView = view.findViewById(R.id.xRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setPullRefreshEnabled(false);
        xRecyclerView.setLoadingMoreEnabled(false);

        draftBoxAdapter = new DraftBoxAdapter();
        xRecyclerView.setAdapter(draftBoxAdapter);

        final ArrayList<DraftData> data = new ArrayList<>();
        data.addAll(DraftDatabaseUtil.getResultList(DraftData.class));
        draftBoxAdapter.setDatas(data);

        draftBoxAdapter.setOnItemClickListener(new AbstractRichTextViewAdapter.OnItemClickListener() {
            @Override
            public void itemClick(View view, int position) {
                Intent intent = new Intent(view.getContext(),
                        SimpleTextViewPreviewActivity.class);
                intent.putExtra("data", draftBoxAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
