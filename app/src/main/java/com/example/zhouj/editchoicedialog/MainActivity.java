package com.example.zhouj.editchoicedialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textview)
    TextView textview;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.button,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                final EditChoiceDialog.Builder builder = new EditChoiceDialog.Builder(this);
                builder.setMessage("请输入房间名称和设备名称");
                builder.setTitle("提示");
                builder.setEditSpinner1(R.array.edits_array_1);
                builder.setEditSpinner2(R.array.edits_array_2);
                builder.setEditSpinnerStr1("客厅");
                builder.setEditSpinnerStr2("吊灯");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String EditSpinnerStr1=builder.mEditSpinner1.getText().toString();
                        String EditSpinnerStr2=builder.mEditSpinner2.getText().toString();
                        if(EditSpinnerStr1.isEmpty()||EditSpinnerStr2.isEmpty()){
                            Toast.makeText(MainActivity.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                        }else{
                            dialog.dismiss();
                            //设置你的操作事项
                            textview.setText(EditSpinnerStr1+ builder.txtde.getText().toString() + EditSpinnerStr2);
                        }
                    }
                });

                builder.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.create().show();

                break;
        }
    }
}
