package servlets;

import DBcatalog.DBworker;
import JSONcustom.JsonWorker;
import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProjectUpdateServlet extends HttpServlet {
    private final AccountService accountService;

    public ProjectUpdateServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {





        //Add project to BD

        DBworker.conectDB();

        DBworker.updateProject(JsonWorker.getProject(request));

        DBworker.closeDB();


    }

}