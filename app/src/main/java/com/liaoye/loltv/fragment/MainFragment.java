package com.liaoye.loltv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.liaoye.loltv.R;
import com.liaoye.loltv.activity.DetailsActivity;
import com.liaoye.loltv.api.Api;
import com.liaoye.loltv.bean.Champion;
import com.liaoye.loltv.bean.ChampionResult;
import com.liaoye.loltv.presenter.CardPresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Created by xiaoming on 2017/8/29.
 */
public class MainFragment extends BrowseFragment {
    private static final String TAG = "MainFragment";
    private ArrayObjectAdapter adapter;
    private CardPresenter cardPresenter;
    private ArrayObjectAdapter cardRowAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                if(item instanceof Champion){
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void init() {
        setHeadersState(BrowseFragment.HEADERS_DISABLED);
        setTitle(getString(R.string.app_name));


        OkGo.<String>get(Api.Champion)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String res = response.body();
                        if(!TextUtils.isEmpty(res)){
                            Gson gson = new Gson();
                            ChampionResult championResult = gson.fromJson(res, ChampionResult.class);
                            List<Champion> champions = championResult.data;
                            ListRowPresenter listRowPresenter = new ListRowPresenter();
                            listRowPresenter.setNumRows(2);
                            adapter = new ArrayObjectAdapter(listRowPresenter);
                            cardPresenter = new CardPresenter(getActivity());
                            cardRowAdapter = new ArrayObjectAdapter(cardPresenter);
                            for (Champion champion: champions){
                                cardRowAdapter.add(champion);
                            }
                            adapter.add(new ListRow(cardRowAdapter));
                            setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });

    }


}
