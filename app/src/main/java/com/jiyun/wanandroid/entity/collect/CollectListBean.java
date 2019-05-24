package com.jiyun.wanandroid.entity.collect;

import java.util.List;

public class CollectListBean {

    /**
     * data : {"curPage":1,"datas":[{"author":"ITGungnir","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"Kotlin + MVVM + 模块化 + 响应式 的WanAndroid客户端，简约优雅。\r\n\r\n## 技术点\r\n* 基于APT和ASM的GRouter路由框架实现模块化通信；\r\n* 参考Redux的原理，实现一套事件总线框架；\r\n* 封装MVVM和UI库，可供其他应用使用；\r\n* 项目整体使用响应式编程风格，简介优雅易读。","envelopePic":"https://www.wanandroid.com/blogimgs/d95d3a13-85ae-4aa7-bec0-ca9f4bba9eae.png","id":62613,"link":"http://www.wanandroid.com/blog/show/2575","niceDate":"10分钟前","origin":"","originId":8480,"publishTime":1558487666000,"title":"WanAndroid模块化响应式客户端","userId":24275,"visible":0,"zan":0},{"author":"Ruheng","chapterId":26,"chapterName":"基础UI控件","courseId":13,"desc":"详解Android图文混排实现。","envelopePic":"","id":62608,"link":"http://www.jianshu.com/p/6843f332c8df","niceDate":"20分钟前","origin":"","originId":1165,"publishTime":1558487085000,"title":"Android图文混排实现方式详解","userId":24275,"visible":0,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":2}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * curPage : 1
         * datas : [{"author":"ITGungnir","chapterId":294,"chapterName":"完整项目","courseId":13,"desc":"Kotlin + MVVM + 模块化 + 响应式 的WanAndroid客户端，简约优雅。\r\n\r\n## 技术点\r\n* 基于APT和ASM的GRouter路由框架实现模块化通信；\r\n* 参考Redux的原理，实现一套事件总线框架；\r\n* 封装MVVM和UI库，可供其他应用使用；\r\n* 项目整体使用响应式编程风格，简介优雅易读。","envelopePic":"https://www.wanandroid.com/blogimgs/d95d3a13-85ae-4aa7-bec0-ca9f4bba9eae.png","id":62613,"link":"http://www.wanandroid.com/blog/show/2575","niceDate":"10分钟前","origin":"","originId":8480,"publishTime":1558487666000,"title":"WanAndroid模块化响应式客户端","userId":24275,"visible":0,"zan":0},{"author":"Ruheng","chapterId":26,"chapterName":"基础UI控件","courseId":13,"desc":"详解Android图文混排实现。","envelopePic":"","id":62608,"link":"http://www.jianshu.com/p/6843f332c8df","niceDate":"20分钟前","origin":"","originId":1165,"publishTime":1558487085000,"title":"Android图文混排实现方式详解","userId":24275,"visible":0,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 2
         */

        private int curPage;
        private int offset;
        private boolean over;
        private int pageCount;
        private int size;
        private int total;
        private List<DatasBean> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * author : ITGungnir
             * chapterId : 294
             * chapterName : 完整项目
             * courseId : 13
             * desc : Kotlin + MVVM + 模块化 + 响应式 的WanAndroid客户端，简约优雅。

             ## 技术点
             * 基于APT和ASM的GRouter路由框架实现模块化通信；
             * 参考Redux的原理，实现一套事件总线框架；
             * 封装MVVM和UI库，可供其他应用使用；
             * 项目整体使用响应式编程风格，简介优雅易读。
             * envelopePic : https://www.wanandroid.com/blogimgs/d95d3a13-85ae-4aa7-bec0-ca9f4bba9eae.png
             * id : 62613
             * link : http://www.wanandroid.com/blog/show/2575
             * niceDate : 10分钟前
             * origin :
             * originId : 8480
             * publishTime : 1558487666000
             * title : WanAndroid模块化响应式客户端
             * userId : 24275
             * visible : 0
             * zan : 0
             */

            private String author;
            private int chapterId;
            private String chapterName;
            private int courseId;
            private String desc;
            private String envelopePic;
            private int id;
            private String link;
            private String niceDate;
            private String origin;
            private int originId;
            private long publishTime;
            private String title;
            private int userId;
            private int visible;
            private int zan;

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

            public int getOriginId() {
                return originId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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
        }
    }
}
