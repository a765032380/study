package com.example.gll.study;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gll.study.adapter.MyFragmentAdapter;
import com.example.gll.study.adapter.MyPagerAdapter;
import com.example.gll.study.fragment.FragmentFour;
import com.example.gll.study.fragment.FragmentOne;
import com.example.gll.study.fragment.FragmentThree;
import com.example.gll.study.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //自定义适配器View类型
    private MyPagerAdapter mPagerAdapter;
    //自定义适配器Fragment类型
    private MyFragmentAdapter mMyFragmentAdapter;
    //声明ViewPager
    private ViewPager mViewPager;
    //ViewPager的指示器
    private PagerTabStrip mPagerTabStrip;
    //储存View的集合
    private List<View> list;
    //储存ViewPager的指示器文本内容的集合
    private List<String> mTitleList;
    //储存Fragment的集合
    private List<Fragment> mFragmentList;
    //Fragment管理者
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //获取数据源
        getData();
        //实例化适配器
        mPagerAdapter=new MyPagerAdapter(list,mTitleList);
        mMyFragmentAdapter=new MyFragmentAdapter(fm,mFragmentList,mTitleList);
        //加载适配器

        int count = mPagerAdapter.getCount();
        if (count > 1) { // 多于1个，才循环
            int index = mViewPager.getCurrentItem();
            index = (index + 1) % count;
            mViewPager.setCurrentItem(index, true);
        }
        mViewPager.setCurrentItem(count * 100);
        mViewPager.setAdapter(mPagerAdapter);
//        mViewPager.setAdapter(mMyFragmentAdapter);
    }

    private void init() {
        //实例化UI
        mViewPager= (ViewPager) findViewById(R.id.pager);
        mPagerTabStrip= (PagerTabStrip) findViewById(R.id.tab);
        //实例化Fragment管理者
        fm=getSupportFragmentManager();
        //实例化集合
        list=new ArrayList<>();
        mTitleList=new ArrayList<>();
        mFragmentList=new ArrayList<>();
        //设置mPagerTabStrip属性
            //设置背景颜色
            mPagerTabStrip.setBackgroundColor(Color.BLUE);
            //设置字体颜色
            mPagerTabStrip.setTextColor(Color.RED);
            //取消分割线
            mPagerTabStrip.setDrawFullUnderline(false);
            //设置指示颜色
            mPagerTabStrip.setTabIndicatorColor(Color.GREEN);
    }

    private void getData(){
        View view_one=View.inflate(this,R.layout.view_one,null);
        View view_two=View.inflate(this,R.layout.view_two,null);
        View view_three=View.inflate(this,R.layout.view_three,null);
        View view_four=View.inflate(this,R.layout.view_four,null);
        list.add(view_one);
        list.add(view_two);
        list.add(view_three);
        list.add(view_four);
        mTitleList.add("第一页");
        mTitleList.add("第二页");
        mTitleList.add("第三页");
        mTitleList.add("第四页");
        mFragmentList.add(new FragmentOne());
        mFragmentList.add(new FragmentTwo());
        mFragmentList.add(new FragmentThree());
        mFragmentList.add(new FragmentFour());
    }
}
