package com.example.demo.conf;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.po.Permission;
import com.example.demo.po.Role;
import com.example.demo.po.User;
import com.example.demo.service.UserService;

public class TestRealm extends AuthorizingRealm {

  @Autowired
  private UserService us;

  /*
   * 权限校验
   */
  // Authorization授权，将数据库中的角色和权限授权给输入的用户名
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    // 获取登录的用户名
    String phone = (String) principalCollection.getPrimaryPrincipal();
    // 到数据库里查询要授权的内容se
    User user = us.get(phone);
    // 记录用户的所有角色和权限
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();// 权限信息
    for (Role r : user.getRoles()) {
      // 将所有的角色信息添加进来。
      simpleAuthorizationInfo.addRole(r.getName());
      for (Permission p : r.getPermissions()) {
        // 将此次遍历到的角色的所有权限拿到，添加·进来
        simpleAuthorizationInfo.addStringPermission(p.getCode());
      }
    }
    return simpleAuthorizationInfo;
  }

  /*
   * 登录校验
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
    String userName = authenticationToken.getPrincipal().toString();
    User user = us.get(userName);
    if (user == null) {
      throw new UnknownAccountException();
    }
    // if (!user.getEnabled()) {
    // throw new DisabledAccountException();
    // }
    return new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
  }

}
