package edu.app.business;

import java.util.List;

import javax.ejb.Local;

import edu.app.persistence.User;
@Local
public interface AuthenticationServiceLocal {
	void createUser(User user);
	List<User> findAllUsers();
	User authenticate(String login, String password);
	boolean loginExists(String login);
	User findUserByLogin(String login);
}
