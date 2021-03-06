package com.kingja.selectorsir;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kingja.selectorsir.wheelview.OnWheelChangedListener;
import com.kingja.selectorsir.wheelview.WheelView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2018/3/23 13:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddressSelector extends Dialog implements View.OnClickListener {
    private static final String TAG = "AddressSelector";
    private String provinceName;
    private String cityName;
    private String districtName;
    private ProvinceCityDistrict provinceCityDistrict;
    private int maxsize = 20;
    private int minsize = 14;
    private List<AddressInfo> provinceInfos;
    private Map<String, List<AddressInfo>> province2CitiesMap;
    private Map<String, List<AddressInfo>> city2DistrictsMap;
    private AddressTextAdapter provinceAdapter;
    private AddressTextAdapter cityAdapter;
    private AddressTextAdapter districtAdapter;
    private List<AddressInfo> cityInfos;
    private List<AddressInfo> districtInfos;
    private AddressInfo provinceInfo;
    private AddressInfo cityInfo;
    private AddressInfo districtInfo;
    private OnAddressSelectedListener onAddressSelectedListener;


    public AddressSelector(@NonNull Context context, String provinceName, String cityName, String districtName) {
        super(context);
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.districtName = districtName;
        initJsonData();
    }

    public AddressSelector(@NonNull Context context) {
        super(context);
        initJsonData();
    }

    public void setCityName(String cityName) {
        initByCityCode(cityName);
    }

    public void setDistrictId(String districtId) {
        initByDistrictId(districtId);
    }

    private void initByCityCode(String cityName) {
        for (ProvinceCityDistrict.Province province : provinceCityDistrict.getProvinces()) {
            List<ProvinceCityDistrict.Province.City> cities = province.getCities();
            for (ProvinceCityDistrict.Province.City city : cities) {
                if (cityName.equals(city.getCityName())) {
                    this.cityName = city.getCityName();
                    this.provinceName = province.getProvinceName();
                    this.districtName = city.getDistricts().get(0).getDistrictName();
                }
            }
        }
    }

    private void initByDistrictId(String districtId) {
        for (ProvinceCityDistrict.Province province : provinceCityDistrict.getProvinces()) {
            List<ProvinceCityDistrict.Province.City> cities = province.getCities();
            for (ProvinceCityDistrict.Province.City city : cities) {
                List<ProvinceCityDistrict.Province.City.District> districts = city.getDistricts();
                for (ProvinceCityDistrict.Province.City.District district : districts) {
                    if (districtId.equals(district.getDistrictId())) {
                        this.cityName = city.getCityName();
                        this.provinceName = province.getProvinceName();
                        this.districtName = district.getDistrictName();
                    }
                }
            }
        }
    }

    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = getContext().getAssets().open("citys.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            is.close();
            provinceCityDistrict = new Gson().fromJson(sb.toString(), ProvinceCityDistrict.class);
            initAddresses(provinceCityDistrict);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAddresses(ProvinceCityDistrict provinceCityDistrict) {
        List<ProvinceCityDistrict.Province> provinces = provinceCityDistrict.getProvinces();
        provinceInfos = new ArrayList<>();
        province2CitiesMap = new HashMap<>();
        city2DistrictsMap = new HashMap<>();
        for (ProvinceCityDistrict.Province province : provinces) {
            provinceInfos.add(new AddressInfo(province.getProvinceId(), province.getProvinceName()));
            List<AddressInfo> cityInfos = new ArrayList<>();
            List<ProvinceCityDistrict.Province.City> cities = province.getCities();
            for (ProvinceCityDistrict.Province.City city : cities) {
                cityInfos.add(new AddressInfo(city.getCityId(), city.getCityName()));
                List<ProvinceCityDistrict.Province.City.District> districts = city.getDistricts();
                List<AddressInfo> districtInfos = new ArrayList<>();
                for (ProvinceCityDistrict.Province.City.District district : districts) {
                    districtInfos.add(new AddressInfo(district.getDistrictId(), district.getDistrictName()));
                }
                city2DistrictsMap.put(city.getCityName(), getDefaultInfos(districtInfos));
            }
            province2CitiesMap.put(province.getProvinceName(), getDefaultInfos(cityInfos));
        }
    }

    public List<AddressInfo> getDefaultInfos(List<AddressInfo> infos) {
        if (infos.size() > 0) {
            return infos;
        } else {
            List<AddressInfo> defaultInfos = new ArrayList<>();
            defaultInfos.add(new AddressInfo("", ""));
            return defaultInfos;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_address_selector);
        WheelView wvProvince = findViewById(R.id.wv_address_province);
        final WheelView wvCity = findViewById(R.id.wv_address_city);
        final WheelView wvDistrict = findViewById(R.id.wv_address_area);

        TextView tv_selector_confirm = findViewById(R.id.tv_selector_confirm);
        TextView tv_selector_cancel = findViewById(R.id.tv_selector_cancel);

        tv_selector_confirm.setOnClickListener(this);
        tv_selector_cancel.setOnClickListener(this);

        int provinceIndex = getAddressItemIndex(provinceName, provinceInfos);
        provinceAdapter = new AddressTextAdapter(getContext(), provinceInfos, provinceIndex,
                maxsize, minsize);
        wvProvince.setVisibleItems(5);
        wvProvince.setViewAdapter(provinceAdapter);
        wvProvince.setCurrentItem(provinceIndex);

        cityInfos = province2CitiesMap.get(provinceName);
        final int cityIndex = getAddressItemIndex(cityName, cityInfos);
        cityAdapter = new AddressTextAdapter(getContext(), cityInfos, cityIndex, maxsize, minsize);
        wvCity.setVisibleItems(5);
        wvCity.setViewAdapter(cityAdapter);
        wvCity.setCurrentItem(cityIndex);
        districtInfos = city2DistrictsMap.get(cityName);
        int districtIndex = getAddressItemIndex(districtName, districtInfos);
        districtAdapter = new AddressTextAdapter(getContext(), districtInfos, districtIndex, maxsize,
                minsize);
        wvDistrict.setVisibleItems(5);
        wvDistrict.setViewAdapter(districtAdapter);
        wvDistrict.setCurrentItem(districtIndex);

        wvProvince.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentProvinceName = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentProvinceName, provinceAdapter);


                cityInfos = province2CitiesMap.get(currentProvinceName);
                cityAdapter = new AddressTextAdapter(getContext(), cityInfos, 0, maxsize, minsize);
                wvCity.setVisibleItems(5);
                wvCity.setViewAdapter(cityAdapter);
                wvCity.setCurrentItem(0);

                districtInfos = city2DistrictsMap.get(cityInfos.get(0).getAddressName());
                districtAdapter = new AddressTextAdapter(getContext(), districtInfos, 0, maxsize, minsize);
                wvDistrict.setVisibleItems(5);
                wvDistrict.setViewAdapter(districtAdapter);
                wvDistrict.setCurrentItem(0);

                provinceInfo = getCurrentAddressInfo(currentProvinceName, provinceInfos);
                cityInfo = cityInfos.get(0);
                districtInfo = districtInfos.get(0);
            }
        });

        wvCity.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentCityName = (String) cityAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentCityName, cityAdapter);

                districtInfos = city2DistrictsMap.get(currentCityName);
                districtAdapter = new AddressTextAdapter(getContext(), districtInfos, 0, maxsize,
                        minsize);
                wvDistrict.setVisibleItems(5);
                wvDistrict.setViewAdapter(districtAdapter);
                wvDistrict.setCurrentItem(0);

                cityInfo = getCurrentAddressInfo(currentCityName, cityInfos);
                districtInfo = districtInfos.get(0);
            }
        });

        wvDistrict.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentDistrict = (String) districtAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentDistrict, districtAdapter);

                districtInfo = getCurrentAddressInfo(currentDistrict, districtInfos);
            }
        });
        provinceInfo = getCurrentAddressInfo(provinceName, provinceInfos);
        cityInfo = getCurrentAddressInfo(cityName, cityInfos);
        districtInfo = getCurrentAddressInfo(districtName, districtInfos);
    }


    public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxsize);
            } else {
                textvew.setTextSize(minsize);
            }
        }
    }

    public int getAddressItemIndex(String addressName, List<AddressInfo> addresses) {
        for (int i = 0; i < addresses.size(); i++) {
            if (addressName.equals(addresses.get(i).getAddressName())) {
                return i;
            }
        }
        return 0;
    }

    public AddressInfo getCurrentAddressInfo(String addressName, List<AddressInfo> addressInfos) {
        for (AddressInfo addressInfo : addressInfos) {
            if (addressName.equals(addressInfo.getAddressName())) {
                return addressInfo;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_selector_confirm) {
            if (onAddressSelectedListener != null) {
                onAddressSelectedListener.onAddressSelected(provinceInfo, cityInfo, districtInfo);
            }
            dismiss();
        } else if (v.getId() == R.id.tv_selector_cancel) {
            dismiss();
        }
    }

    public interface OnAddressSelectedListener {
        void onAddressSelected(AddressInfo provinceInfo, AddressInfo cityInfo, AddressInfo districtInfo);
    }

    public void setOnAddressSelectedListener(OnAddressSelectedListener onAddressSelectedListener) {
        this.onAddressSelectedListener = onAddressSelectedListener;
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);

    }
}
