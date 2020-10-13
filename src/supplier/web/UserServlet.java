package supplier.web;

import supplier.dao.UserDAO;
import supplier.model.Report;
import supplier.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
	public void init() {
		userDAO = new UserDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
				case "/report-one":
					showReportFinalPrice(request, response);
					break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllSuppliers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("supplier", existingUser);
		dispatcher.forward(request, response);

	}
	private void showReportFinalPrice(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("report-one.jsp");
		List<Report> listReport = userDAO.getReport();
		request.setAttribute("listReport", listReport);
		dispatcher.forward(request, response);
	}
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException, IOException  {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String company = request.getParameter("company");
		User newUser = new User(name, email, company);

		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(newUser);
		if (!constraintViolations.isEmpty()) {
			String errors = "<ul>";
			for (ConstraintViolation<User> constraintViolation : constraintViolations) {
				errors += "<li>" + constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage()
						+ "</li>";
			}
			errors += "</ul>";
			userDAO.insertUser(newUser);
			request.setAttribute("user", newUser);
			request.setAttribute("errors", errors);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			dispatcher.forward(request, response);

		} else {
			userDAO.insertUser(newUser);
			response.sendRedirect("list");
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String company = request.getParameter("company");

		User book = new User(id, name, email, company);
		userDAO.updateUser(book);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");

	}

}
