package org.organizerdb.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.organizerdb.models.Item;
import org.organizerdb.services.DBService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "itemList", value = "/organizerwebdb")
public class ItemListServlet extends HttpServlet
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

        response.setContentType("text/html");

        if (request.getParameter("fontFamily") != null) {
            PrintWriter out = response.getWriter();
            if (!request.getParameter("fontFamily").isBlank()) {
                Cookie cookie = new Cookie("fontFamily", request.getParameter("fontFamily"));
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
                out.println(request.getParameter("fontFamily"));
            }
            else {
                Cookie[] cookies = request.getCookies();
                boolean found = false;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("fontFamily")) {
                        out.println(cookie.getValue());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    out.println("serif");
                }
            }
            return;
        }

        if (request.getParameter("backgroundColor") != null) {
            PrintWriter out = response.getWriter();
            if (!request.getParameter("backgroundColor").isBlank()) {
                Cookie cookie = new Cookie("backgroundColor", request.getParameter("backgroundColor"));
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
                out.println(request.getParameter("backgroundColor"));
            }
            else {
                Cookie[] cookies = request.getCookies();
                boolean found = false;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("backgroundColor")) {
                        out.println(cookie.getValue());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    out.println("white");
                }
            }
            return;
        }

        if (request.getParameter("clear") != null) {
            dbService.deleteAllItems();
        }

        if (request.getParameter("remove") != null) {
            if (!dbService.deleteItem(Long.parseLong(request.getParameter("remove")))) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
            }
        }

        if (request.getParameter("name") != null && request.getParameter("description") != null && request.getParameter("category") != null) {
            dbService.addItem(request.getParameter("name"), request.getParameter("description"), request.getParameter("category"));
        }

        if (request.getParameter("edit") != null
                && request.getParameter("updated-name") != null
                && request.getParameter("updated-description") != null
                && request.getParameter("updated-category") != null) {
            if (!dbService.updateItem(
                    Long.parseLong(request.getParameter("edit")),
                    request.getParameter("updated-name"),
                    request.getParameter("updated-description"),
                    request.getParameter("updated-category")
            ))
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
        }

        if (request.getParameter("list") != null) {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Items" + "</h1>");
            out.println("<table border=\"1\"><tr><th>Id</th><th>Name</th><th>Description</th><th>Category</th></tr>");
            for (Item item : dbService.getAllItems()) {
                out.println("<tr>");
                out.println("<td>" + item.getId() + "</td>");
                out.println("<td>" + item.getName() + "</td>");
                out.println("<td>" + item.getDescription() + "</td>");
                out.println("<td>" + item.getCategory().getCategoryName() + "</td>");
                out.println("</tr>");
            }
            out.println("<button onclick=\"history.back()\">return</button>");
            out.println("</body></html>");
        }

        if (request.getParameter("id") != null) {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h4>" + "Item" + "</h4>");
            Item item = dbService.getItem(Long.parseLong(request.getParameter("id")));
            if (item == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
                return;
            }
            out.println("name: " + item.getName() + "<br>");
            out.println("description: " + item.getDescription() + "<br>");
            out.println("category: " + item.getCategory().getCategoryName() + "<br>");
            out.println("</body></html>");
        }
    }

    @Override
    public void destroy() {
    }

}