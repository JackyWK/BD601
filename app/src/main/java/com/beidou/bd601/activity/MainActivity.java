package com.beidou.bd601.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.beidou.bd601.fragment.MyFragmentPagerAdapter;
import com.beidou.bd601.R;

/**
 * Created by wangkuan on 2016/9/28 .
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    //UI Objects
    private TextView txt_topbar;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_homepage;
    private RadioButton rb_location;
    private RadioButton rb_comm;
    private RadioButton rb_uploadloc;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_HOME = 0;
    public static final int PAGE_LOCATION = 1;
    public static final int PAGE_COMM = 2;
    public static final int PAGE_UPLOADLOC = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_homepage.setChecked(true);
    }

    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_homepage = (RadioButton) findViewById(R.id.rb_homepage);
        rb_location = (RadioButton) findViewById(R.id.rb_location);
        rb_comm = (RadioButton) findViewById(R.id.rb_comm);
        rb_uploadloc = (RadioButton) findViewById(R.id.rb_uploadloc);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_homepage:
                vpager.setCurrentItem(PAGE_HOME);
                break;
            case R.id.rb_location:
                vpager.setCurrentItem(PAGE_LOCATION);
                break;
            case R.id.rb_comm:
                vpager.setCurrentItem(PAGE_COMM);
                break;
            case R.id.rb_uploadloc:
                vpager.setCurrentItem(PAGE_UPLOADLOC);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_HOME:
                    rb_homepage.setChecked(true);
                    break;
                case PAGE_LOCATION:
                    rb_location.setChecked(true);
                    break;
                case PAGE_COMM:
                    rb_comm.setChecked(true);
                    break;
                case PAGE_UPLOADLOC:
                    rb_uploadloc.setChecked(true);
                    break;
            }
        }
    }
}
