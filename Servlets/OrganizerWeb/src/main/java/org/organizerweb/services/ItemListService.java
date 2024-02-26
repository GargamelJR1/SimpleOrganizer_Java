package org.organizerweb.services;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.organizerweb.model.Item;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet(name = "itemList", value = "/item-list")
public class ItemListService extends HttpServlet
{
    private org.organizerweb.model.ItemList itemList;

    @Override
    public void init() {
        itemList = org.organizerweb.model.ItemList.getInstance();
        addSampleData();
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

        String sessionStarted;
        HttpSession session = request.getSession(true);
        Object sessionObj = session.getAttribute("sessionStarted");
        if (sessionObj == null) {
            sessionStarted = LocalDateTime.now().toString();
        }
        else {
            sessionStarted = (String) sessionObj;
        }
        session.setAttribute("sessionStarted", sessionStarted);

        if (request.getParameter("session-started") != null) {
            PrintWriter out = response.getWriter();
            out.println(session.getAttribute("sessionStarted"));
            return;
        }

        if (request.getParameter("font-family") != null) {
            PrintWriter out = response.getWriter();
            if (!request.getParameter("font-family").isBlank()) {
                Cookie cookie = new Cookie("font-family", request.getParameter("font-family"));
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
                out.println(request.getParameter("font-family"));
            }
            else {
                Cookie[] cookies = request.getCookies();
                boolean found = false;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("font-family")) {
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

        if (request.getParameter("background-color") != null) {
            PrintWriter out = response.getWriter();
            if (!request.getParameter("background-color").isBlank()) {
                Cookie cookie = new Cookie("background-color", request.getParameter("background-color"));
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
                out.println(request.getParameter("background-color"));
            }
            else {
                Cookie[] cookies = request.getCookies();
                boolean found = false;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("background-color")) {
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
            itemList.clearList();
        }

        if (request.getParameter("remove") != null) {
            try {
                itemList.removeItem(Integer.parseInt(request.getParameter("remove")));
            } catch (org.organizerweb.model.ItemList.NoItemException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
                return;
            }
        }

        if (request.getParameter("name") != null && request.getParameter("description") != null) {
            itemList.addItem(new Item(request.getParameter("name"), request.getParameter("description")));
        }

        if (request.getParameter("edit") != null && request.getParameter("updated-name") != null && request.getParameter("updated-description") != null) {
            try {
                itemList.changeItemName(Integer.parseInt(request.getParameter("edit")), request.getParameter("updated-name"));
                itemList.changeItemDescription(Integer.parseInt(request.getParameter("edit")), request.getParameter("updated-description"));
            } catch (org.organizerweb.model.ItemList.NoItemException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
                return;
            }
        }

        if (request.getParameter("list") != null) {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Items" + "</h1>");
            out.println("<table border=\"1\"><tr><th>Id</th><th>Name</th><th>Description</th></tr>");
            for (Item item : itemList.getList()) {
                out.println("<tr><td>" + item.getId() + "</td><td>" + item.getName() + "</td><td>" + item.getDescription() + "</td></tr>");
            }
            out.println("<button onclick=\"history.back()\">return</button>");
            out.println("</body></html>");
        }

        if (request.getParameter("id") != null) {
            try {
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h4>" + "Item" + "</h4>");
                out.println("name: " + itemList.getItem(Integer.parseInt(request.getParameter("id"))).getName() + "<br>");
                out.println("description: " + itemList.getItem(Integer.parseInt(request.getParameter("id"))).getDescription() + "<br>");
            } catch (org.organizerweb.model.ItemList.NoItemException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Item not found");
                return;
            }
        }
    }

    @Override
    public void destroy() {
    }

    private void addSampleData() {
        itemList.addItem(new Item("Item 1", "This is the first item"));
        itemList.addItem(new Item("Item 2", "This is the second item"));
        itemList.addItem(new Item("Item 3", "This is the third item"));
    }
}