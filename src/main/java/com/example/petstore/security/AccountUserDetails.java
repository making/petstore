package com.example.petstore.security;

import com.example.petstore.account.Account;
import com.example.petstore.catalog.Product;
import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record AccountUserDetails(Account account, List<Product> myList) implements UserDetails {

	@Serial
	private static final long serialVersionUID = 1L;

	public AccountUserDetails(Account account, List<Product> myList) {
		this.account = account;
		this.myList = new CopyOnWriteArrayList<>(myList);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

}
