package org.redrock.wxchristmas.controller;

import org.json.JSONObject;
import org.redrock.wxchristmas.bean.User;
import org.redrock.wxchristmas.dao.UserDao;
import org.redrock.wxchristmas.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/game/rank")
public class GetRankServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl();
        ResultSet resultSet = userDao.getRank();
        JSONObject dataObj = new JSONObject();
        try {
            int cnt = 1;
            while(resultSet.next()) {
                JSONObject jsonObject  = new JSONObject();
                jsonObject.put("nickname", resultSet.getString("nickname"));
                jsonObject.put("imgurl", resultSet.getString("imgurl"));
                jsonObject.put("rank", cnt);
                dataObj.put(String.valueOf(cnt++), jsonObject);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        User my = userDao.getUserRank( (String) req.getSession().getAttribute("Id"));
        JSONObject userObject  = new JSONObject();
        userObject.put("nickname", my.getNickname());
        userObject.put("imgurl", my.getImgurl());
        userObject.put("rank", my.getRank());
        dataObj.put("my", userObject);

        JSONObject respJson = new JSONObject();
        respJson.put("status", "200");
        respJson.put("msg", "success");
        respJson.put("data", dataObj);
        resp.getWriter().write(respJson.toString());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}