import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree {
    private DataFrequency dataFrequency;
    private HuffmanNode root;
    private HashMap<Character, String> dict;

    public HuffmanTree(DataFrequency dataFrequency){
        this.dataFrequency = dataFrequency;
        this.dict = new HashMap<>();
    }

    public void construct(){
        HashMap<Character, Integer> frq = this.dataFrequency.getFrequencyDictionary();
        Comparator<HuffmanNode> comp = new HuffmanComparator();
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>(comp);
        for (HashMap.Entry<Character, Integer> entry : frq.entrySet()) {
            char key = entry.getKey();
            int value = entry.getValue();
            HuffmanNode node = new HuffmanNode(key, value);
            nodes.add(node);
        }

        while (nodes.size() > 1) {
            HuffmanNode a = nodes.poll(), b = nodes.poll();
            HuffmanNode interMediate = new HuffmanNode();
            interMediate.setLeft(a);
            interMediate.setRight(b);
            interMediate.setCount(a.getCount() + b.getCount());
            nodes.add(interMediate);
        }

        this.root = nodes.poll();
        if(this.root != null) this.setCodes(this.root, "");
    }

    public HuffmanNode getRoot(){
        if(this.root == null) this.construct();
        return this.root;
    }

    private void setCodes(HuffmanNode curr, String code){
        if (curr.getRight() == null && curr.getLeft() == null) {
            curr.setCode(code);
            this.dict.put(curr.getSymbol(), curr.getCode());
        } else {
            if (curr.getRight() != null) {
                setCodes(curr.getRight(), code + "1");
            }
            if (curr.getLeft() != null) {
                setCodes(curr.getLeft(), code + "0");
            }
        }
    }

    public HashMap<Character, String> getCodesDictionary(){
        return this.dict;
    }
}
