package com.condigence.medicare.services;

import com.condigence.medicare.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);
}
