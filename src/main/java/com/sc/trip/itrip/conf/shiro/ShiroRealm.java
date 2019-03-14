package com.sc.trip.itrip.conf.shiro;

import com.sc.trip.itrip.entity.User;
import com.sc.trip.itrip.service.UserService;
import com.sc.trip.itrip.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Lazy //就是这里，必须延时加载，根本原因是bean实例化的顺序上，shiro的bean必须要先实例化，否则@Cacheable注解无效，理论上可以用@Order控制顺序
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        // 从数据库获取对应用户名密码的用户

       User user= new User();
       user.setUserName("admin");
        user.setPassword(Md5Util.getMD5Str("123456"));
        if (user != null) {
            // 用户为禁用状态
            /*if (!user.getLoginFlag().equals("1")) {
                throw new DisabledAccountException();
            }*/
            log.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户
                    user.getPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        //获取session中的用户
        User user=(User) principals.fromRealm(this.getClass().getName()).iterator().next();
       /* List<Role> roleList = user.getRoleList();
        if(roleList!=null && roleList.size()>0){
            for(Role r:roleList){
                simpleAuthorizationInfo.addRole(r.getRole());
            }
        }
        List<Perms> permsList= user.getPermsList();
        if(permsList!=null && permsList.size()>0){
            for(Perms p:permsList){
                simpleAuthorizationInfo.addStringPermission(p.getPerm());
            }
        }*/
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addStringPermission("index");


        return simpleAuthorizationInfo;
    }
}
