package com.kuchipudi.rest.webservices.restfulwebservices.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.kuchipudi.rest.webservices.restfulwebservices.model.User;

@Component
/**
 * 
 * @author Kuchipudi
 *
 */
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;

	static {
		users.add(new User(++usersCount, "KuchipudiPrasadarao", LocalDate.now().minusYears(47)));
		users.add(new User(++usersCount, "KuchipudiSivaParvathi", LocalDate.now().minusYears(45)));
		users.add(new User(++usersCount, "KuchipudiSaiKrishna", LocalDate.now().minusYears(16)));
	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);

	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);

	}

	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

}
