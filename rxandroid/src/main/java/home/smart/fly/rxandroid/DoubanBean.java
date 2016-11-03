package home.smart.fly.rxandroid;

/**
 * Created by co-mall on 2016/8/3.
 */
public class DoubanBean {

    /**
     * homepage : http://www.douban.com/people/ahbei
     * icon : http://img3.douban.com/icon/u1000001-30.jpg
     * id : 1000001
     * r : 0
     * stats : {"board":0,"bub":7,"collect":7}
     * title : 阿北
     * uid : ahbei
     */

    private String homepage;
    private String icon;
    private String id;
    private int r;
    /**
     * board : 0
     * bub : 7
     * collect : 7
     */

    private StatsBean stats;
    private String title;
    private String uid;

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public StatsBean getStats() {
        return stats;
    }

    public void setStats(StatsBean stats) {
        this.stats = stats;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public static class StatsBean {
        private int board;
        private int bub;
        private int collect;

        public int getBoard() {
            return board;
        }

        public void setBoard(int board) {
            this.board = board;
        }

        public int getBub() {
            return bub;
        }

        public void setBub(int bub) {
            this.bub = bub;
        }

        public int getCollect() {
            return collect;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }
    }
}
