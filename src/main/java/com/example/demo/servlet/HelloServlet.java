package com.example.demo.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 当引入  tomcat-embed-core 依赖之后，不需要再单独引用 javax.servlet-api 依赖， 前者已经完全包含了后者的功能。
 *
 * @author cylv
 * @date 2023/3/6 22:55
 */

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
