package com.kingja.selectorsir;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/3/23 9:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProvinceCityDistrict {


    private List<Province> provinces;

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public static class Province {

        private String provinceId;
        private String provinceName;
        private List<City> cities;

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public List<City> getCities() {
            return cities;
        }

        public void setCities(List<City> cities) {
            this.cities = cities;
        }

        public static class City {

            private String cityId;
            private String cityName;
            private List<District> districts;

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public List<District> getDistricts() {
                return districts;
            }

            public void setDistricts(List<District> districts) {
                this.districts = districts;
            }

            public static class District {

                private String districtId;
                private String districtName;

                public String getDistrictId() {
                    return districtId;
                }

                public void setDistrictId(String districtId) {
                    this.districtId = districtId;
                }

                public String getDistrictName() {
                    return districtName;
                }

                public void setDistrictName(String districtName) {
                    this.districtName = districtName;
                }
            }
        }
    }
}
