package com.docker.container.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	private String password;
	private String username;
	private int age;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles;

	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	public User() {
		this.authorities = translate(getRoles());
	}

	/**
	 * Translates the List<Role> to a List<GrantedAuthority>
	 * 
	 * @param roles
	 *            the input list of roles.
	 * @return a list of granted authorities
	 */
	private Collection<? extends GrantedAuthority> translate(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(roles!=null&&!roles.isEmpty())
		for (Role role : roles) {
			String name = role.getName().toUpperCase();
			// Make sure that all roles start with "ROLE_"
			if (!name.startsWith("ROLE_"))
				name = "ROLE_" + name;
			authorities.add(new SimpleGrantedAuthority(name));
		}
		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
