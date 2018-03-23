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
        /**
         * provinceId : 110000
         * provinceName : 北京市
         * cities : [{"cityId":"110100","cityName":"北京","districts":[{"districtId":"110101","districtName":"东城区"},
         * {"districtId":"110102","districtName":"西城区"},{"districtId":"110103","districtName":"崇文区"},
         * {"districtId":"110104","districtName":"宣武区"},{"districtId":"110105","districtName":"朝阳区"},
         * {"districtId":"110106","districtName":"丰台区"},{"districtId":"110107","districtName":"石景山区"},
         * {"districtId":"110108","districtName":"海淀区"},{"districtId":"110109","districtName":"门头沟区"},
         * {"districtId":"110111","districtName":"房山区"},{"districtId":"110112","districtName":"通州区"},
         * {"districtId":"110113","districtName":"顺义区"},{"districtId":"110114","districtName":"昌平区"},
         * {"districtId":"110115","districtName":"大兴区"},{"districtId":"110116","districtName":"怀柔区"},
         * {"districtId":"110117","districtName":"平谷区"},{"districtId":"110228","districtName":"密云县"},
         * {"districtId":"110229","districtName":"延庆县"}]}]
         */

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
            /**
             * cityId : 110100
             * cityName : 北京
             * districts : [{"districtId":"110101","districtName":"东城区"},{"districtId":"110102",
             * "districtName":"西城区"},{"districtId":"110103","districtName":"崇文区"},{"districtId":"110104",
             * "districtName":"宣武区"},{"districtId":"110105","districtName":"朝阳区"},{"districtId":"110106",
             * "districtName":"丰台区"},{"districtId":"110107","districtName":"石景山区"},{"districtId":"110108",
             * "districtName":"海淀区"},{"districtId":"110109","districtName":"门头沟区"},{"districtId":"110111",
             * "districtName":"房山区"},{"districtId":"110112","districtName":"通州区"},{"districtId":"110113",
             * "districtName":"顺义区"},{"districtId":"110114","districtName":"昌平区"},{"districtId":"110115",
             * "districtName":"大兴区"},{"districtId":"110116","districtName":"怀柔区"},{"districtId":"110117",
             * "districtName":"平谷区"},{"districtId":"110228","districtName":"密云县"},{"districtId":"110229",
             * "districtName":"延庆县"}]
             */

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
                /**
                 * districtId : 110101
                 * districtName : 东城区
                 */

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
