package com.paymybuddy.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccessUserDetails implements UserDetails
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8732188486958290584L;
	
	private String email;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

	public AccessUserDetails(User user)
	{
		this.email = user.getEmail();
	    this.password = user.getPassword();
	    this.authorities = Arrays.stream(user.getEmail().split(email))
	    		.map(SimpleGrantedAuthority::new)
	            .collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
	        return authorities;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

	@Override
	public boolean isEnabled()
	{
	    return active;
    }
}