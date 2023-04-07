package com.example.dsatutor.UI.Dashboard.Learning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dsatutor.MainActivity;
import com.example.dsatutor.R;
import com.example.dsatutor.databinding.ActivityWebViewBinding;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebViewBinding binding;
    private int currentApiVersion;
    private View customView;
    private WebChromeClient.CustomViewCallback customViewCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setScreenType();
        binding=ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        webViewInit();
        binding.webViewVideo.loadUrl("https://www.youtube.com/watch?v=2JroZSycSXs");
        binding.webview.loadUrl("https://www.javatpoint.com/data-structure-introduction");
        setWebView();
        clickOnButton();
    }

    private void clickOnButton(){
        binding.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                startActivity(new Intent(WebViewActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finishAffinity();
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heartTouchEffect(view);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        });
    }

    private void heartTouchEffect(View view) {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        view.startAnimation(anim);
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void webViewInit()
    {
        WebSettings webSettings1 = binding.webview.getSettings();
        WebSettings webSettings2 = binding.webViewVideo.getSettings();
        webSettings1.setJavaScriptEnabled(true);
        webSettings2.setJavaScriptEnabled(true);
    }

    private void setWebView(){
        binding.webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        binding.webViewVideo.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        binding.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        binding.webViewVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });



        binding.webViewVideo.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                binding.webViewVideo.setVisibility(View.GONE);
                binding.videoLayout.addView(view);
                customView = view;
                customViewCallback = callback;
                binding.videoLayout.setVisibility(View.VISIBLE);
                binding.homeBtn.setVisibility(View.GONE);
                binding.backBtn.setVisibility(View.GONE);
            }

            @Override
            public void onHideCustomView() {
                super.onHideCustomView();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                binding.videoLayout.removeView(customView);
                customView = null;
                customViewCallback.onCustomViewHidden();
                binding.webViewVideo.setVisibility(View.VISIBLE);
                binding.videoLayout.setVisibility(View.GONE);
                binding.homeBtn.setVisibility(View.VISIBLE);
                binding.backBtn.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
    @Override
    public void onBackPressed() {
        if (binding.webview.canGoBack() || binding.webViewVideo.canGoBack()) {
            if(binding.webview.canGoBack()){
                binding.webview.goBack();
            }else{
                binding.webViewVideo.goBack();
            }

        } else {
            super.onBackPressed();
        }
    }
    private void setScreenType() {
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

    }
    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}