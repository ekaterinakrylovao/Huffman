import java.io.Serializable;

// A Tree node
class Huffman_Node implements Serializable {
    Character charac;
    Integer frequency;
    Huffman_Node left = null, right = null;

    Huffman_Node(Character charac, Integer frequency) {
        this.charac = charac;
        this.frequency = frequency;
    }

    public Huffman_Node(Character charac, Integer frequency, Huffman_Node left, Huffman_Node right) {
        this.charac = charac;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}
