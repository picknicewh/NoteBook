package com.wh.kotlinnotebook.ui.home.daily;

import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.util.G;

import butterknife.BindView;
import butterknife.OnClick;

public class DailyReadDetailActivity extends BaseActivity {
    /**
     * 网页
     */
    @BindView(R.id.webview)
    WebView mWebView;
    /**
     * 网页数据
     */
    private String loadData;
    @Override
    public void setTop() {
        isHideNavigation(true);
    }

    @Override
    public void initData() {
        loadData = getIntent().getStringExtra("loadData");
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.loadDataWithBaseURL(null, loadData, "text/html", "utf-8", null);
        showProgress();
        G.log("xxxxxxxxxx"+loadData);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_daily_read_detil;
    }


    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }

    /**
     * 访问内部链接
     */
    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                hideProgress();
            }
        }
    }

    /**
     * 返回按键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            } else {
                this.finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
