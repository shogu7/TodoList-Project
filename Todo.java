public class Todo {

    public String task;
    private StatusValue status;

    public Todo (String task, StatusValue status) {
        this.task = task;
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public StatusValue getStatus() {
        return status;
    }

    public void setStatus(StatusValue newStatus) {
        status = newStatus;
    }

    public void Show() {
        System.out.println("task: " + task + ", status: " + getStatus());
    }
    
    @Override
    public String toString() {
        return task + " , " + status;
    }
}
