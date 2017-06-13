package com.main.androiddemo.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ThreadPoolUtils;
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
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.blankj.utilcode.util.ThreadPoolUtils.CachedThread;

/**
 * <br/> Description:
 * <br/> Author: xiaojianfeng
 * <br/> Version: 1.0
 * <br/> Date: 2017/6/13 0013
 */
public class ComInfoActivity extends BaseActivity {

    private SwipeRefreshLayout swipeRef;
    private RecyclerView recyclerView;
    private LinearLayout linear;
    private ThreadPoolUtils threadPool;

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
        threadPool = new ThreadPoolUtils(CachedThread, 5);
        requestData();
    }

    String jinriyunshi = "http://m.meiguoshenpo.com/meiri/";

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
        }).flatMap(new Function<Document, ObservableSource<Document>>() {
            @Override
            public ObservableSource<Document> apply(Document document) throws Exception {
                Elements links = document.getElementsByClass("list_box");
                final String url = links.get(0).getElementsByTag("dd").get(0).getElementsByTag("h3").get(0).getElementsByTag("a").get(0).attr("href");
                for (Element element : links) {
                    Logger.d("element", element.text() + element.attr("href"));
                }
                return new ObservableSource<Document>() {
                    @Override
                    public void subscribe(Observer<? super Document> observer) {
                        try {
                            Document doc = Jsoup.connect(url).execute().parse();
                            if (doc != null) {
                                observer.onNext(doc);
                                observer.onComplete();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            observer.onError(e);
                        }
                    }
                };
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Document>() {
            @Override
            public void accept(Document document) throws Exception {
                swipeRef.setRefreshing(false);
                Elements context = document.getElementsByClass("show_cnt");
                Logger.d("context", context.text());
                linear.removeAllViews();
                TextView txt = new TextView(mContext);
                txt.setText(Html.fromHtml(context.outerHtml()));
                linear.addView(txt);
            }
        }));
    }

}
