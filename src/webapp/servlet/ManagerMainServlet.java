package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.beans.Stock;
import webapp.utils.ClientUtils;
import webapp.utils.MyUtils;

 
@WebServlet(urlPatterns = { "/managerMain"})
public class ManagerMainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ManagerMainServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	Connection conn = MyUtils.getStoredConnection(request);

		String handle = request.getParameter("handle");
		String content = "<h3>";
		switch (handle) {
		case "stock_by_name":
			try {
				ArrayList<Stock> stocks = ClientUtils.getStocksByName(conn);
				if (stocks.isEmpty()) {
					content += "You don't got no stock babe";
				} else {
					for (Stock s : stocks) {
						content += s + "<br />";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "stock_by_symbol":
			try {
				ArrayList<Stock> stocks = ClientUtils.getStocksByName(conn);
				if (stocks.isEmpty()) {
					content += "You don't got no stock babe";
				} else {
					for (Stock s : stocks) {
						content += s + "<br />";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			content += "Invalid Operation";
		}

		content += "</h3>";
		request.setAttribute("mainPanel", content);

        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)        
        RequestDispatcher dispatcher = this.getServletContext()
        		.getRequestDispatcher("/WEB-INF/views/MainManager.jsp");
         
        dispatcher.forward(request, response);
         
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}