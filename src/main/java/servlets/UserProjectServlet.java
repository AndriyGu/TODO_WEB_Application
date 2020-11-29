package servlets;

import DBcatalog.DBworker;
import JSONcustom.JsonWorker;
import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserProjectServlet  extends HttpServlet {
    private final AccountService accountService;

    public UserProjectServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        DBworker.conectDB();



    //    JsonObject jsonObj = JsonWorker.createJsonByUserID(request.getId);

        String sessionId = request.getSession().getId();
        //UserProfile profile = accountService.getUserBySessionId(sessionId);
        int i = 4;



       // if (!accountService.sessionIDisOpen(sessionId)) {
      //      response.setContentType("text/html;charset=utf-8");
      //      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      //  } else {

            String line = JsonWorker.createJsonByUserID(3);
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(JsonWorker.createJsonByUserID(3));
           // response.set
        int ik = 4;

        DBworker.closeDB();
       // }
    }

}