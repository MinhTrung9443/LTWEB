package vn.iotstar.dao;

import vn.iotstar.model.User;

public interface UserDao {
	User get(String username);
	void insert(User user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	User findByUserName(String username);
	boolean checkExistId(int id);
	void changePassword(String username, String password);
	void updateNPI(String userName, String fullname, String phone, String fileName);

}
