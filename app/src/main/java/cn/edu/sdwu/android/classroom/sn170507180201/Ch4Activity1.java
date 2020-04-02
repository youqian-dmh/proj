package cn.edu.sdwu.android.classroom.sn170507180201;

import android.app.WallpaperManager;
import android.content.Intent;
import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ch4Activity1 extends AppCompatActivity implements View.OnFocusChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载界面 不能写文件名
        setContentView(R.layout.layout_ch4_1);
        //1.获取普通界面组件
        Button button=(Button) findViewById(R.id.button1);
        ImageView imageView=(ImageView)findViewById(R.id.ch4_iv);

        // 3.调用事件源的setXXXXXXXListener方法注册事件监听器
        button.setOnClickListener(new MyClickListener());

        //3)使用内部匿名类注册监听器
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //设置壁纸
                WallpaperManager wallpaperManager=(WallpaperManager) getSystemService(WALLPAPER_SERVICE);
                try{
                    wallpaperManager.setResource(R.raw.img);
                }catch(Exception e){
                    Log.e(Ch4Activity1.class.toString(),e.toString());
                }
                return false;
            }
        });
        EditText email=(EditText) findViewById(R.id.ch4_1_email);
        email.setOnLongClickListener(this);

        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.ch4_1_tel);
        linearLayout.setOnTouchListener(new View.OnTouchListener(){

        });


    }

    @Override
    public void onFocusChange(View view, boolean b) {
        //参数代表是否获取焦点
        //判断邮箱的合法性
        EditText editText=(EditText)view;
        if(!b){
            String content=editText.getText().toString();//得到Edittext的内容
            //判断邮箱的正则表达式格式是否正确
            String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern pattern=Pattern.compile(regEx1);
            Matcher matcher=pattern.matcher(content);
            TextView textView=(TextView)findViewById(R.id.ch4_1_tel);
            if(matcher.matches()){
                textView.setText("");
            }else {
                textView.setText("email invalidate");
            }
        }
    }

    //2.实现事件的监听类，该监听类是一个特殊的Java类，必须实现一个XXXListener接口：
    class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Log.i(Ch4Activity1.class.toString(),"button click");
        }
    }

    public void startMain(View view){
        //界面跳转
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
