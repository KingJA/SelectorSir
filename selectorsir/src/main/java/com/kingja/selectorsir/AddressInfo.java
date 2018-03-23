package com.kingja.selectorsir;

/**
 * Description:TODO
 * Create Time:2018/3/23 13:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddressInfo {
    private String addressId;
    private String addressName;

    public AddressInfo(String addressId, String addressName) {
        this.addressId = addressId;
        this.addressName = addressName;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
