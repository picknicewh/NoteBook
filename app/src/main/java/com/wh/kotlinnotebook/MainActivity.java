package com.wh.kotlinnotebook;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.base.BaseFragment;
import com.wh.kotlinnotebook.common.Consist;
import com.wh.kotlinnotebook.common.ViewPagerAdapter;
import com.wh.kotlinnotebook.database.UserInfo;
import com.wh.kotlinnotebook.ui.about.AboutFragment;
import com.wh.kotlinnotebook.ui.home.HomeFragment;
import com.wh.kotlinnotebook.ui.info.InfoFragment;
import com.wh.kotlinnotebook.ui.secret.SecretDetailFragment;
import com.wh.kotlinnotebook.ui.secret.SecretFragment;
import com.wh.kotlinnotebook.util.G;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, SecretFragment.ReplaceFragmentListener {

    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_secret)
    RadioButton rbSecret;
    @BindView(R.id.rb_information)
    RadioButton rbInformation;
    @BindView(R.id.rb_about)
    RadioButton rbAbout;
    @BindView(R.id.rg_nav)
    RadioGroup rgNav;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    /**
     * 数据列表
     */
    private List<BaseFragment> fragmentList;
    /**
     * 适配器
     */
    private ViewPagerAdapter adapter;
    /**
     * 首页
     */
    private HomeFragment homeFragment;
    /**
     * 秘密设置
     */
    private SecretFragment secretFragment;
    /**
     * 秘密详情页
     */
    private SecretDetailFragment detailFragment;
    /**
     * 信息页
     */
    private InfoFragment infoFragment;
    /**
     * 关于页
     */
    private AboutFragment aboutFragment;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setTop() {
        isHideNavigation(true);
    }

    @Override
    public void initData() {
        rgNav.setOnCheckedChangeListener(this);
        fragmentList = new ArrayList<>();
        initFragment();
    }
   private void initFragment(){
        homeFragment = new HomeFragment();
        secretFragment = new SecretFragment();
        detailFragment = new SecretDetailFragment();
        infoFragment = new InfoFragment();
        aboutFragment = new AboutFragment();
       fragmentList.add(homeFragment);
       if (G.isEmteny(UserInfo.getInstance().getSecretDate())){
           fragmentList.add(secretFragment);
           secretFragment.setReplaceFragmentListener(this);
       }else {
           fragmentList.add(detailFragment);
       }
       fragmentList.add(infoFragment);
       fragmentList.add(aboutFragment);
       adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentList);
       vpMain.setAdapter(adapter);
       vpMain.setCurrentItem(0);
       vpMain.setOffscreenPageLimit(3);
       vpMain.addOnPageChangeListener(this);
   }
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                vpMain.setCurrentItem(Consist.HOME);
                break;
            case R.id.rb_secret:
                vpMain.setCurrentItem(Consist.SECRET);
                break;
            case R.id.rb_information:
                vpMain.setCurrentItem(Consist.INFROMATION);
                break;
            case R.id.rb_about:
                vpMain.setCurrentItem(Consist.ABOUT);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageSelected(int position) {
        switch (position){
            case Consist.HOME:
                rbHome.setChecked(true);
                rbSecret.setChecked(false);
                rbInformation.setChecked(false);
                rbAbout.setChecked(false);
                break;
            case Consist.SECRET:
                rbHome.setChecked(false);
                rbSecret.setChecked(true);
                rbInformation.setChecked(false);
                rbAbout.setChecked(false);
                break;
            case Consist.INFROMATION:
                rbHome.setChecked(false);
                rbSecret.setChecked(false);
                rbInformation.setChecked(true);
                rbAbout.setChecked(false);
                break;
            case Consist.ABOUT:
                rbHome.setChecked(false);
                rbSecret.setChecked(false);
                rbInformation.setChecked(false);
                rbAbout.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void replace() {

        fragmentList.set(1,detailFragment);
        if (adapter!=null){
            adapter.setNewFragments(fragmentList);
            vpMain.setCurrentItem(1);
        }
    }

}
