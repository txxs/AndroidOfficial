package syway.txxs.com.syway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import syway.txxs.com.syway.adapter.MainFragmentPagerAdapter;


/**
 * Created by jianghuimin on 2017/10/17.
 */

public class MainLayoutActivity extends FragmentActivity {

    private ViewPager mPager;
    private RadioGroup mGroup;
    private RadioButton around,hot,mine;
    private ArrayList<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        //初始化界面组件
        initView();
        //初始化ViewPager
        initViewPager();
    }

    private void initView(){
        mPager=(ViewPager)findViewById(R.id.viewPager);
        mGroup=(RadioGroup)findViewById(R.id.radiogroup);
        around=(RadioButton)findViewById(R.id.mainlayout_radio_around);
        hot=(RadioButton)findViewById(R.id.mainlayout_radio_hot);
        mine=(RadioButton)findViewById(R.id.mainlayout_radio_mine);
        //RadioGroup选中状态改变监听
        CheckChangeListener checkChangeListener = new CheckChangeListener();
        mGroup.setOnCheckedChangeListener(checkChangeListener);
    }

    private void initViewPager(){
        AroundFragment aroundFragment = new AroundFragment();
        HotFragment hotFragment = new HotFragment();
        MineFragment mineFragment = new MineFragment();
        fragmentList=new ArrayList<>();
        fragmentList.add(aroundFragment);
        fragmentList.add(hotFragment);
        fragmentList.add(mineFragment);
        //ViewPager设置适配器
        mPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        //ViewPager显示第一个Fragment
        mPager.setCurrentItem(0);
        //ViewPager页面切换监听
        mPager.setOnPageChangeListener(new RadioPageChangeListener());
    }

    /**
     *RadioButton切换Fragment
     */
    private class CheckChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.mainlayout_radio_around:
                    //ViewPager显示第一个Fragment且关闭页面切换动画效果
                    mPager.setCurrentItem(0,false);
                    break;
                case R.id.mainlayout_radio_hot:
                    mPager.setCurrentItem(1,false);
                    break;
                case R.id.mainlayout_radio_mine:
                    mPager.setCurrentItem(2,false);
                    break;
            }
        }
    }

    /**
     *ViewPager切换Fragment,RadioGroup做相应变化
     */
    private class RadioPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    mGroup.check(R.id.mainlayout_radio_around);
                    break;
                case 1:
                    mGroup.check(R.id.mainlayout_radio_hot);
                    break;
                case 2:
                    mGroup.check(R.id.mainlayout_radio_mine);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}

