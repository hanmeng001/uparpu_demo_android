package com.testjcenter.uparpu.testjcenter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.uparpu.api.AdError;
import com.uparpu.banner.api.UpArpuBannerListener;
import com.uparpu.banner.api.UpArpuBannerView;

import java.util.HashMap;

public class BannerAdActivity extends Activity {

    private static String TAG = "BannerAdActivity";
    String unitIds[] = new String[]{
            DemoApplicaion.mPlacementId_banner_all
            , DemoApplicaion.mPlacementId_banner_admob
            , DemoApplicaion.mPlacementId_banner_GDT
            , DemoApplicaion.mPlacementId_banner_toutiao
            , DemoApplicaion.mPlacementId_banner_uniplay
            , DemoApplicaion.mPlacementId_banner_facebook
            , DemoApplicaion.mPlacementId_banner_inmobi
            , DemoApplicaion.mPlacementId_banner_flurry
            , DemoApplicaion.mPlacementId_banner_applovin
            , DemoApplicaion.mPlacementId_banner_mopub
            , DemoApplicaion.mPlacementId_banner_mobpower
            , DemoApplicaion.mPlacementId_banner_appnext
            , DemoApplicaion.mPLacementId_banner_baidu
            , DemoApplicaion.mPlacementId_banner_nend
    };

    String unitGroupName[] = new String[]{
            "All",
            "Admob",
            "GDT",
            "Toutiao",
            "Uniplay",
            "Facebook",
            "Inmobi",
            "Flurry",
            "Applovin",
            "Mopub",
            "Mobpower",
            "appnext",
            "Baidu",
            "Nend"
    };

    UpArpuBannerView mBannerView;

    int mCurrentSelectIndex;
    boolean hasAddBannerView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_banner);

        Spinner spinner = (Spinner) findViewById(R.id.banner_spinner);
        final FrameLayout frameLayout = findViewById(R.id.adview_container);
        mBannerView = new UpArpuBannerView(this);
        mBannerView.setBannerAdListener(new UpArpuBannerListener() {
            @Override
            public void onBannerLoaded() {
                Toast.makeText(BannerAdActivity.this,
                        "onBannerLoaded",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBannerLoaded: ");
                if (!hasAddBannerView) {
                    frameLayout.addView(mBannerView);
                    hasAddBannerView = true;
                }
            }

            @Override
            public void onBannerFailed(AdError adError) {
                Toast.makeText(BannerAdActivity.this,
                        "onBannerFailed",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBannerFailed: "+adError.printStackTrace());
            }

            @Override
            public void onBannerClicked() {
                Toast.makeText(BannerAdActivity.this,
                        "onBannerClicked",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBannerClicked: ");
            }

            @Override
            public void onBannerShow() {
                Toast.makeText(BannerAdActivity.this,
                        "onBannerShow",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBannerShow: ");
            }

            @Override
            public void onBannerClose() {
                Toast.makeText(BannerAdActivity.this,
                        "onBannerClose",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBannerClose: ");
            }

            @Override
            public void onBannerAutoRefreshed() {
                Toast.makeText(BannerAdActivity.this,
                        "onBannerAutoRefreshed",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBannerAutoRefreshed: ");
            }

            @Override
            public void onBannerAutoRefreshFail(AdError adError) {
                Toast.makeText(BannerAdActivity.this,
                        "onBannerAutoRefreshFail",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onBannerAutoRefreshFail: "+adError.printStackTrace());
            }

        });

        // 声明一个ArrayAdapter用于存放简单数据
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                BannerAdActivity.this, android.R.layout.simple_spinner_dropdown_item,
                unitGroupName);
        // 把定义好的Adapter设定到spinner中
        spinner.setAdapter(adapter);
        // 为第一个Spinner设定选中事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // 在选中之后触发
                Toast.makeText(BannerAdActivity.this,
                        parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                mCurrentSelectIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 这个一直没有触发，我也不知道什么时候被触发。
                //在官方的文档上说明，为back的时候触发，但是无效，可能需要特定的场景
            }
        });


        findViewById(R.id.loadAd_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> maps = new HashMap<>();
                mBannerView.setUnitId(unitIds[mCurrentSelectIndex]);
                mBannerView.loadAd();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
