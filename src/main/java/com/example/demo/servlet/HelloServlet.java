package com.example.demo.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
public class HelloServlet extends HttpServlet {


    private String message;

    public void init() throws ServletException {
        message = "Hello World!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        try {
            response.getWriter().println("<h1>" + message + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void destroy() {
        // no-op
    }
}
