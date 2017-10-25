package syway.txxs.com.syway;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import syway.txxs.com.syway.adapter.MainFragmentPagerAdapter;
import syway.txxs.com.syway.modifyclass.NoScrollViewPager;


/**
 * Created by jianghuimin on 2017/10/17.
 */

public class MainLayoutActivity extends FragmentActivity  implements View.OnClickListener {

    private RadioGroup mGroup;
    private RadioButton around,hot,mine;

    /**
     * 上下文
     */
    private FragmentActivity mContext;
    /**
     * Fragment 全是v4包
     */
    private Fragment[] mFragments;
    private FragmentManager manager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        mContext = this;
        // 初始化视图
        initViews();
        // 设置监听
        setListener();
    }

    /**
     * 初始化试图
     */
    private void initViews() {
        // 设置下部导航
        mGroup=(RadioGroup)findViewById(R.id.radiogroup);
        around=(RadioButton)findViewById(R.id.mainlayout_radio_around);
        hot=(RadioButton)findViewById(R.id.mainlayout_radio_hot);
        mine=(RadioButton)findViewById(R.id.mainlayout_radio_mine);

        // 设置Fragment
        mFragments = new Fragment[4];
        mFragments[0] = new AroundFragment();// 页面一添加到集合中
        mFragments[1] = new HotFragment();// 页面二添加到集合中
        mFragments[2] = new MineFragment();// 页面三添加到集合中z

        manager = mContext.getSupportFragmentManager();// 获得FragmentManager
        fragmentTransaction = manager.beginTransaction();// 获得事务
        fragmentTransaction.add(R.id.fl_contain, mFragments[0], mFragments[0] .getClass().getName());// 添加到FragmentLayout中
        fragmentTransaction.add(R.id.fl_contain, mFragments[1], mFragments[1] .getClass().getName());// 添加到FragmentLayout中
        fragmentTransaction.add(R.id.fl_contain, mFragments[2], mFragments[2] .getClass().getName());// 添加到FragmentLayout中

        // 默认显示页面一，隐藏页面二
        fragmentTransaction.show(mFragments[0]);
        fragmentTransaction.hide(mFragments[1]);
        fragmentTransaction.hide(mFragments[2]);
        fragmentTransaction.commitAllowingStateLoss();// 提交
    }

    /**
     * 设置监听
     */
    private void setListener() {
        around.setOnClickListener(this);
        hot.setOnClickListener(this);
        mine.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        FragmentTransaction transaction = mContext.getSupportFragmentManager() .beginTransaction();
        switch (view.getId()) {
            case R.id.mainlayout_radio_around:
                transaction.show(mFragments[0]);
                transaction.hide(mFragments[1]).hide(mFragments[2]);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.mainlayout_radio_hot:
                transaction.show(mFragments[1]);
                transaction.hide(mFragments[0]).hide(mFragments[2]);
                transaction.commitAllowingStateLoss();
                break;
            case R.id.mainlayout_radio_mine:
                transaction.show(mFragments[2]);
                transaction.hide(mFragments[0]).hide(mFragments[1]);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }

    }
}

