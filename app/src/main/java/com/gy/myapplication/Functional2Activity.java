package com.gy.myapplication;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Functional2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional2);
        initListener();
    }

    @SuppressLint("CheckResult")
    private void initListener() {
        findViewById(R.id.af_b_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick1: ");
            }
        });
        findViewById(R.id.af_b_btn2).setOnClickListener(v -> Log.d(TAG, "onClick2: "));

        findViewById(R.id.af_b_btn3).setOnClickListener(v -> {
            String[] list = new String[]{"1", "2", "a", "..", "3", "--", "4", "5", "6"};
            int sum = 0;
            for (String s : list) {
                try {
                    int i = Integer.parseInt(s);
                    if (i <= 3) continue;
                    sum += i;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            Log.d(TAG, "initListener: " + sum);
        });

        findViewById(R.id.af_b_btn4).setOnClickListener(v -> {


            sumFun();


        });
    }

    @SuppressLint("CheckResult")
    private void sumFun() {
        String[] list = new String[]{"1", "2", "a", "..", "3", "--", "4", "5", "6"};
        List<String> strings = Arrays.asList(list);
        Observable
                .fromIterable(strings).filter(s -> isInt(s))
                .map(Integer::parseInt)
                .filter(integer -> integer > 3)
                .toList()
                .map(integers -> sum(integers))
                .flatMapObservable((Function<Integer, ObservableSource<Integer>>) integer ->
                        Observable.just(integer))
                .subscribe(
                        it -> Log.d(TAG, ": " + it),
                        t -> Log.e(TAG, ": " + t.getLocalizedMessage()),
                        () -> Log.d(TAG, ":compelt ")
                );
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Integer sum(List<Integer> integers) {
        int sum = 0;
        for (Integer integer : integers) {
            sum += integer;
        }
        return sum;
    }

    public interface Map {
        void map();
    }
}
