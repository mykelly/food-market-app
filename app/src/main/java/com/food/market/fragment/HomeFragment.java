package com.food.market.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.food.market.R;
import com.food.market.adapter.home.ViewFlowAdapter;
import com.food.market.util.ScreenUtil;
import com.food.market.widget.viewflow.CircleFlowIndicator;
import com.food.market.widget.viewflow.HomeViewFlow;


/**
 * Created by Kelly Li on 2017-07-02.
 */

public class HomeFragment extends BaseFragment {
    private HomeViewFlow viewFlow;
    private CircleFlowIndicator flowIndicator;
    private ViewFlowAdapter viewFlowAdapter;
    View contentView = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(contentView == null){
            contentView = inflater.inflate(R.layout.home_fragment_layout, null);
            initsListViewHeadView(contentView);
        }
        initFlowData();
        return contentView;

    }

    private void initsListViewHeadView(View contentView) {
        viewFlow = (HomeViewFlow) contentView.findViewById(R.id.home_exchange_viewflow);
        int width = ScreenUtil.getScreenWidth(getActivity());
        viewFlow.setLayoutParams(new RelativeLayout.LayoutParams(width, width / 2));
        flowIndicator = (CircleFlowIndicator) contentView.findViewById(R.id.dfhome_ad_indicator);
        flowIndicator.setVisibility(View.VISIBLE);
    }

    private void supplyFlowData() {
        if (viewFlow != null) {
            viewFlow.startAutoFlowTimer(); // 启动自动播放
        }
    }

    private void initFlowData() {
        try {
            if (viewFlow != null) {
                viewFlowAdapter = new ViewFlowAdapter(getActivity());
                viewFlow.setAdapter(viewFlowAdapter);
                viewFlow.setmSideBuffer(5);// 实际图片张数为size大小
                viewFlow.setTimeSpan(5000);// 图片切换时间间隔
                viewFlow.setSelection(3 * 1000); // 设置初始位置
//                if (viewPager != null) {
//                    viewFlow.setViewPage(viewPager);
//                }
                flowIndicator.setViewFlow(viewFlow);
                viewFlow.setFlowIndicator(flowIndicator);
//                if (bannerList.size() > 1) {
                viewFlow.startAutoFlowTimer(); // 启动自动播放
//                }
            } else {
//                viewFlowAdapter.setData(bannerList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
