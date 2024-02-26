package org.organizerdb.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.organizerdb.models.Category;
import org.organizerdb.services.DBService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "category", value = "/organizerwebdb/category")
public class CategoryServlet extends HttpServlet
{
    private DBService dbService;

    @Override
    public void init() {
        dbService = DBService.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("list") != null) {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Categories" + "</h1>");
            out.println("<ul>");
            for (Category category : dbService.getAllCategories()) {
                out.println("<li>" + category.getCategoryName() + "</li>");
            }
            out.println("</ul>");
            out.println("<button onclick=\"history.back()\">return</button>");
            out.println("</body></html>");
        }

        if(request.getParameter("remove") != null) {
            if(!dbService.deleteCategory(request.getParameter("remove"))){
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Category not found");
            }
        }
    }
}
