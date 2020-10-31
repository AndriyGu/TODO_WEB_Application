package entitys;

public class Task {
    private int id;
    private int dedline;
    private String text;
    private int status;
    private int project_id;
    private int date_crerate;
    private int prority;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDedline() {
        return dedline;
    }

    public void setDedline(int dedline) {
        this.dedline = dedline;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getDate_crerate() {
        return date_crerate;
    }

    public void setDate_crerate(int date_crerate) {
        this.date_crerate = date_crerate;
    }

    public int getPrority() {
        return prority;
    }

    public void setPrority(int prority) {
        this.prority = prority;
    }
}