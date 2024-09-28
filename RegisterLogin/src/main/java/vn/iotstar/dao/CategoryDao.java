package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.model.CategoryModel;

public interface CategoryDao {
	void insert(CategoryModel CategoryModel);

	void update(CategoryModel CategoryModel);

	void delete(int id);

	CategoryModel findById(int id);

	CategoryModel findByName(String name);

	List<CategoryModel> findAll();

	List<CategoryModel> search(String keyword);
}
