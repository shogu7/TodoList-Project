import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Saver { // Crée un fichier saver car rien a faire dans le todolist

    ToDoList todolist;

    public Saver(ToDoList todolist) {

        this.todolist = todolist;
         // menuhandler = new MenuHandler();
        // todolist = new ToDoList(menuhandler);
    }

    public void saver_toAdd() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt", false))) {
            String TaskListString = todolist.getTaskListAsString();
            String[] tasklist = TaskListString.split("\n");  
            for (String line : tasklist) {
                writer.write(line); 
                writer.newLine();
            }
            } catch (IOException WrittingFileError) {
                System.out.println("An error occurred while writing to the file: " + WrittingFileError.getMessage());
        }
    }

    public void saver_toReWritte() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt", false))) {
            String TaskListString = todolist.getTaskListAsString();
            String[] tasklist = TaskListString.split("\n");  
            for (String line : tasklist) {
                writer.write(line); 
                writer.newLine();
            }
        } catch (IOException WrittingFileError) {
                System.out.println("An error occurred while writing to the file: " + WrittingFileError.getMessage());
            }
    }
}