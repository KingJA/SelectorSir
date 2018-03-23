package sample.kingja.selectorsir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kingja.selectorsir.AddressInfo;
import com.kingja.selectorsir.AddressSelector;
import com.kingja.selectorsir.wheelview.ChangeAddressDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "MainActivity: onCreate");
        AddressSelector addressSelector = new AddressSelector(this, "浙江省", "温州市", "龙湾区");
        addressSelector.setOnAddressSelectedListener(new AddressSelector.OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(AddressInfo provinceInfo, AddressInfo cityInfo, AddressInfo districtInfo) {
                Log.e(TAG, "province: " + provinceInfo.getAddressName() + " city: " + cityInfo.getAddressName() + " " +
                        "area: " + districtInfo.getAddressName() + " provinceId: " +
                        provinceInfo.getAddressId() + " cityId: " + cityInfo.getAddressId() + " areaId: " +
                        districtInfo.getAddressId());
            }
        });
        addressSelector.show();
    }

    public void show(View view) {
        AddressSelector addressSelector = new AddressSelector(this, "浙江省", "温州市", "龙湾区");
        addressSelector.setOnAddressSelectedListener(new AddressSelector.OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(AddressInfo provinceInfo, AddressInfo cityInfo, AddressInfo districtInfo) {
                Log.e(TAG, "province: " + provinceInfo.getAddressName() + " city: " + cityInfo.getAddressName() + " " +
                        "area: " + districtInfo.getAddressName() + " provinceId: " +
                        provinceInfo.getAddressId() + " cityId: " + cityInfo.getAddressId() + " areaId: " +
                        districtInfo.getAddressId());
            }
        });
        addressSelector.show();
    }
}
