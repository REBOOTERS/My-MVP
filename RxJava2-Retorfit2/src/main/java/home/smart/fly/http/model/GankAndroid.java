package home.smart.fly.http.model;

import java.util.List;

/**
 * Created by engineer on 2017/12/5.
 */

public class GankAndroid {

    /**
     * error : false
     * results : [{"_id":"5a1f5e55421aa90fe725367f","createdAt":"2017-11-30T09:26:45.819Z","desc":"Activity、View、Window的理解一篇文章就够了","publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488456&idx=1&sn=4136c0a098ca71583534fb203738accc","used":true,"who":"陈宇明"},{"_id":"5a23cf60421aa90fef203577","createdAt":"2017-12-03T18:18:08.892Z","desc":"图解HashMap","images":["http://img.gank.io/35cd05e6-d27a-43ac-93e5-5e4461665ace"],"publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/12/03/%E5%9B%BE%E8%A7%A3HashMap(%E4%B8%80)/","used":true,"who":"HuYounger"},{"_id":"5a24b074421aa90fe725368a","createdAt":"2017-12-04T10:18:28.419Z","desc":"无需root，不用刷机也能使用xposed！","images":["http://img.gank.io/de2b867c-ca59-46f8-a5f9-e15c5303903f"],"publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"http://weishu.me/2017/12/02/non-root-xposed/","used":true,"who":"weishu"},{"_id":"5a255af2421aa90fe2f02cb2","createdAt":"2017-12-04T22:25:54.192Z","desc":"图解HashMap(二)","publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"http://rkhcy.github.io/2017/12/04/%E5%9B%BE%E8%A7%A3HashMap(%E4%BA%8C)/","used":true,"who":"HuYounger"},{"_id":"5a256123421aa90fef203580","createdAt":"2017-12-04T22:52:19.981Z","desc":"MultiTypeTemplates 插件发布 v1.4.0 版本，新增加支持自动生成内部类代码功能，有助于更方便实现某些代码内聚","publishedAt":"2017-12-05T08:48:31.384Z","source":"web","type":"Android","url":"https://github.com/drakeet/MultiTypeTemplates#action","used":true,"who":"drakeet"},{"_id":"5a1937af421aa90fe50c023b","createdAt":"2017-11-25T17:28:15.605Z","desc":"florent37 开源了一个很有意思的项目，MyLittleCanvas，用来替代\u201c难用\u201d的 canvas","publishedAt":"2017-11-30T13:11:10.665Z","source":"web","type":"Android","url":"https://github.com/florent37/MyLittleCanvas","used":true,"who":"drakeet"},{"_id":"5a1f5fd2421aa90fe2f02c98","createdAt":"2017-11-30T09:33:06.290Z","desc":"GitHub年度报告，2017年编程语言之争，谁与争锋？","publishedAt":"2017-11-30T13:11:10.665Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzU3NzA0ODQzMw==&mid=2247483675&idx=1&sn=b6f0a4e71d5164cf2201bcd8b6df8969","used":true,"who":"陈宇明"},{"_id":"5a137c67421aa90fef20354d","createdAt":"2017-11-21T09:07:51.275Z","desc":"iOS 和 Android 开发是否要采用 React Native?","publishedAt":"2017-11-24T11:08:03.624Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzU4MjAzNTAwMA==&mid=2247483866&idx=1&sn=b5cad7e5c26d001d920b0eff0625a995&key=6571080d88416c7220506cd2febefb7313338d6e5ef958258d4c8740d2dfc118bcca1c3edef0a834a293256d27046ecda4902f4b359f8550d6db0b150d4de080cfb515dbef6bd2654b3e3f35e3a625e7&ascene=0&uin=NTI1MzE1NDE1&devicetype=iMac+MacBookPro12%2C1+OSX+OSX+10.12.6+build(16G29)&version=12020610&nettype=WIFI&fontScale=100&pass_ticket=B5BwzwdLM8k1Q49ldLzImBZOjI9TR1ZOuqEJVskf%2B3xP0nK44%2FBkaEEXGpYDvsxG","used":true,"who":null},{"_id":"5a1627fe421aa90fe2f02c80","createdAt":"2017-11-23T09:44:30.245Z","desc":"适配三星Galaxy S8及S8+","publishedAt":"2017-11-24T11:08:03.624Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488324&idx=1&sn=e043220e37d6eaaff217769f4061c04e","used":true,"who":"陈宇明"},{"_id":"5a16379e421aa90fef203556","createdAt":"2017-11-23T10:51:10.599Z","desc":"我为Dexposed续一秒\u2014\u2014论ART上运行时 Method AOP实现","images":["http://img.gank.io/fef497ed-83ba-46f6-8a94-0e7b724e1c10"],"publishedAt":"2017-11-24T11:08:03.624Z","source":"web","type":"Android","url":"http://weishu.me/2017/11/23/dexposed-on-art/","used":true,"who":"weishu"}]
     */

    private boolean error;
    private List<ResultsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }

    public static class ResultsEntity {
        /**
         * _id : 5a1f5e55421aa90fe725367f
         * createdAt : 2017-11-30T09:26:45.819Z
         * desc : Activity、View、Window的理解一篇文章就够了
         * publishedAt : 2017-12-05T08:48:31.384Z
         * source : web
         * type : Android
         * url : https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247488456&idx=1&sn=4136c0a098ca71583534fb203738accc
         * used : true
         * who : 陈宇明
         * images : ["http://img.gank.io/35cd05e6-d27a-43ac-93e5-5e4461665ace"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "ResultsEntity{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", images=" + images +
                    '}';
        }
    }
}
