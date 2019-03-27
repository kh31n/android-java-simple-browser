package com.test.hirotoshi.basic_browser;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainWindow extends AppCompatActivity {
    private WebView webView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        if(savedInstanceState == null){
            ActionBar actionBar = this.getSupportActionBar();

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        webView = (WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        this.getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this.onQueryTextListener);

        return super.onCreateOptionsMenu(menu);
    }

    public SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener(){
        @Override
        public boolean onQueryTextSubmit(String searchWord){
            return searchWeb(searchWord);
        }

        @Override
        public boolean onQueryTextChange(String newText){
            return false;
        }
    };

    public boolean searchWeb(String searchWord){
        webView.loadUrl("https://www.google.co.jp/search?q=" + searchWord);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                webView.goBack();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
