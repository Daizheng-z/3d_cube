package com.example.jmeeee.View;


import android.app.AlertDialog;

import com.example.jmeeee.Presenter.ParaHandler;
import com.jme3.app.AndroidHarnessFragment;
import com.jme3.input.event.TouchEvent;

public class JmeFragment extends AndroidHarnessFragment {
    public JmeFragment() {
        this.appClass = "com.example.jmeeee.Presenter.HelloRubik";
        this.mouseEventsEnabled = true;

    }

    @Override
    public void onTouch(String name, TouchEvent evt, float tpf) {
        if (name.equals("TouchEscape")) {//检测到点击退出按钮
            switch(evt.getType()) {
                case KEY_UP:
                    this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(JmeFragment.this.getActivity());
                            builder.setTitle("您真想退出？");
                            builder.setPositiveButton("退出", JmeFragment.this);
                            builder.setNegativeButton("取消", JmeFragment.this);
                            builder.setMessage("现在退出您的魔方状态将会保存到默认存档，您可以点击取消并在屏幕右下角点击保存按钮另存您的魔方状态。");
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
            }
        }
    }

}
