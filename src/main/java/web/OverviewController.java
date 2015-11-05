package web;

import model.Contact;
import repository.DAOContact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Denis on 28.10.2015.
 */
@WebServlet(name = "OverviewController", urlPatterns = {"/overview.do"})
public class OverviewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sortStyle = "ASC";
        String columnName = "id";
        int pageNumber = 1;
        int recordsPerPage = 5;
        if (null != req.getParameter("page")) {
            pageNumber = Integer.parseInt(req.getParameter("page"));
        }
        if (null != req.getParameter("sort")) {
            sortStyle = req.getParameter("sort");
        }
        if (null != req.getParameter("column")) {
            columnName = req.getParameter("column");
        }
        DAOContact daoContact = new DAOContact();
        List<Contact> contacts
                = daoContact.asList((pageNumber - 1) * recordsPerPage, recordsPerPage, columnName, sortStyle);
        int numberOfPages = 1;
        numberOfPages = (int) Math.ceil(daoContact.getLastId() * 1.0 / recordsPerPage);
        req.setAttribute("contacts", contacts);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("numberOfPages", numberOfPages);
        req.setAttribute("sortStyle", sortStyle);
        req.setAttribute("columnName", columnName);
        RequestDispatcher dispatcher = req.getRequestDispatcher("overview.jsp");
        if (dispatcher != null) {
            dispatcher.forward(req, resp);
        }
    }
}
