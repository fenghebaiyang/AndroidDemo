package com.main.androiddemo.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.main.androiddemo.R;
import com.main.androiddemo.utils.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/6/13 0013
 */
public class ComInfoActivity extends BaseActivity {

    private SwipeRefreshLayout swipeRef;
    private LinearLayout linear;
    private CompositeDisposable disposables;

    @Override
    protected void initGetData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.act_cominfo;
    }

    @Override
    protected void findViews() {
        swipeRef = (SwipeRefreshLayout) findViewById(R.id.swipeRef);
        linear = (LinearLayout) findViewById(R.id.linear);
    }

    @Override
    protected void widgetListener() {
        swipeRef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
    }

    @Override
    protected void init() {
        disposables = new CompositeDisposable();
        requestData();
    }

    String jinriyunshi = "http://m.meiguoshenpo.com/yunshi/baiyang/";

    private void requestData() {
        disposables.add(Observable.create(new ObservableOnSubscribe<Document>() {

            @Override
            public void subscribe(ObservableEmitter<Document> e) throws Exception {
                Document doc = Jsoup.connect(jinriyunshi).execute().parse();
                if (doc != null) {
                    e.onNext(doc);
                    e.onComplete();
                } else {
                    e.onError(new IOException("error"));
                }
            }
        }).doOnNext(new Consumer<Document>() {

            @Override
            public void accept(Document document) throws Exception {
                Logger.d("doc", document.outerHtml());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Document>() {
            @Override
            public void accept(Document document) throws Exception {
                swipeRef.setRefreshing(false);
                Element title = document.getElementById("YUNSHI_TITLE");
                TextView txtTitle = new TextView(mContext);
                txtTitle.setText(Html.fromHtml(title.outerHtml()));

                Elements context = document.getElementsByClass("yuncheng_cnt");
                TextView txt = new TextView(mContext);
                txt.setText(Html.fromHtml(context.outerHtml()));

                linear.removeAllViews();
                linear.addView(txtTitle);
                linear.addView(txt);
            }
        }));
    }
}
