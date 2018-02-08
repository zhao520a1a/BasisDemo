package com.xin.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xin.service.IUserService;
import com.xin.service.UserServiceImple;
import com.xin.vo.User;

@WebServlet( name = "/UserControl", urlPatterns={"*.do"}, 
	initParams = {
			@WebInitParam(name="success", value="success.jsp"),
			@WebInitParam(name="error", value="index.jsp")
	}
		)

public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private IUserService service = new UserServiceImple();
	private Map<String, String> map = new HashMap<String, String>();
   
    public UserControl() {
        super();
    }
   
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("success", config.getInitParameter("success"));
		map.put("error", config.getInitParameter("error"));
	}
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		login(request, response);
	}

	 
	private void login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
	    
	    RequestDispatcher dispatcher = null;
		if(service.login(user)) {
			request.getSession().setAttribute("username", username);
			dispatcher = request.getRequestDispatcher(map.get("success"));
		} else {
			dispatcher = request.getRequestDispatcher(map.get("error"));
		}
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}


}
