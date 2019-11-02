package com.itex.ceva.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.itex.ceva.BaseApplication;
import com.itex.ceva.R;
import com.itex.ceva.databinding.ActivityMainBinding;
import com.itex.ceva.network.ServiceResultReceiver;
import com.itex.ceva.network.background.GetCevaDataService;
import com.itex.ceva.util.DialogUtil;
import com.itex.ceva.util.ToastUtil;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ServiceResultReceiver.Receiver {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private DialogUtil dialogUtil;
    private ServiceResultReceiver mServiceResultReceiver;
    private KProgressHUD kProgressHUD;
    private int position = 1;
    private Handler handler;

    @Inject Context context;
    @Inject Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ((BaseApplication)getApplication()).getAppComponent().inject(this);

        dialogUtil = new DialogUtil(this, null);
        mServiceResultReceiver = new ServiceResultReceiver(new Handler());
        mServiceResultReceiver.setReceiver(this);
        handler = new Handler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.play.setOnClickListener(view -> {
            kProgressHUD = dialogUtil.showNonCloseableProgress();
            GetCevaDataService.startActionRefresh(this, GetCevaDataService.TYPE_1, mServiceResultReceiver);
        });
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        if (resultCode == GetCevaDataService.SHOW_RESULT){
            if (resultData != null) {
                switch (position){
                    case 1:
                        kProgressHUD.dismiss();
                        String val = resultData.getString(GetCevaDataService.KEY);
                        binding.text.setText(val);
                        GetCevaDataService.startActionRefresh(context, GetCevaDataService.TYPE_2, mServiceResultReceiver);
                        position++;
                        break;
                    case 2:
                        handler.postDelayed(() -> {
                            List<String> vals = (List<String>) resultData.getSerializable(GetCevaDataService.KEY);
                            new Thread(() ->{
                                StringBuilder finalVal = new StringBuilder();
                                for (String value : Objects.requireNonNull(vals)){
                                    finalVal.append(" ").append(value);
                                }
                                handler.post(() -> {
                                    binding.text.setText(finalVal.toString());
                                    GetCevaDataService.startActionRefresh(context, GetCevaDataService.TYPE_3, mServiceResultReceiver);
                                    position++;
                                });
                            }).start();
                        }, 2000);
                        break;
                    case 3:
                        handler.postDelayed(() -> {
                            int all_val = resultData.getInt(GetCevaDataService.KEY);
                            binding.text.setText(String.valueOf(all_val));
                            position = 1;
                            toast.show();
                        }, 2000);
                        break;
                }
            }
        }else if (resultCode == GetCevaDataService.ERROR){
            if (resultData != null) {
                kProgressHUD.dismiss();
                ToastUtil.makeToast(this, resultData.getString(GetCevaDataService.KEY));
                toast.show();
            }
        }
    }
}
