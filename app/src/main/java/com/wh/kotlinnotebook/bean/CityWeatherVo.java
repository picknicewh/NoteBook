package com.wh.kotlinnotebook.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/25
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CityWeatherVo implements Serializable {

    /**
     * city : 上海
     * cityid : 101020100
     * date : 08月25日(星期五)
     * current : {"weather":"多云","temp":"29","WD":"北风","WS":"0级","SD":"80%","aqi":"30","weather_img":"d01","weather_img_url":"http://d.fenfenriji.com/web/act/weather/static/images1/d01.png","weather_bg_img":"http://img.fenfenriji.com/62/A4/44/Image/1DE16D4D-EAC3-CAA7-2F65-55A37221FEE8.jpg","update":"08:15"}
     * forcast : [{"temp":"34/26","weather":"雷阵雨","day":"今天","date":"08-25","weather_img":"d04","weather_img_url":"http://d.fenfenriji.com/web/act/weather/static/images1/d04.png"},{"temp":"31/26","weather":"雷阵雨","day":"周六","date":"08-26","weather_img":"d04","weather_img_url":"http://d.fenfenriji.com/web/act/weather/static/images1/d04.png"},{"temp":"33/27","weather":"多云","day":"周日","date":"08-27","weather_img":"d01","weather_img_url":"http://d.fenfenriji.com/web/act/weather/static/images1/d01.png"},{"temp":"33/27","weather":"晴","day":"周一","date":"08-28","weather_img":"d00","weather_img_url":"http://d.fenfenriji.com/web/act/weather/static/images1/d00.png"},{"temp":"32/25","weather":"小雨","day":"周二","date":"08-29","weather_img":"d07","weather_img_url":"http://d.fenfenriji.com/web/act/weather/static/images1/d07.png"}]
     * alarm : {}
     * action : http://d.fenfenriji.com/web/act/weather/index.html?cityid=101020100
     * servertime :
     */

    private String city;
    private String cityid;
    private String date;
    private CurrentBean current;
    private AlarmBean alarm;
    private String action;
    private String servertime;
    private List<ForcastVo> forcast;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CurrentBean getCurrent() {
        return current;
    }

    public void setCurrent(CurrentBean current) {
        this.current = current;
    }

    public AlarmBean getAlarm() {
        return alarm;
    }

    public void setAlarm(AlarmBean alarm) {
        this.alarm = alarm;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    public List<ForcastVo> getForcast() {
        return forcast;
    }

    public void setForcast(List<ForcastVo> forcast) {
        this.forcast = forcast;
    }

    public static class CurrentBean {
        /**
         * weather : 多云
         * temp : 29
         * WD : 北风
         * WS : 0级
         * SD : 80%
         * aqi : 30
         * weather_img : d01
         * weather_img_url : http://d.fenfenriji.com/web/act/weather/static/images1/d01.png
         * weather_bg_img : http://img.fenfenriji.com/62/A4/44/Image/1DE16D4D-EAC3-CAA7-2F65-55A37221FEE8.jpg
         * update : 08:15
         */
        private String weather;
        private String temp;
        private String WD;
        private String WS;
        private String SD;
        private String aqi;
        private String weather_img;
        private String weather_img_url;
        private String weather_bg_img;
        private String update;

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWD() {
            return WD;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public String getWS() {
            return WS;
        }

        public void setWS(String WS) {
            this.WS = WS;
        }

        public String getSD() {
            return SD;
        }

        public void setSD(String SD) {
            this.SD = SD;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getWeather_img() {
            return weather_img;
        }

        public void setWeather_img(String weather_img) {
            this.weather_img = weather_img;
        }

        public String getWeather_img_url() {
            return weather_img_url;
        }

        public void setWeather_img_url(String weather_img_url) {
            this.weather_img_url = weather_img_url;
        }

        public String getWeather_bg_img() {
            return weather_bg_img;
        }

        public void setWeather_bg_img(String weather_bg_img) {
            this.weather_bg_img = weather_bg_img;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }


    }

    public static class AlarmBean {
    }

}
