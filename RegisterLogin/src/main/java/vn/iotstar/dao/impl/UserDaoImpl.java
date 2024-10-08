package vn.iotstar.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import vn.iotstar.config.dbConnectionSQL;
import vn.iotstar.dao.UserDao;
import vn.iotstar.model.User;


public class UserDaoImpl implements UserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public User findByUserName(String username) {
		conn = new dbConnectionSQL().getConnection();
		User user = null;
		if (conn == null)
		{
			System.out.println("null rồi");
			return user;
		}
		String sql = "SELECT * FROM [User] WHERE username = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setFullName(rs.getString("fullname"));
				user.setPassWord(rs.getString("password"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedate(rs.getDate("createdate"));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public User get(String username) {
		return this.findByUserName(username);
	}
	@Override
	public void insert(User user) {
		String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid,phone,createdate) VALUES (?,?,?,?,?,?,?,?)";
			try {
			conn = new dbConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getFullName());
			ps.setString(4, user.getPassWord());
			ps.setString(5, user.getAvatar());
			ps.setInt(6,user.getRoleid());
			ps.setString(7,user.getPhone());
			ps.setDate(8, user.getCreatedate());
			ps.executeUpdate();
			} catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from [User] where email = ?";
		try {
		conn = new dbConnectionSQL().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from [User] where username = ?";
		try {
		conn = new dbConnectionSQL().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from [User] where phone = ?";
		try {
		conn = new dbConnectionSQL().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, phone);
		rs = ps.executeQuery();
		if (rs.next()) {
			duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	
	@Override
	public boolean checkExistId(int id) {
		boolean duplicate = false;
		String query = "select * from [User] where id = ?";
		try {
		conn = new dbConnectionSQL().getConnection();
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if (rs.next()) {
			duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	@Override
	public void changePassword(String username, String password) {
		String query = "update [User] set password= ? where username= ?";
		try {
			conn = new dbConnectionSQL().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, password);
			ps.setString(2, username);
			rs = ps.executeQuery();
			ps.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateNPI(String userName, String fullname, String phone, String fileName) {
		String query = "update [User] set fullname = ?, phone = ?, avatar = ? where username = ?";
		try {
			conn = new dbConnectionSQL().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, fullname);
			ps.setString(2, phone);
			ps.setString(3, fileName);
			ps.setString(4, userName);
			rs = ps.executeQuery();
			ps.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
