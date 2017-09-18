package com.wh.kotlinnotebook.ui.home.daily;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.common.Consist;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyWordDetailActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 加载网页
     */
    @BindView(R.id.webview)
    WebView mWebView;
    /**
     * 子标题
     */
    @BindView(R.id.tv_msubtitle)
    TextView tvMsubtitle;
    /**
     * 网页地址
     */
    private String url;
    @Override
    public void setTop() {
        isHideNavigation(true);
    }
    @Override
    public void initData() {
        int from = getIntent().getIntExtra("from",Consist.FROM_HOME);
        tvMsubtitle.setVisibility(from==Consist.FROM_HOME?View.VISIBLE: View.GONE);

        url = getIntent().getStringExtra("url");
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setUseWideViewPort(true);
       // mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(url);
        showProgress();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_webview;
    }


    @OnClick({R.id.iv_mback, R.id.tv_msubtitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mback:
                finish();
                break;
            case R.id.tv_msubtitle:
                Intent intent = new Intent(this, DailyWordListActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 访问内部链接
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            hideProgress();
            view.loadUrl(url);
            return false;
        }
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
