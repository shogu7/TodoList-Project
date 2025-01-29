import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
 
public class MenuHandler {

    Scanner scanner;
    boolean start = true;
    ToDoList todolist;
    Todo todo;

    //#region Singleton
    private static MenuHandler INSTANCE;

    public static MenuHandler GetInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MenuHandler();
        }
        return INSTANCE;
    }
    //#endregion

    private MenuHandler(){ 
        //#region Singleton
        if(INSTANCE != null) {
            return;
        }
        INSTANCE = this;
        //#endregion
 
        todolist = new ToDoList();
        scanner = new Scanner(System.in);
    }

    //#region  loading of task
    public void loadTask() {
        try {
            File fileSave = new File("tasks.txt");
            if (fileSave.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileSave))) {
                    String line;
                    while ((line = reader.readLine()) != null) { 
                        String[] parts = line.split(","); 
                        StatusValue statusfinal = StatusValue.Neutral;
                        if (parts.length == 2) {
                            String taskname = parts[0].trim();   
                            String status = parts[1].trim();
                            if (status != null){
                                statusfinal = StatusValueExtension.GetStatusValue(status);  
                            } else { 
                                System.out.println("This task : " + line + " do not have Status "); 
                            }  
                            Todo todo = new Todo(taskname, statusfinal); 
                            todolist.loadtaskdefault(todo); 
                        }   
                    }
                }
            }
        } catch (IOException ReadingFileError) {
            System.out.println("Error reading the file.");
        }
    } 
    //#endregion

    public void CheckInput() {
        Todo todochange;
        
        while(start){
            String userInput = GetActionFromUserInput();
            switch(userInput) {
                case "status":
                    System.out.println("Which Task do you want to assign a new status ? ");
                    String taskNameInput = ListenToInput();
                    System.out.println("Whats status do you want to assign to this task? Completed / Neutral / Dropped");
                    String status = ListenToInput();
                    todolist.statusTask(taskNameInput , status);
                    break;

                case "add":
                    System.out.println("What's the name of your new Task ? ");
                    String todoInputs = ListenToInput();
                    if(todoInputs == null) {
                        System.out.println("Invalid Input");
                    } else {
                        todolist.parsingOfTask(todoInputs);
                    }
                    break;

                case "remove":
                    System.out.println("Which Task do you want to Remove ? ");
                    todochange = getTaskFromUserInput();
                    if(todochange != null) {
                        todolist.removeTask(todochange);
                    } else {
                        System.out.println("Task not found");
                    }
                    break;

                case "show":
                    todolist.showTasks();
                    break;

                case "exit":
                    exitProg();
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }        
        }
    }
    //#region Input of user function
    public Todo getTaskFromUserInput() {
        String userInput = ListenToInput();
        Todo todoToChange = todolist.FindByTask(userInput);
        return todoToChange;
    }

    public String GetActionFromUserInput() {
        System.out.println("\nWhat do you want to do ? : ( show - add - remove - status - exit)");
        String userInput = ListenToInput();
        return userInput;
    }

    public String ListenToInput() {
        String userInput = scanner.nextLine();
        return userInput;
    }

    //#endregion
    
    public void exitProg() {
        System.out.println("Program Stop, Exit");
        start = false;
    }
}

