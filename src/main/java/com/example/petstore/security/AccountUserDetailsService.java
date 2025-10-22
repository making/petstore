package com.example.petstore.security;

import com.example.petstore.account.Account;
import com.example.petstore.account.AccountMapper;
import com.example.petstore.catalog.Product;
import com.example.petstore.catalog.ProductMapper;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

	private final AccountMapper accountMapper;

	private final ProductMapper productMapper;

	public AccountUserDetailsService(AccountMapper accountMapper, ProductMapper productMapper) {
		this.accountMapper = accountMapper;
		this.productMapper = productMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = this.accountMapper.getAccountByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException(username + " is not found.");
		}
		List<Product> myList = this.productMapper.getProductListByCategory(account.getFavouriteCategoryId());
		return new AccountUserDetails(account, myList);
	}

	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		Account account = ((AccountUserDetails) user).account();
		account.setPassword(newPassword);
		this.accountMapper.updateSignon(account);
		return new AccountUserDetails(account, ((AccountUserDetails) user).myList());
	}

}
