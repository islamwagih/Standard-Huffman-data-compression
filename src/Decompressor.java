import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Decompressor {
    private boolean loaded = false;
    private File compFile, decompFile;
    private String bits = "";
    private HashMap<String, Character> revCodesDict;

    public Decompressor(String compFileName, HashMap<Character, String> codesDict){
        this.compFile = new File(compFileName);
        this.revCodesDict = new HashMap<>();
        for(Map.Entry<Character, String> entry:codesDict.entrySet()){
            this.revCodesDict.put(entry.getValue(), entry.getKey());
        }
    }

    private void loadData(){
        if(this.loaded) return;
        try (Scanner myReader = new Scanner(this.compFile)) {
            while (myReader.hasNextLine()) {
                this.bits += myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(!this.decompFile.exists()){
            try {
                this.decompFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        this.loaded = true;
    }

    public String getBits(){
        this.loadData();
        return bits;
    }

    public String decompress(String fileName, String path){
        this.decompFile = new File(path+"\\"+fileName);
        this.loadData();
        String currCharCode = "";
        String orgData = "";

        for(int i=0;i<this.bits.length();i++){
            currCharCode += this.bits.charAt(i);
            if(this.revCodesDict.containsKey(currCharCode)){
                orgData += this.revCodesDict.get(currCharCode);
                currCharCode = "";
            }
        }

        try{
            PrintWriter out = new PrintWriter(fileName);
            out.println(orgData);
            out.flush();
            out.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return orgData;
    }


}
