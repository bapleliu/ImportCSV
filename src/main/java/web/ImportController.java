package web;

import model.Contact;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import repository.DAOContact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 24.10.2015.
 */
@WebServlet(name = "ImportController", urlPatterns = {"/import.do"})
public class ImportController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            importCSV(req);
            req.setAttribute("success", "Done!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("import.jsp");
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }

    private synchronized void importCSV(HttpServletRequest request) throws FileUploadException, IOException {
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> files = upload.parseRequest(request);
            List<CSVRecord> csvRecords = null;
            FileItem file = files.get(0);
//            for (FileItem file : files) {
            Reader reader = new InputStreamReader(file.getInputStream());
            if (file.getName().endsWith(".csv")) {
                CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL.withDelimiter(';'));
                csvRecords = parser.getRecords();
            } else {
                request.setAttribute("success", "Not .csv file, try again.");
            }
            //}
            List<Contact> contactList = new ArrayList<>();
            DAOContact daoContact = new DAOContact();
            int currId = daoContact.getLastId() + 1;
            for (CSVRecord csvRecord : csvRecords) {
                contactList.add(new Contact(
                        currId,
                        csvRecord.get(0),
                        csvRecord.get(1),
                        csvRecord.get(2),
                        csvRecord.get(3),
                        csvRecord.get(4),
                        0));
                currId++;
            }
            if (null != contactList) {
                daoContact.addAll(contactList);
            } else {
                request.setAttribute("success", "Failed.");
            }
        } else {
            request.setAttribute("success", "Multidownload not allowed.");
        }

    }
}
