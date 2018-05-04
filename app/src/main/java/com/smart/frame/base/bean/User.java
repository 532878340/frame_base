package com.smart.frame.base.bean;

/**
 * $desc$
 *
 * @author Gjm
 * @date 2018/5/3
 */
public class User {

    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    private static User sUser;

    public static boolean isLogin(){
        return sUser == null;
    }

    public static void setUser(User user){
        sUser = user;
    }

    public static User getUser(){
        if(sUser == null){
            sUser = new User();
        }
        return sUser;
    }
}
