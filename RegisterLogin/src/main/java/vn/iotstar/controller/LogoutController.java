package vn.iotstar.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.ultis.Constant;



@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2974540161270815253L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		session.removeAttribute("account");
		
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies)
		{
			if(Constant.COOKIE_REMEMBER.equals(cookie.getName())){
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
				break;
				
			}
		}
		resp.sendRedirect("/RegisterLogin/login");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
