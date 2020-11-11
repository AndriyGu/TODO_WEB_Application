package DBcatalog; /**
 * Created by AdanaC on 16.10.2020.
 */
import entitys.Project;
import entitys.Task;
import entitys.User;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DBworker {
    private static int EMPTY_OBJ = -1;
    static Connection co;
    static PreparedStatement preparedStatement = null;

    public static void conectDB(){

        try {
            File f = new File(".\\src\\main\\java\\DBcatalog\\todo.db");
            if(f.exists()) {
                System.out.println("yes");
            }


            Class.forName("org.sqlite.JDBC"); //".src\\resourses\\sqlite-jdbc-3.32.3.2.jar"
            co = DriverManager.getConnection(
                    "jdbc:sqlite:.\\src\\main\\java\\DBcatalog\\todo.db");
            //DB/todo.db
            System.out.println("conect");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void closeDB(){
        try {
            if (preparedStatement!=null){preparedStatement.close();}
            co.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Project

    public static void addProject(Project project){

        String query =
                "INSERT INTO projects (name, user_id, description) "+
                        "VALUES ('"+project.getName()+"','"+project.getUser_id()+"','"+project.getCreated()+"','"+project.getDescription()+"')";
        try {

            Statement statement = co.createStatement();
            statement.executeUpdate(query);
            System.out.println("addProject");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProject (Project project){



        if (project.getName()!=null){
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE projects SET name = ? WHERE id = ?");

                preparedStatement.setString(1,project.getName());
                preparedStatement.setInt(2,project.getId());
                int ui =preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (project.getUser_id()!=EMPTY_OBJ) {
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE projects SET user_id = ? WHERE id = ?");

                preparedStatement.setInt(1, project.getUser_id());
                preparedStatement.setInt(2, project.getId());
                int ui =preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        if (project.getDescription()!=null) {
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE projects SET description = ? WHERE id = ?");

                preparedStatement.setString(1, project.getDescription());
                preparedStatement.setInt(2, project.getId());
                int ui =preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (project.getCreated()!=EMPTY_OBJ) {
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE projects SET created = ? WHERE id = ?");

                preparedStatement.setInt(1, project.getUser_id());
                preparedStatement.setInt(2, project.getCreated());
                int ui =preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void deleteProject (Project project){

        try {
            preparedStatement = co.prepareStatement(
                    "DELETE FROM  projects WHERE id = ?");

            preparedStatement.setInt(1,project.getId());

            int ui =preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            preparedStatement = co.prepareStatement(
                    "DELETE FROM  task WHERE project_id = ?");

            preparedStatement.setInt(1,project.getId());

            int ui =preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public static Project getProjectID (int id){

        Project project = new Project();

        try {
            preparedStatement = co.prepareStatement(
                    "SELECT * FROM projects WHERE user_id = ?");

            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();


                project.setId(resultSet.getInt(1));
                project.setName(resultSet.getString(2));
                project.setUser_id(resultSet.getInt(3));
                project.setDescription(resultSet.getString(4));
                project.setCreated(resultSet.getInt(5));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return project;

    }

    public static ArrayList<Project> getProjectUser_id(int userId) {

        Project project = new Project();

        ArrayList projArr = new ArrayList();

        try {
            preparedStatement = co.prepareStatement(
                    "SELECT * FROM projects WHERE user_id = ?");

            preparedStatement.setInt(1,userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                project = new Project();
                project.setId(resultSet.getInt(1));
                project.setName(resultSet.getString(2));
                project.setUser_id(resultSet.getInt(3));
                project.setDescription(resultSet.getString(4));
                project.setCreated(resultSet.getInt(5));


                projArr.add(project);

            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return projArr;
    }



    //Task

    public static void addTask(Task task){

        String query =
                "INSERT INTO task (deadline, text, status, project_id, date_create, priority) "+
                        "VALUES ('"+task.getDedline()+"','"+task.getText()+"','"+task.getStatus()+
                        "','"+task.getProject_id()+"','"+task.getDate_crerate()+
                        "','"+task.getName()+"','"+task.getPrority()+"')";
        try {

            Statement statement = co.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTask (Task task){

        int id = task.getId();

        if (task.getDedline()!=EMPTY_OBJ){
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE task SET deadline = ? WHERE id = ?");

                preparedStatement.setInt(1,task.getDedline());
                preparedStatement.setInt(2,id);
                int ui =preparedStatement.executeUpdate();
                co.commit();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (task.getText()!=null){
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE task SET text = ? WHERE id = ?");

                preparedStatement.setString(1,task.getText());
                preparedStatement.setInt(2,id);
                int ui =preparedStatement.executeUpdate();
                co.commit();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (task.getStatus()!=EMPTY_OBJ){
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE task SET status = ? WHERE id = ?");

                preparedStatement.setInt(1,task.getStatus());
                preparedStatement.setInt(2,id);
                int ui =preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (task.getProject_id()!=EMPTY_OBJ){
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE task SET project_id = ? WHERE id = ?");

                preparedStatement.setInt(1,task.getProject_id());
                preparedStatement.setInt(2,id);
                int ui =preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        if (task.getDate_crerate()!=EMPTY_OBJ){
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE task SET date_create = ? WHERE id = ?");

                preparedStatement.setInt(1,task.getDate_crerate());
                preparedStatement.setInt(2,id);
                int ui =preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (task.getPrority()!=null){
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE task SET priority = ? WHERE id = ?");

                preparedStatement.setString(1,task.getPrority());
                preparedStatement.setInt(2,id);
                int ui =preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (task.getName()!=null){
            try {
                preparedStatement = co.prepareStatement(
                        "UPDATE task SET name = ? WHERE id = ?");

                preparedStatement.setString(1,task.getName());
                preparedStatement.setInt(2,id);
                int ui =preparedStatement.executeUpdate();
                co.commit();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    public static void deleteTask (Task task){

        try {
            preparedStatement = co.prepareStatement(
                    "DELETE FROM  task WHERE id = ?");

            preparedStatement.setInt(1,task.getId());

            int ui =preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static Task getTaskID(int task_id) {

        Task task = new Task();
        try {
            preparedStatement = co.prepareStatement(
                    "SELECT * FROM task WHERE id = ?");

            preparedStatement.setInt(1,task_id);

            ResultSet resultSet = preparedStatement.executeQuery();


                task.setId(resultSet.getInt(1));
                task.setDedline(resultSet.getInt(2));
                task.setText(resultSet.getString(3));
                task.setStatus(resultSet.getInt(4));
                task.setProject_id(resultSet.getInt(5));
                task.setDate_crerate(resultSet.getInt(6));
                task.setName(resultSet.getString(7));
                task.setPrority(resultSet.getString(8));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return task;
    }

    public static ArrayList<Task> getTasksProj_id(int proj_id) {

        Task task;

        ArrayList taskArr = new ArrayList();

        try {
            preparedStatement = co.prepareStatement(
                    "SELECT * FROM task WHERE project_id = ?");

            preparedStatement.setInt(1,proj_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                task = new Task();

                task.setId(resultSet.getInt(1));
                task.setDedline(resultSet.getInt(2));
                task.setText(resultSet.getString(3));
                task.setStatus(resultSet.getInt(4));
                task.setProject_id(resultSet.getInt(5));
                task.setDate_crerate(resultSet.getInt(6));
                task.setName(resultSet.getString(7));
                task.setPrority(resultSet.getString(8));

                taskArr.add(task);

            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return taskArr;
    }


    //User

    public static String getAutorisation(String userName){

        String result = null;
        try {

            preparedStatement = co.prepareStatement(
                    "SELECT password FROM users WHERE name = ? ");

            preparedStatement.setString(1,userName);
            Statement statement = co.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery();
            result  = resultSet.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  result;
    }



}
