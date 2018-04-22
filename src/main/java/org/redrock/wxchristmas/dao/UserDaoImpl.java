package org.redrock.wxchristmas.dao;


import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.redrock.wxchristmas.bean.User;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Properties;


public class UserDaoImpl implements UserDao {
    private static Connection conn;

    public UserDaoImpl(){
        try {
            Properties properties = new Properties();
            properties.load(UserDaoImpl.class.getResourceAsStream("/config.properties"));
            String url = properties.getProperty("jdbc.url");
            String user = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            Class.forName(properties.getProperty("jdbc.driver"));
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void addUser(User user) {
        String sql="insert into users(openid , nickname , score , imgurl , counts , share , dates)  values(?,?,?,?,40,0,?)";

        try
        {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user.getOpenid());
            pstmt.setString(2,user.getNickname());
            pstmt.setInt(3,user.getScore());
            pstmt.setString(4,user.getImgurl());
            pstmt.setString(5,user.getDate());
            pstmt.executeUpdate();

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }


    public User findUserByOpenid(String openid) {
        try {
            String sql="select * from  users where openid= ?;";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,openid);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
                User user = new User();
                user.setOpenid(rs.getString("openid"));
                user.setNickname(rs.getString("nickname"));
                user.setScore(rs.getInt("score"));
                user.setImgurl(rs.getString("imgurl"));
                user.setCount(rs.getInt("counts"));
                user.setDate(rs.getString("dates"));
                user.setShare(rs.getInt("share"));
                return user;
            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateInfo(User user) {
        String reg = "UPDATE users SET score = ? , imgurl = ? , nickname = ? , counts = ? , dates = ? , share = ? WHERE openid = ?;";

        User users = findUserByOpenid(user.getOpenid());
        if (users.getOpenid().equals(user.getOpenid())) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(reg);
                pstmt.setInt(1, user.getScore());
                pstmt.setString(2, user.getImgurl());
                pstmt.setString(3, user.getNickname());
                pstmt.setInt(4, user.getCount());
                pstmt.setString(5, user.getDate());
                pstmt.setInt(6, user.getShare());
                pstmt.setString(7, user.getOpenid());
                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public ResultSet getRank() {
        String sql = "select nickname, imgurl from users order by score DESC limit 50" ;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserRank(String openid){
        String sql = "SELECT *, (SELECT count(DISTINCT score) FROM users AS b WHERE a.score<b.score)+1 AS rank " +
                "FROM users AS a WHERE a.openid = ?";
        User user = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, openid);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                user = new User();
                user.setNickname(resultSet.getString("nickname"));
                user.setImgurl(resultSet.getString("imgurl"));
                user.setRank(resultSet.getInt("rank"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean isSameDay(String openid) {
        boolean res = true;
        String sql = "SELECT lastDate from users WHERE openid = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, openid);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                Date pre = new Date(resultSet.getLong("lastDate"));
                Date cur = new Date(System.currentTimeMillis());
                System.out.println(pre.toString());
                System.out.println(cur.toString() + "\n");
                int curDay = cur.getDay();
                int curMon = cur.getMonth();
                int curYear = cur.getYear();
                int preDay = pre.getDay();
                int preMon = pre.getMonth();
                int preYear = pre.getYear();
                if(curDay != preDay || curYear != preYear || curMon != preMon) {
                    res = true;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

//    JdbcTemplate jdbcTemplate = new JdbcTemplate();
//
//
//
//    public void addUser(User user) {
//        String sql = "insert user(nickname,imgurl,openid,count,score,share) value(?,?,?,40,0,0)";
//        jdbcTemplate.update(sql, user.getNickname(), user.getImgurl(), user.getOpenid());
//    }
//
//    public void updateInfo(User user) {
//        String sql = "update user set nickname = ? , imgurl = ? where openid = ?";
//        jdbcTemplate.update(sql, user.getNickname(), user.getImgurl(), user.getOpenid());
//    }
//
//    public User findUserByOpenid(String openid) {
//        String sql = "select * from user where openid = ? ";
//        Map<String, Object> usermap = jdbcTemplate.queryForMap(sql, openid);
//        User user = null;
//        try {
//            user = User.class.newInstance();
//            for (Map.Entry<String, Object> entry : usermap.entrySet()) {
//                String key = entry.getKey();
//                Object value = entry.getValue();
//                Field field = User.class.getDeclaredField(key);
//                if (!field.isAccessible()) {
//                    field.setAccessible(true);
//                }
//                field.set(user, value);
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    public List<User> getRank(){
//        String sql = "select * from user order by score DESC limit 50";
//        List<User> userList = new ArrayList();
//        List<Map<String,Object>> rankList = jdbcTemplate.queryForList(sql);
//        for (Map<String,Object> rank:rankList) {
//            User user = null;
//            try {
//                user = User.class.newInstance();
//                for (Map.Entry<String, Object> entry : rank.entrySet()) {
//                    String key = entry.getKey();
//                    Object value = entry.getValue();
//                    Field field = User.class.getDeclaredField(key);
//                    if (!field.isAccessible()) {
//                        field.setAccessible(true);
//                    }
//                    field.set(user, value);
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (NoSuchFieldException e) {
//                e.printStackTrace();
//            }
//            rankList.add(rank);
//        }
//        return userList;
//    }
//
//    public int getUserRank(String openid){
//        String sql = "select rank from user where openid = ?";
//        int rank = jdbcTemplate.query(sql,openid);
//    }
}
