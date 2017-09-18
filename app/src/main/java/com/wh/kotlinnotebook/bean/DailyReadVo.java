package com.wh.kotlinnotebook.bean;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/25
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyReadVo {

    /**
     * header : {"title":"娱乐没圈圈","action":"pinksns://diarytopic/detail?topicid=208200","bg":"http://d.fenfenriji.com/web/static/index_topic/index_read_ylmqq.png","icon":"http://d.fenfenriji.com/web/static/index_topic/icon_ylmqq.png"}
     * footer : {"title":"更多","action":"pinksns://channel/recommend/diarys"}
     * type :
     * list : [{"id":3990,"title":"付辛博颖儿公开恋爱了！请收好你的狗粮！，","image":"http://img.fenfenriji.com/05/4D/97/Image/A4B6298C-9068-ED6D-DBD6-599EB171BFE1.jpg","link":"","action":"pinksns://article/info?aid=74020&author_uid=23613751","is_original":"1","user":{"uid":"23613751","nickname":"逸酱-粉粉编辑","is_verified":"1"}}]
     * start : 1503626400
     * end : 1503676799
     */
    private HeaderBean header;
    private FooterBean footer;
    private String type;
    private int start;
    private int end;
    private List<ListBean> list;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public FooterBean getFooter() {
        return footer;
    }

    public void setFooter(FooterBean footer) {
        this.footer = footer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class HeaderBean {
        /**
         * title : 娱乐没圈圈
         * action : pinksns://diarytopic/detail?topicid=208200
         * bg : http://d.fenfenriji.com/web/static/index_topic/index_read_ylmqq.png
         * icon : http://d.fenfenriji.com/web/static/index_topic/icon_ylmqq.png
         */

        private String title;
        private String action;
        private String bg;
        private String icon;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class FooterBean {
        /**
         * title : 更多
         * action : pinksns://channel/recommend/diarys
         */

        private String title;
        private String action;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }

    public static class ListBean {
        /**
         * id : 3990
         * title : 付辛博颖儿公开恋爱了！请收好你的狗粮！，
         * image : http://img.fenfenriji.com/05/4D/97/Image/A4B6298C-9068-ED6D-DBD6-599EB171BFE1.jpg
         * link :
         * action : pinksns://article/info?aid=74020&author_uid=23613751
         * is_original : 1
         * user : {"uid":"23613751","nickname":"逸酱-粉粉编辑","is_verified":"1"}
         */

        private int id;
        private String title;
        private String image;
        private String link;
        private String action;
        private String is_original;
        private UserBean user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getIs_original() {
            return is_original;
        }

        public void setIs_original(String is_original) {
            this.is_original = is_original;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * uid : 23613751
             * nickname : 逸酱-粉粉编辑
             * is_verified : 1
             */

            private String uid;
            private String nickname;
            private String is_verified;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getIs_verified() {
                return is_verified;
            }

            public void setIs_verified(String is_verified) {
                this.is_verified = is_verified;
            }
        }
    }
}
