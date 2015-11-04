package web;

import model.Contact;
import repository.DAOContact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 28.10.2015.
 */
@WebServlet(name = "Overview", urlPatterns = {"/overview.jsp"})
public class OverviewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = 1;
        int recordsPerPage = 5;
        if (null != req.getParameter("page")) {
            pageNumber = Integer.parseInt(req.getParameter("page"));
        }
        DAOContact daoContact = new DAOContact();
        List<Contact> contacts = daoContact.asList();
        System.out.println("i'm here");
        int numberOfPages = 1;
        numberOfPages = (int) Math.ceil(daoContact.getLastId() / recordsPerPage);
        req.setAttribute("contacts", contacts);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("numberOfPages", numberOfPages);
        req.getRequestDispatcher("overview.jsp").forward(req, resp);


    }
}
