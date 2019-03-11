package ipetrokala.com.petrokala;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;
//    @BindView(R.id.prog1)
//    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        if (hasInternetConnection(MainActivity.this)) {
            Toast.makeText(getApplicationContext(), "خوش آمدید", Toast.LENGTH_LONG).show();
            WebViewClient webViewClient = new WebViewClient();
            webView.setWebViewClient(webViewClient);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setBackgroundColor(getResources().getColor(R.color.black));
            webView.loadUrl("http://ipetrokala.com");

        } else {
            Toast.makeText(getApplicationContext(), "اینترنت قطغ میباشد. لطفا بررسی بفرمایید", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
//
//    public class myWebClient extends WebViewClient {
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            // TODO Auto-generated method stub
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            // TODO Auto-generated method stub
//            progressBar.setVisibility(View.VISIBLE);
//            view.loadUrl(url);
//            return true;
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            // TODO Auto-generated method stub
//            super.onPageFinished(view, url);
//            progressBar.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
//            webView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public boolean hasInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = null;
        if (cm != null) {
            wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        }
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }
        NetworkInfo mobileNetwork = null;
        if (cm != null) {
            mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }
        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }
        return false;
    }
}
