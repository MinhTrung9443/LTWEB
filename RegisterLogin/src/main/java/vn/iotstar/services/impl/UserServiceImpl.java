package vn.iotstar.services.impl;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.model.User;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {
	public UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.findByUserName(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
		}

	@Override
	public User get(String username) {
		return userDao.findByUserName(username);
	}
	@Override
	public void insert(User user) {
		userDao.insert(user);
		
	}
	@Override
	public boolean register(String email, String username, String fullname,String password, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		long millis=System.currentTimeMillis();
		java.sql.Date date=new java.sql.Date(millis);
		userDao.insert(new User(email, username, fullname,password,null,5,phone,date));
		return true;

	}
	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}
	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}
	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}
	
	public boolean checkExistId(int id) {
		return userDao.checkExistId(id);
	}
	@Override
	public void changePassword(String username, String password) {
		userDao.changePassword(username,password);
		
	}
	@Override
	public void updateNPI(String userName, String fullname, String phone, String fileName) {
		userDao.updateNPI(userName, fullname, phone, fileName);
		
	}
}
