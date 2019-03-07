package com.jb.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculationBackend
 */
@WebServlet("/CalculationBackend")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	public Calculator() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Keys
		Float initialValue = Float.parseFloat(request.getParameter("initialValue"));
		Float changingValue = Float.parseFloat(request.getParameter("changingValue"));
		String operatorSymbol = request.getParameter("operatorSymbol").toString();
		
		// Set response
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(Float.toString(getAnswer(initialValue, changingValue, operatorSymbol)));
		response.getWriter().flush();
		
		// Send message as test
		//PrintWriter out = response.getWriter();
		//out.println(getAnswer(initialValue, changingValue, operatorSymbol));
	}

	
	
	private float getAnswer(float initialValue, float changingValue, String operatorSymbol) {
		if (operatorSymbol.equals("plus")) {
			return initialValue + changingValue;
		} else if (operatorSymbol.equals("minus")) {
			return initialValue - changingValue;
		} else if (operatorSymbol.equals("multiply")) {
			return initialValue * changingValue;
		} else if (operatorSymbol.equals("divide")) {
			return initialValue / changingValue;
		}
		
		// Error fall through
		return 0;
	}
}
