package com.example.leiguo.networkprograming;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.leiguo.networkprograming.bean.AcItem;
import com.example.leiguo.networkprograming.retrofit.ApiStore;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lei Guo on 2016/10/29.
 */

public class HttpActivity extends AppCompatActivity {
    private static final String baseUrl = "http://ticket.eeyes.net/";
    private TextView sourceTextView;
    private TextView titleTextView;
    private TextView textTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        sourceTextView = (TextView) findViewById(R.id.textView_source);
        titleTextView = (TextView) findViewById(R.id.textView_title);
        textTextView = (TextView) findViewById(R.id.textView_text);
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiStore apiStore = mRetrofit.create(ApiStore.class);
        Observable<List<AcItem>> acItemObservable = apiStore.getAcItem(174);
        acItemObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<AcItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<AcItem> acItems) {
                        sourceTextView.setText(acItems.get(0).getAcsource());
                        titleTextView.setText(acItems.get(0).getActitle());
                        textTextView.setText(acItems.get(0).getActext());

                    }
                });



    }
}
