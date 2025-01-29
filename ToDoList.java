import java.io.*;
import java.util.ArrayList;

public class ToDoList {

    ArrayList<Todo> taskList;
    MenuHandler menuhandler;
    Saver saver;
    Todo todo;
    
    // Map<String, Runnable> User_In = new HashMap<>(); DEMANDER AKUMA
    // User_In.put("Add", this::addTask); 
    // User_In.put("Remove", this::removeTask)
    // User_In.put("Exit", this::exitTask)

    public ToDoList(){ 

        this.saver = new Saver(this);
        menuhandler = MenuHandler.GetInstance();
        taskList = new ArrayList<>();
        
    }

    public void addTask(Todo todochange)  {
            taskList.add(todochange);
            try {
                saver.saver_toAdd();
            } catch(IOException exception) {
                System.out.print("Couldn't save new Todo.");
            }
            System.out.print("You just added " + todochange + " to your ToDo list.\n ");
    }

    public void loadtaskdefault(Todo todochange) {
        if (todochange.equals(null)) {
            System.out.print("Invalid Task input");
            menuhandler.CheckInput();
        }
        else {
            taskList.add(todochange);
            System.out.print("You just added " + todochange + " to your ToDo list.\n ");
        }
 }

public String getTaskListAsString() {
    StringBuilder result = new StringBuilder();
    for(Todo todo : taskList) {
        result.append(todo.toString()).append("\n");
    }
    return result.toString();
}

    public void showTasks() {
        System.out.println("Your current tasks are: ");
        String tasklistAsString = getTaskListAsString(); 
        System.out.println(tasklistAsString);   
    }

//    public void addTask (String task) {
//       taskList.add(task);
//     }

    public void removeTask (Todo todochange) {
        taskList.remove(todochange);
        try {
            saver.saver_toAdd(); // Will Update the new values of the Todo for the file.
        } catch(IOException exception) {
            System.out.print("Couldn't save new Todo.");
        }
        System.out.println("the task " + todochange + " from your To-Do List has been deleted.");
    }

    public void statusTask (String taskname, String status) {
        Todo todoname = FindByTask(taskname);
        if(todoname != null) {
            StatusValue statusfinal = StatusValueExtension.GetStatusValue(status);
            todoname.setStatus(statusfinal);
            System.out.println("Your new Status has succesfully been assign.");
            todoname.Show();
            try {
                saver.saver_toAdd(); // Will Update the new values of the Todo for the file.
            } catch(IOException exception) {
                System.out.print("Couldn't save new Todo.");
            }
        } else { 
            System.out.println("Invalid Task Input");
            menuhandler.CheckInput();
        }
    }

    // takes a string task as parameter, return a todo if it is contained in the list, null if not. >.<
    public Todo FindByTask(String task) {
        for(Todo todo : taskList) {
            if(todo.task.equals(task)) {
                return todo;
            }
        }
        return null;
    } 

    public Todo parsingOfTask(String task) {  

        String[] parts = task.split(",");         
        if (parts.length == 2) {
            String taskname = parts[0].trim();   
            String status = parts[1].trim();
                if (status != null){
                    StatusValue statusfinal = StatusValueExtension.GetStatusValue(status);
                    Todo todo = new Todo(taskname, statusfinal);   
                    addTask(todo); 
                } 
        } else {
            System.out.println("Error to dectect the Input, please make sure to write your Task like the Exemple : Task , Status ");
        } 
        return todo;
    }
}





