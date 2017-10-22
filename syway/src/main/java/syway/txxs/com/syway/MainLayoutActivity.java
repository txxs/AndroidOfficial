package syway.txxs.com.syway;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import syway.txxs.com.syway.adapter.MainFragmentPagerAdapter;
import syway.txxs.com.syway.modifyclass.NoScrollViewPager;


/**
 * Created by jianghuimin on 2017/10/17.
 */

public class MainLayoutActivity extends FragmentActivity {

    private NoScrollViewPager mPager;
    private RadioGroup mGroup;
    private RadioButton around,hot,mine;
    private AroundFragment aroundFragment;
    private HotFragment hotFragment;
    private MineFragment mineFragment;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //初始化界面组件
        initView();
        //初始化ViewPager
        initViewPager();
    }

    private void initView(){
        mPager=(NoScrollViewPager)findViewById(R.id.viewPager);
        mGroup=(RadioGroup)findViewById(R.id.radiogroup);
        around=(RadioButton)findViewById(R.id.mainlayout_radio_around);
        hot=(RadioButton)findViewById(R.id.mainlayout_radio_hot);
        mine=(RadioButton)findViewById(R.id.mainlayout_radio_mine);
        //RadioGroup选中状态改变监听
        CheckChangeListener checkChangeListener = new CheckChangeListener();
        mGroup.setOnCheckedChangeListener(checkChangeListener);
    }

    private void initViewPager(){
        aroundFragment = new AroundFragment();
        hotFragment = new HotFragment();
        mineFragment = new MineFragment();
        fragmentList=new ArrayList<>();
        fragmentList.add(aroundFragment);
        fragmentList.add(hotFragment);
        fragmentList.add(mineFragment);
        //ViewPager设置适配器
        mPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        //ViewPager显示第一个Fragment
        mPager.setCurrentItem(0);
        mPager.setNoScroll(true); //禁止手动滑动
    }

    /**
     *RadioButton切换Fragment
     */
    private class CheckChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.mainlayout_radio_around:
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

}

