package servlets;

import DBcatalog.DBworker;
import JSON.JsonWorker;
import accounts.AccountService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entitys.Project;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class ProjectAddServlet extends HttpServlet {
    private final AccountService accountService;

    public ProjectAddServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {





        //InputStreamReader isr =  new InputStreamReader(t.getRequestBody(),"utf-8");
        // BufferedReader br = new BufferedReader(isr);


        //Add project to BD

        DBworker.conectDB();

        DBworker.addProject(JsonWorker.getProject(request));

        DBworker.closeDB();


    }

}