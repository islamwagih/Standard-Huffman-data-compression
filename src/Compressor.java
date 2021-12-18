import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedWriter;

public class Compressor {
    private String data = "";
    private HashMap<Character, String> codesDict;
    private File compFile;
    private int totalBits = 0;

    public Compressor(String data, HashMap<Character, String> codesDict, String fileName, String path){
        this.data = data;
        this.codesDict = codesDict;
        this.compFile = new File(path+"\\"+fileName);
    }

    public void compress(){
        if(!compFile.exists()){
            try {
                compFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(compFile));
            for(int i=0;i<data.length();i++){
                char curr = data.charAt(i);
                bf.write(codesDict.get(curr));
                totalBits += codesDict.get(curr).length();
            }
            bf.flush();
            bf.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getTotalBits(){
        return totalBits;
    }

    public File getCompFile(){
        return compFile;
    }


}
