package com.nowcoder.bookmanager.utils;

import com.nowcoder.bookmanager.model.Ticket;

import org.joda.time.DateTime;

/**
 * Created by nowcoder on 2018/08/07 下午3:31
 */

/**
 * 提供了一个生产Ticket的方法。
 *
 * 这些类封装了一些基本的常用的方法，供我们调用。
 *
 * 我们还封装了一个自己的Exception类：LoginRegisterException.java。
 *
 * HostHolder是一个重要的类，用来包装ConcurrentUtils.java的方法，
 * 并交给Spring容器去管理，
 * 使得我们可以在任何时候都能找当前的User，只要用户登录了，
 * 我们就将User信息设置到HostHolder
 * 里面，这样我们就在其他地方可以直接拿出User来用。
 */
public class TicketUtils {

  public static Ticket next(int uid){

    Ticket ticket = new Ticket();
    ticket.setTicket(UuidUtils.next());
    ticket.setUserId(uid);
    //设置t票过期时间
    DateTime expiredTime = new DateTime();
    expiredTime = expiredTime.plusMonths(3);
    ticket.setExpiredAt(expiredTime.toDate());

    return ticket;
  }

}
