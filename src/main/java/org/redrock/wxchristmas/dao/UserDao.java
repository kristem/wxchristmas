package org.redrock.wxchristmas.dao;


import org.redrock.wxchristmas.bean.User;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface UserDao {

    void addUser(User user);

    void updateInfo(User user);

    User findUserByOpenid(String openid);

    ResultSet getRank();

    User getUserRank(String openid);

    boolean isSameDay(String openid);
}
