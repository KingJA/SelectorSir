package com.kingja.selectorsir;

import android.content.Context;

import com.kingja.selectorsir.wheelview.AbstractWheelTextAdapter;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/3/23 13:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddressTextAdapter extends AbstractWheelTextAdapter {
    private final List<AddressInfo> list;

    protected AddressTextAdapter(Context context, List<AddressInfo> list, int currentItem, int
            maxsize, int minsize) {
        super(context, R.layout.item_text, NO_RESOURCE, currentItem, maxsize, minsize);
        this.list = list;
        setItemTextResource(R.id.tempValue);
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return list.get(index).getAddressName();
    }
}
