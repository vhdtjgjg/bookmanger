package com.nowcoder.bookmanager.utils;


import com.nowcoder.bookmanager.model.User;


/**
 * Created by nowcoder on 2018/08/07 下午3:58
 */

/**
 * 用来保存当前访问者的容器。我们知道，当web程序运行在web服务器中时，都是并发的
 * 环境，拿tomcat来说，对于每一个请求tomcat都会从自己维护的线程池中选一个线程去处理这个
 * 请求。
 * ` ThreadLocal `这个类提供了一种线程id到一个泛型的绑定，你可以认为它是一个Map，
 * 当我们从里面取数据的时候，实际上是将当前的线程id作为map的key，取出之前这个线程存的
 * 东西。这里我们将User保存在里面，这样我们就能随时在程序的任何地方拿出User信息了。
 */
public class ConcurrentUtils {

  private static ThreadLocal<User> host = new ThreadLocal<>();

  public static User getHost(){
    return host.get();
  }

  public static void setHost(User user){
    host.set(user);
  }

}
