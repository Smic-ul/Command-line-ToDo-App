import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TodoList {
    private final Map<String, Boolean> list = new TreeMap<>();

    private final String FILE_NAME = "file.txt";

    public TodoList(){
        File file = new File(FILE_NAME);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        loadItems();
    }

    private void loadItems(){
        try (Scanner scan = new Scanner(new File(FILE_NAME))) {
            while (scan.hasNextLine()){
                String[] tokens = scan.nextLine().split(":");
                list.put(tokens[1], tokens[0].equals("done"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeRecordToFile(String item, boolean done){
        try (FileWriter pw = new FileWriter(new File(FILE_NAME), true)) {
            pw.append(done ? "done" : "not done").append(":").append(item).append("\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean addRecord(String item){
        if(list.containsKey(item)) return false;
        writeRecordToFile(item, false);
        list.put(item, false);

        return true;
    }

    private void reloadFile(){
        try (FileWriter fw = new FileWriter(new File(FILE_NAME))) {
            for (Map.Entry<String, Boolean> entry: list.entrySet()) {
                writeRecordToFile(entry.getKey(), entry.getValue());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean deleteRecord(String item){
        if(!list.containsKey(item)){
            return false;
        }
        list.remove(item);
        reloadFile();

        return true;
    }

    public boolean completeTask(String item){
        if(!list.containsKey(item)){
            return false;
        }

        list.put(item, true);
        reloadFile();

        return true;
    }

    public String getTodoListAsString(){
        if(list.isEmpty()) return "List is empty";
        StringBuilder sb = new StringBuilder();
        final int[] i = {1};
        list.forEach((K, V) -> sb.append(i[0]++)
                        .append(". ").append(V ? "done\t\t" : "not done\t")
                        .append(K).append("\n"));
        return  sb.toString();
    }
}
