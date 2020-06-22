package com.nowcoder.bookmanager.interceptor;

import com.nowcoder.bookmanager.model.Ticket;
import com.nowcoder.bookmanager.model.User;
import com.nowcoder.bookmanager.service.TicketService;
import com.nowcoder.bookmanager.service.UserService;
import com.nowcoder.bookmanager.utils.ConcurrentUtils;
import com.nowcoder.bookmanager.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    /**
     * 为注入host信息
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {

        String t=CookieUtils.getCookie("t",request);

        if(!StringUtils.isEmpty(t)){
            Ticket ticket=ticketService.getTicket(t);
            if(ticket!=null&&ticket.getExpiredAt().after(new Date())){
                User host= userService.getUser(ticket.getUserId());
                ConcurrentUtils.setHost(host);
            }
        }


        return true;
    }
}
