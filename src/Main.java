import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String generalPath = System.getProperty("user.dir");
        System.out.println("NOTE : Files will be created and modified at -> "+generalPath);
        System.out.println("1 - Compression");
        System.out.print("2 - Decompression\n>>");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        if(input == 1){

            //load the data from input file
            System.out.print("Write file name with extension\n>>");
            String fileName = scan.next();
            DataLoader dataLoader = new DataLoader(fileName, generalPath);
            dataLoader.load();
            String data = dataLoader.getLoadedData();
            //System.out.println("data = "+data);

            //get frequency table
            DataFrequency dataFrequency = new DataFrequency(data);
            dataFrequency.calculate();
            HashMap<Character, Integer> frq = dataFrequency.getFrequencyDictionary();
            //System.out.println("frq = "+frq);

            //construct our huffman tree
            HuffmanTree tree = new HuffmanTree(dataFrequency);
            tree.construct();
            HashMap<Character, String> codesDict = tree.getCodesDictionary();

            //write our dictionary
            DictionaryWriter writer = new DictionaryWriter(data, codesDict, "dict.txt", generalPath);
            writer.write();

            //write out compressed file

            Compressor comp = new Compressor(data, codesDict, "comp.txt", generalPath);
            comp.compress();

            System.out.println(frq);
            System.out.println(codesDict);

            int compressedSize = comp.getTotalBits();

            System.out.println("Compressed Size = "+compressedSize);
            System.out.println("original size = "+data.length()*8);

            double compressionRatio = (data.length()*8)/compressedSize;

            System.out.println("compression ratio = "+compressionRatio);
        }else if(input == 2){

            System.out.print("Write dictionary file name with extension\n>>");
            String dictFileName = scan.next();
            System.out.print("Write compressed file name with extension\n>>");
            String compFileName = scan.next();

            // read dictionary file
            DictionaryReader reader = new DictionaryReader(dictFileName, generalPath);
            HashMap<Character, String> codeDict = reader.getCodesDict();

            // decompress our data
            Decompressor decomp = new Decompressor(compFileName, codeDict);
            decomp.decompress("decomp.txt", generalPath);

            System.out.println(codeDict);

        }else{
            System.out.println("Undefined");
        }
    }





}
