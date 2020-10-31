package servlets;

import DBcatalog.DBworker;
import JSON.JsonWorker;
import accounts.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskAddServlet extends HttpServlet {
    private final AccountService accountService;

    public TaskAddServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {








        //Add task to BD

        DBworker.conectDB();

        DBworker.addTask(JsonWorker.getTask(request));

        DBworker.closeDB();


    }

}