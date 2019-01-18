package com.example.aoneill98.organiser2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class ContactUs extends AppCompatActivity {

    ImageButton insta, facebook, linkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        insta=(ImageButton)findViewById(R.id.btnInsta);
        facebook=(ImageButton)findViewById(R.id.btnFacebook);
        linkedIn=(ImageButton)findViewById(R.id.btnLinkedIn);

        // Checks if the user has clicked on the link for instagram and it loads the appropiate website if so (company's page)
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_webview);
                WebView webView = (WebView) findViewById(R.id.webview);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://www.instagram.com/?hl=en");
            }
        });

        // Checks if the user has clicked on the link for facebook and it loads the appropiate website if so (company's page)
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_webview);
                WebView webView = (WebView) findViewById(R.id.webview);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://www.facebook.com/");
            }
        });

        // Checks if the user has clicked on the link for LinkedIn and it loads the appropiate website if so (company's page)
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_webview);
                WebView webView = (WebView) findViewById(R.id.webview);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("https://www.linkedin.com/");
            }
        });
    }
}
