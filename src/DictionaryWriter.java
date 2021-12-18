import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DictionaryWriter {
    private File dictFile;
    private HashMap<Character, String> codesDict;
    private String data;

    public DictionaryWriter(String data, HashMap<Character, String> codesDict, String fileName, String path){
        this.dictFile = new File(path+"\\"+fileName);
        this.codesDict = codesDict;
        this.data = data;
    }

    public void write(){
        if(!dictFile.exists()){
            try {
                dictFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(dictFile));
            for (HashMap.Entry<Character, String> entry : codesDict.entrySet()){
                bf.write(entry.getKey() + ":"  + entry.getValue() + ",");
            }
            bf.flush();
            bf.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}
