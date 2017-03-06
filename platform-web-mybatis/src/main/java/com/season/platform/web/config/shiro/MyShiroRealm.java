package com.season.platform.web.config.shiro;

import com.season.platform.web.api.model.SysUser;
import com.season.platform.web.api.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by jiyc on 2017/3/3.
 */
public class MyShiroRealm extends AuthorizingRealm {
	@Resource
	private SysUserService sysUserService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		//String userName = (String) token.getPrincipal();
		SysUser sysUser = sysUserService.selectByName(token.getUsername());
		if (sysUser == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if (!"Y".equals(sysUser.getStatus())) {
			throw new LockedAccountException(); // 帐号被锁定
		}
		// 从数据库查询出来的账号名和密码,与用户输入的账号和密码对比
		// 当用户执行登录时,在方法处理上要实现subject.login(token);
		// 然后会自动进入这个类进行认证
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得shiro自带的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				sysUser, // 用户对象
				sysUser.getPassword(), //
				getName() // realm name
		);
		return authenticationInfo;
	}


	/**
	 * 此方法调用  hasRole,hasPermission的时候才会进行回调.
	 * <p>
	 * 权限信息.(授权):
	 * 1、如果用户正常退出，缓存自动清空；
	 * 2、如果用户非正常退出，缓存自动清空；
	 * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
	 * （需要手动编程进行实现；放在service进行调用）
	 * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
	 * 调用clearCached方法；
	 * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
	 *
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser authorizingUser = (SysUser) principals.getPrimaryPrincipal();
		if (authorizingUser != null) {
			//权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

			// TODO

			return simpleAuthorizationInfo;
		}
		return null;
	}
}
