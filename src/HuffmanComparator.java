import java.util.Comparator;
public class HuffmanComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode x, HuffmanNode y) {
        return Integer.compare(x.getCount(), y.getCount());
    }
}
