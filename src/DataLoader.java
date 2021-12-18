import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DataLoader {
    private String loadedData = "", path = "";
    private File inputFile;

    public DataLoader(String fileName, String path){
        this.path = path;
        this.inputFile = new File(this.path+"\\"+fileName);
    }

    public void load(){
        //create file  if not exist
        if(!this.inputFile.exists()){
            try {
                this.inputFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //take input from file line by line
        try (Scanner fileReader = new Scanner(inputFile)) {
            while (fileReader.hasNextLine()) {
                this.loadedData += fileReader.nextLine();
                this.loadedData += '\n';
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getLoadedData(){
        return this.loadedData;
    }

    public String getPath(){
        return this.path;
    }
}
