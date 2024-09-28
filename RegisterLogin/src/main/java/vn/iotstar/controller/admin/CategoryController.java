package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.model.CategoryModel;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.ultis.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,

		maxFileSize = 1024 * 1024 * 5,

		maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categorys", "/admin/category/insert", "/admin/category/update",
		"/admin/category/delete", "/admin/category/search" })
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118185900565619848L;
	public CategoryService cateservice = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		if (url.contains("insert")) {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			req.getRequestDispatcher("/views/admin/categoryInsert.jsp").forward(req, resp);
		} else if (url.contains("categorys")) {
			List<CategoryModel> list = cateservice.getAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/categoryList.jsp").forward(req, resp);
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel cate = cateservice.get(id);
			req.setAttribute("cate", cate);
			req.getRequestDispatcher("/views/admin/categoryUpdate.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			cateservice.delete(Integer.parseInt(id));
			resp.sendRedirect(req.getContextPath() + "/admin/categorys");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/categorySearch.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("insert")) {
			String categoryname = req.getParameter("categoryname");
			String image = req.getParameter("image");
			String active = req.getParameter("active");
			CategoryModel cate = new CategoryModel();
			cate.setCate_name(categoryname);

			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					cate.setIcon(fname);
				} else {
					cate.setIcon(Constant.DEFAULT_FILENAME);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			cate.setActive(active.equals("true"));
			cateservice.insert(cate);
			resp.sendRedirect(req.getContextPath() + "/admin/categorys");
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("cateid"));
			String categoryname = req.getParameter("categoryname");
			String image = req.getParameter("image");
			String active = req.getParameter("active");

			CategoryModel cate = new CategoryModel();
			cate.setCate_id(id);
			cate.setCate_name(categoryname);
			cate.setIcon(image);
			cate.setActive(active.equals("true"));
			CategoryModel cateold = new CategoryModel();
			cateold = cateservice.get(id);
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					cate.setIcon(fname);
				} else {
					cate.setIcon(cateold.getIcon());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateservice.edit(cate);
			resp.sendRedirect(req.getContextPath() + "/admin/categorys");
		} else if (url.contains("search")) {
			req.getRequestDispatcher("/views/admin/categorySearch.jsp").forward(req, resp);
		}

	}

}
