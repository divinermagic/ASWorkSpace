package com.example.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ViewPager:系统提供的一个天生带有左右滑动的控件
 *
 * @author Diviner
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //viewpager的用法:跟ListView用法相似
        //也是要设置Adapter 不过是 PagerAdapter
        viewpager.setAdapter(new Myadapter());
    }


    /**
     * ViewPager的Adapter
     * 默认只自动生成 getCount()和 isViewFromObject()
     * 记得要手动生成 instantiateItem()和destroyItem()
     */
    private class Myadapter extends PagerAdapter{

        @Override
        /**
         * 设置ViewPager的条目的个数
         * 跟ListView的Adapter的getCount方法是一致的
         */
        public int getCount() {
            return 0;
        }

        @Override
        /**
         * 设置是否显示相应界面的操作,因为有左右两种切换操作,所以需要知道相应的切换操作,应该切换到哪个界面
         * View View:ViewPager的显示界面
         * Object object:创建的显示的界面,也是添加到viewpager中实际显示的界面,instantiateItem返回的
         */
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            //return view == object; 固定写法
            return view == object;
        }

        @NonNull
        @Override
        /**
         * 给ViewPager添加显示界面
         * 在instantiateItem方法中就需要将显示的界面创建出来,并设置给viewPager
         */
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        /**
         * 当界面删除的时候调用的方法
         * 要把 'super.destroyItem(container, position, object);'注释掉 因为它会抛出一个异常
         * throw new UnsupportedOperationException("所需的方法销毁项没有被覆盖");
         */
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //super.destroyItem(container, position, object);
        }
    }
}
