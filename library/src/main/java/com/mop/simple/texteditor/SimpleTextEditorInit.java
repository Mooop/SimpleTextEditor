package com.mop.simple.texteditor;

import android.content.Context;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Administrator on 2018/2/2.
 */

public class SimpleTextEditorInit {

    private static SimpleTextEditorInit singleton;
    private static boolean logEnable = false;

    private SimpleTextEditorInit(Context context) {
        FlowManager.init(new FlowConfig.Builder(context).build());
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return logEnable;
            }
        });

        EmojiCompat.Config config = new BundledEmojiCompatConfig(context);
        EmojiCompat.init(config);
    }

    public static void setLogEnable(boolean logEnable) {
        SimpleTextEditorInit.logEnable = logEnable;
    }

    public static SimpleTextEditorInit init(Context context) {
        if (singleton == null)
            synchronized (SimpleTextEditorInit.class) {
                if (singleton == null)
                    singleton = new SimpleTextEditorInit(context);
            }
        return singleton;
    }
}
