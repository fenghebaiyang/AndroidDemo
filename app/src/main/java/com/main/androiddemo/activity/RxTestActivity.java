package com.main.androiddemo.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.main.androiddemo.R;
import com.main.androiddemo.utils.Logger;
import com.main.androiddemo.utils.StepLineManager;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxTestActivity extends AppCompatActivity {

    private ProgressBar prb;
    private Button button;

    private final CompositeDisposable disposables = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
        prb = (ProgressBar) findViewById(R.id.prb);
        button = (Button) findViewById(R.id.button);
        maketest();
    }

    private void maketest() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRunSchedulerExampleButtonClicked();
                StepLineManager.getInstance().nextStep(RxTestActivity.this,null);
            }
        });
    }

    private void onRunSchedulerExampleButtonClicked() {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override public void onComplete() {
                        Logger.d("onComplete()");
                    }

                    @Override public void onError(Throwable e) {
                        Logger.dd("onError()", e);
                    }

                    @Override public void onNext(String string) {
                        Logger.d("onNext(" + string + ")");
                    }
                }));
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override public ObservableSource<? extends String> call() throws Exception {
                // Do some long running operation
                SystemClock.sleep(5000);
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }
}
