package com.mop.simple.texteditor.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mop.simple.texteditor.R;
import com.mop.simple.texteditor.db.entity.DraftData;

public class SimpleTextViewPreviewActivity extends AppCompatActivity {

    private SimpleTextViewPreviewFragment simpleTextViewPreviewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_text_view_preview);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DraftData data = getIntent().getParcelableExtra("data");

        simpleTextViewPreviewFragment = SimpleTextViewPreviewFragment.newInstance(data);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.layoutContainer, simpleTextViewPreviewFragment)
                .commitNow();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
