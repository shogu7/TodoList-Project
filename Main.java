import java.io.IOException;

public class Main {
    public static void main(String[] args) throws StopWrittenExepection, IOException {
        MenuHandler.GetInstance().loadTask();
        MenuHandler.GetInstance().CheckInput();
    }   
}