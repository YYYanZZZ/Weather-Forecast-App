package com.example.forecast.bean;

import java.util.List;

public class CityBean {

    /**
     * code : 200
     * location : [{"name":"哈尔滨","id":"101050101","lat":"45.80298","lon":"126.53505","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"11","fxLink":"https://www.qweather.com/weather/harbin-101050101.html"},{"name":"双城","id":"101050102","lat":"45.37794","lon":"126.30878","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"33","fxLink":"https://www.qweather.com/weather/shuangcheng-101050102.html"},{"name":"呼兰","id":"101050103","lat":"45.88956","lon":"126.58770","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"33","fxLink":"https://www.qweather.com/weather/hulan-101050103.html"},{"name":"阿城","id":"101050104","lat":"45.53837","lon":"126.97273","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"33","fxLink":"https://www.qweather.com/weather/acheng-101050104.html"},{"name":"宾县","id":"101050105","lat":"45.75937","lon":"127.48594","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"https://www.qweather.com/weather/bin-county-101050105.html"},{"name":"依兰","id":"101050106","lat":"46.31511","lon":"129.56560","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"33","fxLink":"https://www.qweather.com/weather/yilan-101050106.html"},{"name":"巴彦","id":"101050107","lat":"46.08189","lon":"127.40360","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"33","fxLink":"https://www.qweather.com/weather/bayan-101050107.html"},{"name":"通河","id":"101050108","lat":"45.97762","lon":"128.74779","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"43","fxLink":"https://www.qweather.com/weather/tonghe-101050108.html"},{"name":"方正","id":"101050109","lat":"45.83954","lon":"128.83614","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"43","fxLink":"https://www.qweather.com/weather/fangzheng-101050109.html"},{"name":"延寿","id":"101050110","lat":"45.45565","lon":"128.33188","adm2":"哈尔滨","adm1":"黑龙江省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"43","fxLink":"https://www.qweather.com/weather/yanshou-101050110.html"}]
     * refer : {"sources":["QWeather"],"license":["QWeather Developers License"]}
     */

    private String code;
    private ReferBean refer;
    private List<LocationBean> location;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<LocationBean> getLocation() {
        return location;
    }

    public void setLocation(List<LocationBean> location) {
        this.location = location;
    }

    public static class ReferBean {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class LocationBean {
        /**
         * name : 哈尔滨
         * id : 101050101
         * lat : 45.80298
         * lon : 126.53505
         * adm2 : 哈尔滨
         * adm1 : 黑龙江省
         * country : 中国
         * tz : Asia/Shanghai
         * utcOffset : +08:00
         * isDst : 0
         * type : city
         * rank : 11
         * fxLink : https://www.qweather.com/weather/harbin-101050101.html
         */

        private String name;
        private String id;
        private String lat;
        private String lon;
        private String adm2;
        private String adm1;
        private String country;
        private String tz;
        private String utcOffset;
        private String isDst;
        private String type;
        private String rank;
        private String fxLink;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getAdm2() {
            return adm2;
        }

        public void setAdm2(String adm2) {
            this.adm2 = adm2;
        }

        public String getAdm1() {
            return adm1;
        }

        public void setAdm1(String adm1) {
            this.adm1 = adm1;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
        }

        public String getIsDst() {
            return isDst;
        }

        public void setIsDst(String isDst) {
            this.isDst = isDst;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getFxLink() {
            return fxLink;
        }

        public void setFxLink(String fxLink) {
            this.fxLink = fxLink;
        }
    }
}
