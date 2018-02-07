package sample.mop.com.simpletexteditor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mop.simple.texteditor.SimpleTextEditorInit;
import com.mop.simple.texteditor.view.DraftBoxActivity;
import com.mop.simple.texteditor.view.SimpleTextViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleTextEditorInit.init(this);
        SimpleTextEditorInit.setLogEnable(true);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SimpleTextViewActivity.class));
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DraftBoxActivity.class));
            }
        });
    }
}
