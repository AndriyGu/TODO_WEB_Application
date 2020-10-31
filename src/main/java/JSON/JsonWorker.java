package JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entitys.Project;
import entitys.Task;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;

public class JsonWorker {


    public static String jsonPprepare(HttpServletRequest request)throws ServletException, IOException{


        request.setCharacterEncoding("utf-8");
        Charset.defaultCharset();


        JSONObject jsonObject;

        StringBuffer jb = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = (request.getReader());
            while ((line = reader.readLine()) != null){
                jb.append(line);}
        } catch (Exception e) { /*report an error*/ }

        try {
            jsonObject =  org.json.HTTP.toJSONObject(jb.toString());
        } catch (JSONException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }


        String resultOfPOST="";
        System.out.println("jb length "+jb.length());
        try {
            resultOfPOST = java.net.URLDecoder.decode(jb.toString(), StandardCharsets.UTF_8.name());

        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
        }

        return resultOfPOST;

    }

    public static Project getProject(HttpServletRequest request){
        Project project=null;
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            project = gson.fromJson(JsonWorker.jsonPprepare(request), Project.class);

        } catch (Exception c){
            try {
                throw new IOException("Error parsing JSON request string");
            } catch (IOException e) {

            }
        }

        return project;

    }
    public static Task getTask(HttpServletRequest request){
        Task task=null;
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            task = gson.fromJson(JsonWorker.jsonPprepare(request), Task.class);

        } catch (Exception c){
            try {
                throw new IOException("Error parsing JSON request string");
            } catch (IOException e) {

            }
        }

        return task;

    }


}