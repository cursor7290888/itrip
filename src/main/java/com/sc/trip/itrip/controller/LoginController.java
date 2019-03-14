package com.sc.trip.itrip.controller;


import com.sc.trip.itrip.model.Message;
import com.sc.trip.itrip.model.ServerRespMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @ResponseBody
    @PostMapping("/login")
    public Message login(String loginName, String pwd){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginName, pwd);
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            if (subject.isAuthenticated()) {
                return new ServerRespMessage<>().success("");
            }else{
                return  new ServerRespMessage<>().error(-1,"认证失败!");
            }
        }catch (IncorrectCredentialsException | UnknownAccountException e){
            e.printStackTrace();
            return  new ServerRespMessage<>().error(-1,"认证失败!");
        }catch (LockedAccountException e){
            e.printStackTrace();
            return  new ServerRespMessage<>().error(-1,"认证失败!");
        }catch (Exception e){
            e.printStackTrace();
            return  new ServerRespMessage<>().error(-1,"认证失败!");
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String  logout(){
        SecurityUtils.getSubject().logout();
        return "loginPage";
    }
}
