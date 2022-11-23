

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String fname = request.getParameter("first");
		String mname = request.getParameter("middle");
		String lname = request.getParameter("last");
		String dateofbirth = request.getParameter("last");
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String school = request.getParameter("school");
		String course = request.getParameter("course");
		String stream = request.getParameter("stream");
		String mobile = request.getParameter("mobile");
		
		System.out.println(fname+ "  " + mname+ "  " + lname+ "  " + dateofbirth+ "  " + sex+ "  " + email+ "  " + address+ "  " + school + "  " + course + "  " + stream + "  " + mobile);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
