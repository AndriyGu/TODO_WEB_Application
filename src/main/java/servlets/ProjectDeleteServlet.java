package servlets;

import DBcatalog.DBworker;
import JSONcustom.JsonWorker;
import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProjectDeleteServlet extends HttpServlet {
    private final AccountService accountService;

    public ProjectDeleteServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {





        //InputStreamReader isr =  new InputStreamReader(t.getRequestBody(),"utf-8");
        // BufferedReader br = new BufferedReader(isr);


        //Add project to BD

        DBworker.conectDB();

        DBworker.deleteProject(JsonWorker.getProject(request));

        DBworker.closeDB();


    }

}