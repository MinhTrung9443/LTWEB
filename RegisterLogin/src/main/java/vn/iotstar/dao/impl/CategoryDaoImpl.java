package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.config.dbConnectionSQL;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.model.CategoryModel;

public class CategoryDaoImpl extends dbConnectionSQL implements CategoryDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public void insert(CategoryModel CategoryModel) {

		String sql = "INSERT INTO category(cate_name,icons,active) VALUES (?,?,?)";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, CategoryModel.getCate_name());
			ps.setString(2, CategoryModel.getIcon());
			ps.setBoolean(3, CategoryModel.isActive());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CategoryModel CategoryModel) {
		String sql = "UPDATE category SET cate_name = ?, icons=? , active=? WHERE cate_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, CategoryModel.getCate_name());
			ps.setString(2, CategoryModel.getIcon());
			ps.setBoolean(3,CategoryModel.isActive());
			ps.setInt(4, CategoryModel.getCate_id());
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM category WHERE cate_id = ?";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CategoryModel findById(int id) {
		String sql = "SELECT * FROM category WHERE cate_id = ? ";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCate_id(rs.getInt("cate_id"));
				category.setCate_name(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				category.setActive(rs.getBoolean("active"));
				return category;
			}
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryModel findByName(String name) {

		String sql = "SELECT * FROM category WHERE cate_name = ? ";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCate_id(rs.getInt("cate_id"));
				category.setCate_name(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				category.setActive(rs.getBoolean("active"));
				return category;
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryModel> findAll() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM Category";
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCate_id(rs.getInt("cate_id"));
				category.setCate_name(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				category.setActive(rs.getBoolean("active"));
				categories.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public List<CategoryModel> search(String keyword) {

		return null;
	}

}
