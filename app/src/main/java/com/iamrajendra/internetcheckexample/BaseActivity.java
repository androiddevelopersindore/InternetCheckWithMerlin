package com.iamrajendra.internetcheckexample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.novoda.merlin.Merlin;
import com.novoda.merlin.MerlinsBeard;
import com.novoda.merlin.registerable.connection.Connectable;
import com.novoda.merlin.registerable.disconnection.Disconnectable;

/**
 * Created by rajendraverma on 30-07-2016.
 */
public class BaseActivity extends AppCompatActivity {
    private Merlin mMerlin;
    private Context mContext;
    private MerlinsBeard mMerlinsBeard;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        connectionCallBack_onConnected();
        connectionCallBack_onDisconnected();

    }

    private void connectionCallBack_onDisconnected() {
    mMerlin.registerDisconnectable(new Disconnectable() {
        @Override
        public void onDisconnect() {

        }
    });
    }

    private void connectionCallBack_onConnected() {
        mMerlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {

            }
        }


        );


    }

    private void init() {
        mContext =getApplicationContext();
        mMerlin = new Merlin.Builder().withConnectableCallbacks().build(mContext);
        mMerlinsBeard = MerlinsBeard.from(mContext);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMerlin.bind();
    }

    @Override
    protected void onPause() {
        mMerlin.unbind();
        super.onPause();
    }
    public boolean isConnected()
    {

        return mMerlinsBeard.isConnected();
    }
    public boolean isConnected_wifi()
    {

        return mMerlinsBeard.isConnectedToWifi();

    }

    public boolean isConnected_mobile()
    {

        return mMerlinsBeard.isConnectedToMobileNetwork();

    }
}
