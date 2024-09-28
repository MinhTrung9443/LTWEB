package vn.iotstar.services.impl;

import java.io.File;
import java.util.List;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.model.CategoryModel;
import vn.iotstar.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(CategoryModel category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(CategoryModel newCategory) {
		CategoryModel oldCategory = categoryDao.findById(newCategory.getCate_id());
		 oldCategory.setCate_name(newCategory.getCate_name());
		 if (newCategory.getIcon() != null) {
		 // XOA ANH CU DI
		 String fileName = oldCategory.getIcon();
		 final String dir = "E:\\upload";
		 File file = new File(dir + "/category" + fileName);
		 if (file.exists()) {
		 file.delete();
		 }
		 oldCategory.setIcon(newCategory.getIcon());
		 }
		 categoryDao.update(oldCategory);
	}

	@Override
	public void delete(int id) {
		 categoryDao.delete(id);

	}

	@Override
	public CategoryModel get(int id) {
		 return categoryDao.findById(id);
	}

	@Override
	public CategoryModel get(String name) {
		 return categoryDao.findByName(name);
	}

	@Override
	public List<CategoryModel> getAll() {
		return categoryDao.findAll();
	}

	@Override
	public List<CategoryModel> search(String keyword) {
		 return categoryDao.search(keyword);
	}

}
