package com.seraph.hrms.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.seraph.hrms.beans.UserBean;
import com.seraph.hrms.database.entity.User;
import com.seraph.hrms.database.service.UserService;
import com.seraph.hrms.utility.EncryptionUtil;

@Transactional
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        
        final User user = userService.findByUsernameAndPassword(username, EncryptionUtil.getMd5(password));
        if(user != null) {
        	user.setLastSuccessfulLogin(new Date());
        	userService.insert(user);
        	
        	final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(user.getUserType().name()));
        	return new UsernamePasswordAuthenticationToken(new UserBean(username, password, grantedAuths, user), password, grantedAuths);
        } else {
        	return null;
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
