package org.redrock.wxchristmas.controller;

import org.json.JSONObject;
import org.redrock.wxchristmas.bean.User;
import org.redrock.wxchristmas.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game/end")
public class EndServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String score = req.getParameter("score");
        String openid = (String) httpSession.getAttribute("Id");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        User user = userDaoImpl.findUserByOpenid(openid);
        if(user.getScore() < Integer.parseInt(score)) {
            user.setScore(Integer.parseInt(score));
        }
        user.setCount(user.getCount()-1);
        userDaoImpl.updateInfo(user);
        User users = userDaoImpl.getUserRank(openid);

        JSONObject dataObj = new JSONObject();
        JSONObject respJson = new JSONObject();
        dataObj.put("nickname",users.getNickname());
        dataObj.put("rank",users.getRank());
        dataObj.put("count",users.getCount());
        dataObj.put("imgurl",users.getImgurl());
        respJson.put("status", "200");
        respJson.put("msg", "success");
        respJson.put("data", dataObj.toString());
        resp.getWriter().write(respJson.toString());
    }
}
