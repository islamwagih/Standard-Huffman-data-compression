public class HuffmanNode {
    private char symbol;
    private HuffmanNode left = null, right = null;
    private int count = 0;
    private String code = "";

    public HuffmanNode(char c, int cnt){
        this.symbol = c;
        this.count = cnt;
    }

    public HuffmanNode(){}

    public HuffmanNode getLeft() {
        return this.left;
    }

    public HuffmanNode getRight() {
        return this.right;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public void setRight(HuffmanNode right){
        this.right = right;
    }

    public int getCount() {
        return this.count;
    }
    public void setCount(int cnt){
        this.count = cnt;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public void setCode(String code){
        this.code = code;
    }

    public void setSymbol(char c){
        this.symbol = c;
    }

    public String getCode(){
        return this.code;
    }
}
