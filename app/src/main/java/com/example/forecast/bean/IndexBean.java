package com.example.forecast.bean;

import java.util.List;

public class IndexBean {

    /**
     * code : 200
     * updateTime : 2024-05-16T13:13+08:00
     * fxLink : https://www.qweather.com/indices/beijing-101010100.html
     * daily : [{"date":"2024-05-16","type":"1","name":"运动指数","level":"2","category":"较适宜","text":"天气较好，较适宜进行各种运动，但因天气热，请适当减少运动时间，降低运动强度。"},{"date":"2024-05-16","type":"2","name":"洗车指数","level":"1","category":"适宜","text":"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。"},{"date":"2024-05-16","type":"3","name":"穿衣指数","level":"6","category":"热","text":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},{"date":"2024-05-16","type":"4","name":"钓鱼指数","level":"2","category":"较适宜","text":"较适合垂钓，但天气稍热，会对垂钓产生一定的影响。"},{"date":"2024-05-16","type":"5","name":"紫外线指数","level":"2","category":"弱","text":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"},{"date":"2024-05-16","type":"6","name":"旅游指数","level":"1","category":"适宜","text":"天气较好，温度适宜又有微风相伴，适宜旅游。"},{"date":"2024-05-16","type":"7","name":"过敏指数","level":"4","category":"易发","text":"天气条件易诱发过敏，易过敏人群应减少外出，外出宜穿长衣长裤并佩戴好眼镜和口罩，外出归来时及时清洁手和口鼻。"},{"date":"2024-05-16","type":"8","name":"舒适度指数","level":"2","category":"较舒适","text":"白天多云，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"},{"date":"2024-05-16","type":"9","name":"感冒指数","level":"1","category":"少发","text":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},{"date":"2024-05-16","type":"10","name":"空气污染扩散条件指数","level":"2","category":"良","text":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},{"date":"2024-05-16","type":"11","name":"空调开启指数","level":"3","category":"较少开启","text":"您将感到很舒适，一般不需要开启空调。"},{"date":"2024-05-16","type":"12","name":"太阳镜指数","level":"3","category":"必要","text":"白天太阳辐射较强，建议佩戴透射比1级且标注UV380-UV400的浅色太阳镜"},{"date":"2024-05-16","type":"13","name":"化妆指数","level":"3","category":"去油防晒","text":"建议用蜜质SPF15以上面霜打底，水质无油粉底霜。"},{"date":"2024-05-16","type":"14","name":"晾晒指数","level":"2","category":"适宜","text":"天气不错，较适宜晾晒,赶紧把久未见阳光的衣物搬出来吸收一下太阳的味道吧！"},{"date":"2024-05-16","type":"15","name":"交通指数","level":"1","category":"良好","text":"天气较好，路面干燥，交通气象条件良好，车辆可以正常行驶。"},{"date":"2024-05-16","type":"16","name":"防晒指数","level":"3","category":"中等","text":"属中等强度紫外辐射天气，外出时应注意防护，建议涂擦SPF指数高于15，PA+的防晒护肤品。"}]
     * refer : {"sources":["QWeather"],"license":["CC BY-SA 4.0"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private ReferBean refer;
    private List<DailyBean> daily;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<DailyBean> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyBean> daily) {
        this.daily = daily;
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

    public static class DailyBean {
        /**
         * date : 2024-05-16
         * type : 1
         * name : 运动指数
         * level : 2
         * category : 较适宜
         * text : 天气较好，较适宜进行各种运动，但因天气热，请适当减少运动时间，降低运动强度。
         */

        private String date;
        private String type;
        private String name;
        private String level;
        private String category;
        private String text;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
