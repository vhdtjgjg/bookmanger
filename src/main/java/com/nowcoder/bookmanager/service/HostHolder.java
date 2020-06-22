package com.nowcoder.bookmanager.service;

import com.nowcoder.bookmanager.model.User;
import com.nowcoder.bookmanager.utils.ConcurrentUtils;
import org.springframework.stereotype.Service;

@Service
public class HostHolder {

    public User getUser(){
        return ConcurrentUtils.getHost();
    }

    public void setUser(User user){
        ConcurrentUtils.setHost(user);
    }
}
