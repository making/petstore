/*
 *    Copyright 2010-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.example.petstore.account;

import org.apache.ibatis.annotations.Mapper;

/**
 * The Interface AccountMapper.
 *
 * @author Eduardo Macarron
 */
@Mapper
public interface AccountMapper {

	Account getAccountByUsername(String username);

	void insertAccount(Account account);

	void insertProfile(Account account);

	void insertSignon(Account account);

	void updateAccount(Account account);

	void updateProfile(Account account);

	void updateSignon(Account account);

}
