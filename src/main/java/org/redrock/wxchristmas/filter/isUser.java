package org.redrock.wxchristmas.filter;
import org.redrock.wxchristmas.bean.User;
import org.redrock.wxchristmas.dao.UserDao;
import org.redrock.wxchristmas.dao.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class isUser implements Filter {
    public FilterConfig config;

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String openid = (String) req.getSession().getAttribute("Id");
        User user = null;
        UserDao userDaoImpl = new UserDaoImpl();
        if(req.getSession().isNew() || openid == null || userDaoImpl.findUserByOpenid(openid) == null) {
            user = new User();
            Date date = new Date();
            user.setDate(String.valueOf(date.getTime()));
            user.setOpenid(openid);
            user.setImgurl("http://imgsrc.baidu.com/forum/w=580/sign=1588b7c5d739b6004dce0fbfd9503526/7bec54e736d12f2eb97e1a464dc2d56285356898.jpg");
            user.setNickname("unname");
            user.setCount(40);
            user.setScore(0);
            user.setOpenid(user.getDate() + user.getNickname());
            userDaoImpl.addUser(user);
            req.getSession().setAttribute("Id", user.getOpenid());
        }
        filterChain.doFilter(req, resp);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    public void destroy() {
        this.config = null;
    }
}
