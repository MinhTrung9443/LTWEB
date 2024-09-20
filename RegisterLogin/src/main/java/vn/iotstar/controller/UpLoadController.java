package vn.iotstar.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.model.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;
import vn.iotstar.ultis.Constant;

@WebServlet(

		name = "MultiPartServlet",

		urlPatterns = { "/multiPartServlet" }

)

@MultipartConfig(fileSizeThreshold = 1024 * 1024,

		maxFileSize = 1024 * 1024 * 5,

		maxRequestSize = 1024 * 1024 * 5 * 5)

public class UpLoadController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String getFileName(Part part) {

		for (String content : part.getHeader("content-disposition").split(";")) {

			if (content.trim().startsWith("filename"))

				return content.substring(content.indexOf("=") + 2, content.length() - 1);

		}

		return Constant.DEFAULT_FILENAME;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fullname =request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String fileName = "";
		String uploadPath = File.separator + Constant.UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ

		// String uploadPath = getServletContext().getRealPath("") + File.separator +
		// UPLOAD_DIRECTORY; //upload vào thư mục project

		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists())

			uploadDir.mkdir();

		try {


			for (Part part : request.getParts()) {

				fileName = getFileName(part);

				part.write(uploadPath + File.separator + fileName);

			}
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("account");
			
			UserService serv = new UserServiceImpl();
			u = serv.get(u.getUserName());
			if (u != null)
			{
				if (phone != "" && serv.checkExistPhone(phone)) {
					request.setAttribute("message", "Số điện thoại đã tồn tại!!!");
				}
				else 
				{
					if (fullname.isEmpty())
						fullname = u.getFullName();
					if (phone.isEmpty())
						phone = u.getPhone();
					serv.updateNPI(u.getUserName(),fullname,phone,fileName);
					request.setAttribute("message", "File " + fileName + " has uploaded successfully!" + fullname + phone);
				}
			}
		

		} catch (FileNotFoundException fne) {

			request.setAttribute("message", "There was an error: " + fne.getMessage());

		}
		
		

		getServletContext().getRequestDispatcher("/views/result.jsp").forward(request, response);

	}

}