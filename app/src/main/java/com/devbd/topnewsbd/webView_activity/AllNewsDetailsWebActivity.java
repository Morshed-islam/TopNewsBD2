package com.devbd.topnewsbd.webView_activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.devbd.topnewsbd.R;
import com.devbd.topnewsbd.constant.Constant;

import java.util.concurrent.ExecutionException;

public class AllNewsDetailsWebActivity extends AppCompatActivity {

    String url;
    String mProthomALo, mBdNews, mKalerKantho,ntvNews;
    WebView webView;

    private String[] titles = {"ProthomAlo","BDNews"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prothom_alo_latest_web);


//        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            //actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_24dp);
//            actionBar.setDisplayShowTitleEnabled(true);
//            actionBar.setTitle("টপ নিউজ বিডি");
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

//        Intent title = getIntent();
//        Intent title1 = getIntent();

//            if (title0.getStringExtra(Constant.PROTHOM_ALO_TITLE).equals("0")){
//                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                toolbar.setTitle(titles[0]);
//                setSupportActionBar(toolbar);
//            }else if (title1.getStringExtra(Constant.NTV_NEWS_TITILE).equals("1")){
//                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                toolbar.setTitle(titles[1]);
//                setSupportActionBar(toolbar);
//            }



//
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("টপ নিউজ বিডি");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//
//        Intent title = getIntent();
//        mProthomALo = title.getStringExtra(Constant.PROTHOM_ALO_TITLE);
//
//

//
//
//        mProthomALo = title.getStringExtra(Constant.PROTHOM_ALO_TITLE);
//        mBdNews = title.getStringExtra(Constant.BDNEWS_TITLE);
//        mKalerKantho = title.getStringExtra(Constant.KALER_KANTHO_TITLE);
//
//
//        if (mProthomALo.equals("প্রথম আলো")){
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            toolbar.setTitle(mProthomALo);
//            setSupportActionBar(toolbar);
//
//            Log.i("morshed",mProthomALo);
//
//
//        }
//        if (mBdNews.equals("বিডি নিউজ")){
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            toolbar.setTitle(mBdNews);
//            setSupportActionBar(toolbar);
//
//            Log.i("morshed",mBdNews);
//
//        }

//        if (ntvNews.equals("এনটিভি নিউজ")){
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            toolbar.setTitle(ntvNews);
//            setSupportActionBar(toolbar);
//
//            Log.i("morshed",ntvNews);
//
//            //এনটিভি নিউজ
//        }


//
//        if (mKalerKantho.equals("কালের কন্ঠ")){
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            toolbar.setTitle(mKalerKantho);
//            setSupportActionBar(toolbar);
//
//            Log.i("morshed",mKalerKantho);
//        }
//
//


        Intent intent = getIntent();
        url = intent.getStringExtra(Constant.ALL_NEWS_DETAIL_INFO);

        System.out.println(url);


        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);

        WebSettings webSettings = webView.getSettings();

//
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(webViewClient);

        webView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webView.getSettings().setAllowFileAccess(true);
//        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new AppJavaScriptProxy(this, webView), "androidAppProxy");
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // load online by default
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);

        webView.loadUrl(url);


    }

    private class WebViewClientImpl extends WebViewClient {

        Context context;

        public WebViewClientImpl(Context context) {
            this.context = context;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            view.loadUrl(url);
            return true;
        }


        ProgressDialog pd = null;


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {


            Toast.makeText(context, "Please Wait..WebSite is loading!!", Toast.LENGTH_SHORT).show();

//
//            int time = 1500;
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    pd = new ProgressDialog(AllNewsDetailsWebActivity.this);
//                    pd.setTitle("Please Wait..");
//                    pd.setMessage("Website is Loading..");
//                    pd.show();
//
//                }
//            },time);
//

            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //  pd.dismiss();
            super.onPageFinished(view, url);
        }

    }

    private class AppJavaScriptProxy {
        Context mContext;
        WebView webView;

        public AppJavaScriptProxy(Context mContext, WebView webView) {
            this.mContext = mContext;
            this.webView = webView;
        }

        /**
         * Instantiate the interface and set the context
         */


        @JavascriptInterface   // must be added for API 17 or higher
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
