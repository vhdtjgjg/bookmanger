package com.nowcoder.bookmanager.utils;

import java.security.MessageDigest;

/**
 * Created by nowcoder on 2018/08/07 下午2:30
 */

/**
 * 这个类就是用来加密的。服务器不保存用户的明文密码是一项基本常识，所以我们用MD5来加密。
 * 这里也不要专注于MD5的具体实现方法，这不是我们的主要任务，但建议你至少要知道MD5常用
 * 在什么地方，并知道这个加密是不可逆的就可以了。
 */
public class MD5 {

  public static String next(String key) {
    char hexDigits[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    try {
      byte[] btInput = key.getBytes();
      // 获得MD5摘要算法的 MessageDigest 对象
      MessageDigest mdInst = MessageDigest.getInstance("MD5");
      // 使用指定的字节更新摘要
      mdInst.update(btInput);
      // 获得密文
      byte[] md = mdInst.digest();
      // 把密文转换成十六进制的字符串形式
      int j = md.length;
      char str[] = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; i++) {
        byte byte0 = md[i];
        str[k++] = hexDigits[byte0 >>> 4 & 0xf];
        str[k++] = hexDigits[byte0 & 0xf];
      }
      return new String(str);
    } catch (Exception e) {
      //TODO log

      return null;
    }
  }
}
