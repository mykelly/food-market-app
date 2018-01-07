package com.food.market.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.market.R;
import com.food.market.fragment.FoodFragment;
import com.food.market.fragment.HomeFragment;
import com.food.market.fragment.OrderFragment;
import com.food.market.fragment.PersonalCenterFragment;
import com.food.market.util.HttpUtils.HttpManger;
import com.food.market.util.HttpUtils.ResponseTemplate;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends BaseActivity {

    FoodFragment foodFragment;
    HomeFragment homeFragment;
    OrderFragment orderFragment;
    PersonalCenterFragment personalCenterFragment;
    private Fragment[] fragments = new Fragment[]{homeFragment, foodFragment, orderFragment, personalCenterFragment};
    private String fragments_name[] = {"homeFragment", "foodFragment", "orderFragment", "personalCenterFragment"};
    private int currentTabIndex = 0;
    private int loginIndex = 0;
    View bottomlayout[] = new View[4];
    View[] bottomImg = new View[4];
    ImageView[] bottomTv_upimage = new ImageView[4];
    private static final int imagDrawPress[] = {R.mipmap.bottom_bar_home_pressed, R.mipmap.bottom_bar_home_pressed, R.mipmap.bottom_bar_home_pressed, R.mipmap.bottom_bar_home_pressed};
    private static final int imagDraw[] = {R.mipmap.bottom_bar_home_pressed, R.mipmap.bottom_bar_home_pressed, R.mipmap.bottom_bar_home_pressed, R.mipmap.bottom_bar_home_pressed};
    private int selCurrTitleColor = Color.parseColor("#fe6e6e");
    private int nomalCurrTitleColor = Color.parseColor("#737373");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (fragments[0] == null) {
            fragments[0] = new HomeFragment();
        }
        // 添加显示第一个fragment jkkkkkkkk
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragments[0], fragments_name[0]).show(fragments[0]).commitAllowingStateLoss();
    }

    private void initView() {
        bottomlayout[0] = findViewById(R.id.layout_container_home);//首页
        bottomlayout[1] = findViewById(R.id.layout_container_food);//美食
        bottomlayout[2] = findViewById(R.id.layout_container_order);//订单
        bottomlayout[3] = findViewById(R.id.layout_container_personalcenter);//我的

        bottomImg[0] = (View) findViewById(R.id.tv_home_home);
        bottomImg[1] = (View) findViewById(R.id.tv_home_food);
        bottomImg[2] = (View) findViewById(R.id.tv_home_order);
        bottomImg[3] = (View) findViewById(R.id.tv_home_personal);

        bottomTv_upimage[0] = (ImageView) findViewById(R.id.img_home_home_up);
        bottomTv_upimage[1] = (ImageView) findViewById(R.id.img_home_food_up);
        bottomTv_upimage[2] = (ImageView) findViewById(R.id.img_home_order_up);
        bottomTv_upimage[3] = (ImageView) findViewById(R.id.img_home_personal_up);
    }
    private void switchFragment(int index) {
//        if ((index != 0) && (index != 1) && (index != 2) && (index != 3)&& (!AccountService.getInstance().isLogin())) {
//            loginIndex = index;
            /// 如果未登录则跳转到登录界面
//            AccountService.getInstance().jumpToLogin();
//            return;
//        } else {
//            loginIndex = 0;
//        }


        if (currentTabIndex == index) {
            return;
        }
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        if (fragments[index] == null) {
            // FragmentTransaction mFragment = getSupportFragmentManager().beginTransaction();
            if (index == 0) {
                fragments[0] = new HomeFragment();
                trx.add(R.id.fragment_container, fragments[0], fragments_name[0]);
            } else if (index == 1) {
                fragments[1] = new FoodFragment();
                trx.add(R.id.fragment_container, fragments[1], fragments_name[1]);
            } else if (index == 2) {
                fragments[2] = new OrderFragment();
                trx.add(R.id.fragment_container, fragments[2], fragments_name[2]);
            } else if (index == 3) {
                fragments[3] = new PersonalCenterFragment();
                trx.add(R.id.fragment_container, fragments[3], fragments_name[3]);
            }
        }
        trx.hide(fragments[currentTabIndex]);
        trx.show(fragments[index]).commit();
        currentTabIndex = index;
        changeBottom();
    }

    private void changeBottom() {
        int currTitleColor ;
        for (int i = 0; i < bottomlayout.length ; i++) {
            if (i == currentTabIndex) {
                currTitleColor = selCurrTitleColor;
                bottomTv_upimage[i].setImageResource(imagDrawPress[i]);
            } else {
                currTitleColor = nomalCurrTitleColor;
                bottomTv_upimage[i].setImageResource(imagDraw[i]);
            }
            ((TextView) bottomImg[i]).setTextColor(currTitleColor);
        }
    }

    public void onTabClicked(View v) {
        switch (v.getId()) {
            case R.id.layout_container_home://首页
                switchFragment(0);
                break;
            case R.id.layout_container_food://美食
                switchFragment(1);
                break;
            case R.id.layout_container_order://书架
                switchFragment(2);
                break;
            case R.id.layout_container_personalcenter:
                switchFragment(3);
                break;
            default:
                break;
        }
    }
    /**
     * 获取列表数据
     */
    public void getListDate() {
        HashMap<String, Object> optionsMap = new HashMap<>();
        Observable<ResponseTemplate<Object>> observable = HttpManger.commitItsm(optionsMap);
        observable.subscribe(new Subscriber<ResponseTemplate<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseTemplate<Object> responseTemplate) {
                Log.d("optionsMap", "optionsMap--->");
            }
        });
    }
}
