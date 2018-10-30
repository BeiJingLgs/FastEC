package com.flj.latte.ec.main.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.diabin.latte.ec.R;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.ec.main.EcBottomDelegate;
import com.flj.latte.ec.main.index.search.SearchDelegate;
import com.flj.latte.net.RestCreator;
import com.flj.latte.net.rx.RxRestClient;
import com.flj.latte.ui.recycler.BaseDecoration;
import com.flj.latte.ui.refresh.RefreshHandler;
import com.flj.latte.util.callback.CallbackManager;
import com.flj.latte.util.callback.CallbackType;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 傅令杰
 */

public class IndexDelegate extends BottomItemDelegate implements View.OnFocusChangeListener {

    private RecyclerView mRecyclerView = null;
    private SwipeRefreshLayout mRefreshLayout = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRecyclerView = $(R.id.rv_index);
        mRefreshLayout = $(R.id.srl_index);

        final IconTextView mIconScan = $(R.id.icon_index_scan);
        final AppCompatEditText mSearchView = $(R.id.et_search_view);

        $(R.id.icon_index_scan).setOnClickListener(view -> startScanWithCheck(getParentDelegate()));

        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, args ->
                        Toast.makeText(getContext(), "得到的二维码是" + args, Toast.LENGTH_LONG).show());
        mSearchView.setOnFocusChangeListener(this);

//        onCallRxGet();
//        onCallRxRestClient();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Toolbar toolbar = view.findViewById(R.id.tb_index);
        toolbar.getBackground().setAlpha(0);
    }

    //TODO:测试方法，没啥卵用
    void onCallRxGet() {

        final String url = "index.php";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();

        final Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO:测试方法，没啥卵用X2
    private void onCallRxRestClient() {


        final String url = "index.php";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        final Context context = getContext();
        mRecyclerView.setLayoutManager(manager);
        if (context != null) {
            mRecyclerView.addItemDecoration
                    (BaseDecoration.create(ContextCompat.getColor(context, R.color.app_background), 5));
        }
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            getParentDelegate().start(new SearchDelegate());
        }
    }
}
