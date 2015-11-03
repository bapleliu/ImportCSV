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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by Denis on 24.10.2015.
 */
@WebServlet(name = "import", urlPatterns = {"/import"})
public class ImportController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            importCSV(req);
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
            for (FileItem file : files) {
                Reader reader = new InputStreamReader(file.getInputStream());
                if (file.getName().endsWith(".csv")) {
                    CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
                    List<CSVRecord> csvRecords = parser.getRecords();
                    for (CSVRecord csvRecord : csvRecords) {
                        DAOContact daoContact = new DAOContact();
                        int currId = daoContact.getLastId() + 1;
                        daoContact.update(new Contact(
                                currId,
                                csvRecord.get("Name"),
                                csvRecord.get("Surname"),
                                csvRecord.get("Login"),
                                csvRecord.get("E-mail"),
                                csvRecord.get("Phone Number"),
                                0));
                    }
                }
            }
        } else {
            System.out.println("Multidownload not allowed.");
        }

    }
}
