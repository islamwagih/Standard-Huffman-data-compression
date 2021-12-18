import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class DictionaryReader {
    private File dicFile;
    private String[] entries;
    private boolean loaded;
    private HashMap<Character, String> codesDict;

    public DictionaryReader(String fileName, String path){
        this.dicFile = new File(path+"\\"+fileName);
        this.loaded = false;
        this.codesDict = new HashMap<>();
    }

    private void read(){
        if(this.loaded) return;

        String data = "";
        try (Scanner myReader = new Scanner(this.dicFile)) {
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.entries = data.split(",");
        for(int i=0;i<entries.length;i++){
            String[] pr = entries[i].split(":");
            char key='0';
            String val;
            if(pr[0].length() == 0){
                key = '\n';
            }else{
                key = pr[0].charAt(0);
            }
            val = pr[1];
            this.codesDict.put(key, val);
        }

        if(!data.isEmpty()) loaded = true;
    }

    public HashMap<Character, String> getCodesDict(){
        this.read();
        return this.codesDict;
    }




}
