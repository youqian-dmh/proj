package cn.edu.sdwu.android.classroom.sn170507180201;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Ch9Activity2 extends AppCompatActivity {
    private ArrayList category;//类别 第一级数据
    private HashMap product;
    // hashmap快速找到商品
    // 第二级数据产品
    // key表示类别，value表示多个商品
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch9_2);
        initData();//获取数据调用方法

        ExpandableListView expandableListView=(ExpandableListView)findViewById(R.id.ch9_2_elv);
        MyAdapter myAdapter=new MyAdapter();
       expandableListView.setAdapter(myAdapter);

    }
    //自定义适配器
    class MyAdapter extends BaseExpandableListAdapter{

        @Override
        public int getGroupCount() {
            //得到一级数据的个数 ： 商品类别
            return 0;
        }

        @Override
        public int getChildrenCount(int i) {
            //得到某个一级数据下的二级数据：某个类别下包含的商品
            String cat=category.get(i).toString();//得到类别
            ArrayList prolist=(ArrayList) product.get(cat);
            return prolist.size();
        }

        @Override
        public Object getGroup(int i) {
            //得到一级数据
            String cat=category.get(i).toString();//得到类别
            return cat;
        }

        @Override
        public Object getChild(int i, int i1) {
            //得到某个一级数据下的二级数据：某个类别下包含的商品
            String cat=category.get(i).toString();//得到类别
            ArrayList prolist=(ArrayList) product.get(cat);
            return prolist.get(i);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i*1000+i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            //得到一级数据的试图
            //int i 索引 第几类别，View view得到一级数据的试图 为了提高运行的效率，系统将之前使用的View对象传回，我们可以重复利用，避免每次实例化对象
            if(view==null){
                //如果view是null 我们需要进行实例化，布局加载器（加载布局文件）
                LayoutInflater layoutInflater=getLayoutInflater();
                view=layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1,null);//调用系统的布局文件
            }
            //设置展示的内容
            TextView textView=(TextView) view.findViewById(android.R.id.text1);
            String cat=category.get(i).toString();//得到类别
            textView.setText(cat);
            return view;//产品类别可以展示出了
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            //int i, int i1代第几个类别第几个商品
            // 得到二级数据的试图
            if(view==null){
                LayoutInflater layoutInflater=getLayoutInflater();
                view=layoutInflater.inflate(R.layout.layout_goods,null);
            }
            //设置产品展示的内容
            String cat=category.get(i).toString();
            ArrayList list=(ArrayList)product.get(cat);
            HashMap map=(HashMap)list.get(i1);
            String name=(String)map.get("name");
            String price=(String)map.get("price");
            TextView textView=(TextView)view.findViewById(R.id.goods_name);
            textView.setText(name);
            textView=(TextView)view.findViewById(R.id.goods_price);
            textView.setText(price);
            ImageView imageView=(ImageView)findViewById(R.id.goods_img);
            imageView.setImageResource(R.drawable.img);
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }
    private void initData(){
        //准备数据，实际情况下，可能来自网络或数据库（调用模型层model的功能）
        category=new ArrayList();
        category.add("category1");
        category.add("category2");
        category.add("category3");
        //实例化
        product=new HashMap();
        ArrayList list=new ArrayList();
        HashMap map=new HashMap();
        //共三个类别 ：第一个类别
        //添加第一个商品
        map.put("name","category1_prod1");
        map.put("price",50);
        list.add(map);
        //添加第2个商品
        map.put("name","category1_prod2");
        map.put("price",60);
        list.add(map);
        product.put("category1",list);//添加进列表


        list=new ArrayList();
        map=new HashMap();
        //共三个类别 ：第2个类别
        //添加第一个商品
        map.put("name","category2_prod1");
        map.put("price",5);
        list.add(map);
        //添加第2个商品
        map.put("name","category2_prod2");
        map.put("price",6);
        list.add(map);
        product.put("category2",list);//添加进列表


        list=new ArrayList();
        map=new HashMap();
        //共三个类别 ：第3个类别
        //添加第一个商品
        map.put("name","category3_prod1");
        map.put("price",5);
        list.add(map);
        //添加第2个商品
        map.put("name","category3_prod2");
        map.put("price",6);
        list.add(map);
        product.put("category3",list);//添加进列表


    }
}
