package org.organizerweb.services;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "itemListLogs", value = "/item-list-logs")
public class LogsService extends HttpServlet
{

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
        PrintWriter out = response.getWriter();
        {
            out.println("<html><body>");
            out.println("<h4>" + "Logs" + "</h4>");
            if (org.organizerweb.model.ItemList.getLogger() == null || org.organizerweb.model.ItemList.getLogger().getLogs().isEmpty())
                out.println("No logs");
            else {
                out.println("<ul>");

                for (String log : org.organizerweb.model.ItemList.getLogger().getLogs()) {
                    out.println("<li>" + log + "</li>");
                }
            }
            out.println("</ul>");
            out.println("</table>");
            out.println("</body></html>");
        }
    }

}
