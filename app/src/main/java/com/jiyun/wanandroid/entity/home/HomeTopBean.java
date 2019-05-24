package com.jiyun.wanandroid.entity.home;

import java.util.List;

public class HomeTopBean {


    /**
     * data : [{"apkLink":"","author":"鸿洋","chapterId":449,"chapterName":"技术周刊","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8389,"link":"https://www.wanandroid.com/blog/show/2561","niceDate":"2019-05-19","origin":"","prefix":"","projectLink":"","publishTime":1558198015000,"superChapterId":298,"superChapterName":"原创文章","tags":[],"title":"各大互联网公司对外分享链接汇总 | 不可抗力，MockAPI永久下线","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"xiaoyang","chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>思考一下如果捕获一个 Activity页面上所有的点击行为？<\/p><br><p>1. 有哪些可选方案？<\/p><br><p>2. 分别有什么优缺点？<\/p><br><p>3. 注意别忘了动态增加的 View，以及 Dialog 等。<\/p>","envelopePic":"","fresh":true,"id":8476,"link":"https://www.wanandroid.com/wenda/show/8476","niceDate":"13小时前","origin":"","prefix":"","projectLink":"","publishTime":1558454403000,"superChapterId":440,"superChapterName":"问答","tags":[],"title":"每日一问 思考一下如果捕获一个 Activity页面上所有的点击行为？","type":1,"userId":2,"visible":1,"zan":6}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * apkLink :
         * author : 鸿洋
         * chapterId : 449
         * chapterName : 技术周刊
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : false
         * id : 8389
         * link : https://www.wanandroid.com/blog/show/2561
         * niceDate : 2019-05-19
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1558198015000
         * superChapterId : 298
         * superChapterName : 原创文章
         * tags : []
         * title : 各大互联网公司对外分享链接汇总 | 不可抗力，MockAPI永久下线
         * type : 1
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<?> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "apkLink='" + apkLink + '\'' +
                    ", author='" + author + '\'' +
                    ", chapterId=" + chapterId +
                    ", chapterName='" + chapterName + '\'' +
                    ", collect=" + collect +
                    ", courseId=" + courseId +
                    ", desc='" + desc + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", fresh=" + fresh +
                    ", id=" + id +
                    ", link='" + link + '\'' +
                    ", niceDate='" + niceDate + '\'' +
                    ", origin='" + origin + '\'' +
                    ", prefix='" + prefix + '\'' +
                    ", projectLink='" + projectLink + '\'' +
                    ", publishTime=" + publishTime +
                    ", superChapterId=" + superChapterId +
                    ", superChapterName='" + superChapterName + '\'' +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    ", visible=" + visible +
                    ", zan=" + zan +
                    ", tags=" + tags +
                    '}';
        }
    }
}