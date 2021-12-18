import java.util.HashMap;

public class DataFrequency {
    private String data = "";
    private HashMap<Character, Integer> frq;

    public DataFrequency(String data){
        this.data = data;
        this.frq = new HashMap<>();
    }

    public void calculate(){
        for (int i = 0; i < this.data.length(); i++) {
            if (this.frq.containsKey(this.data.charAt(i))) {
                this.frq.put(this.data.charAt(i), this.frq.get(this.data.charAt(i)) + 1);
            } else {
                this.frq.put(this.data.charAt(i), 1);
            }
        }
    }

    public HashMap<Character, Integer> getFrequencyDictionary(){
        return this.frq;
    }

}
