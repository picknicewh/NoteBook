package com.wh.kotlinnotebook.ui.info.detail;


import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.bean.StoryDetail;
import com.wh.kotlinnotebook.network.GlideUtils;
import com.wh.kotlinnotebook.util.G;

import butterknife.BindView;

public class InfoDetailActivity extends BaseActivity implements InfoDetailContract.View {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_mtitle)
    TextView tvTitle;
    @BindView(R.id.tv_image_source)
    TextView tvImageSource;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.mWebView)
    WebView mWebView;
    private InfoDetailPresenter presenter;

    @Override
    public void setTop() {
        isHideNavigation(false);
    }

    @Override
    public void initData() {
        setLeftImage(R.mipmap.ic_back);
        setTitle("咨询详情");
        presenter = new InfoDetailPresenter(this);
        presenter.subscribe();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_info_detail;
    }

    @Override
    public void setStoryDetailVo(StoryDetail storyDetail) {
        GlideUtils.loadImageView(this, storyDetail.getImage() ,ivImage);
        rlTop .setVisibility(G.isEmteny(storyDetail.getImage())? View.GONE : View.VISIBLE);
         tvTitle.setText( storyDetail.getTitle());
        tvImageSource.setText(storyDetail.getImage_source());
        StringBuffer buffer = new StringBuffer();
     //   String  css = "<link  rel=\"stylesheet\"  type=\"text/css\"  href=\"" + storyDetail.getCss().get(0) + "\"/>";
        //buffer.append(css).append("\n");
        buffer.append(storyDetail.getBody());
        G.log("xxxxxxxx" + buffer.toString());
        mWebView.loadDataWithBaseURL(null, buffer.toString(), "text/html", "utf-8", null);
    }

    @Override
    public int getStoryId() {
        return getIntent().getIntExtra("storyId", -1);
    }


}
