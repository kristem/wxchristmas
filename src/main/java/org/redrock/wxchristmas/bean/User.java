package org.redrock.wxchristmas.bean;

import java.util.Date;

public class User {
    /**
     * 用户昵称
     */

    private String nickname;

    /**
     * 用户微信id
     */
    private String openid;


    /**
     * 用户微信头像地址
     */

    private String imgurl;

    /**
     * 用户分数
     */

    private int score;

    /**
     * 用户剩余次数（小于40）
     */
    private int count;

    /**
     * 用户登入的日期
     */
    private String date;

    /**
     * 分享次数
     */
    private int share;

    /**
     * 用户排名
     */
    private int rank;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", rank=" + rank +
                '}';
    }
}
