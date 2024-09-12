package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.model.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;


@WebServlet(urlPatterns = {"/forgot"})
public class ForgotController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7893144794320811731L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		
		UserService service = new UserServiceImpl();
		User usr = service.get(user);
		if (usr != null)
		{
			service.changePassword(user, pass);
			String alertmss = "Đã đổi mật khẩu thành công";
			req.setAttribute("alert", alertmss);
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
		else 
		{
			String alertmss = "Username không tồn tại.";
			req.setAttribute("alert", alertmss);
			req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
		}
	}
}
