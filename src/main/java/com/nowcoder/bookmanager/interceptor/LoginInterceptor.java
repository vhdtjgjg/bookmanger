package com.nowcoder.bookmanager.interceptor;

import com.nowcoder.bookmanager.model.Ticket;
import com.nowcoder.bookmanager.service.TicketService;
import com.nowcoder.bookmanager.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TicketService ticketService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //没有t票，去登陆
        String t= CookieUtils.getCookie("t",request);
        if(StringUtils.isEmpty(t)){
            response.sendRedirect("/users/login");
            return false;
        }

        //无效t票，去登陆
        Ticket ticket=ticketService.getTicket(t);
        if(ticket==null){
            response.sendRedirect("/users/login");
            return false;
        }

        //过期t票，去登陆
        if(ticket.getExpiredAt().before(new Date())){
            response.sendRedirect("/users/login");
            return false;
        }

        return true;
    }
}
