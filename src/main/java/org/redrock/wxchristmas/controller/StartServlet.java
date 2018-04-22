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

@WebServlet("/game/start")
public class StartServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String openid = (String) httpSession.getAttribute("Id");
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        User user = null;
        user = userDaoImpl.findUserByOpenid(openid);
        req.getSession().setAttribute("Id", user.getOpenid());

        JSONObject responseJson = new JSONObject();
        JSONObject data = new JSONObject();
        responseJson.put("status","200");
        responseJson.put("msg","success");
        data.put("nickname", user.getNickname());
        data.put("rank", user.getRank());
        data.put("share",user.getShare());
        data.put("count", user.getCount());
        data.put("imgurl", user.getImgurl());
        responseJson.put("data", data);
        resp.getWriter().write(responseJson.toString());
    }
}
