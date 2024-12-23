public class MinHeapNode  implements Comparable<MinHeapNode>{

    char data;

    int freq;

    MinHeapNode left , right;

    public MinHeapNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
    }

    @Override
    public int compareTo(MinHeapNode o) {
        return this.freq - o.freq;
    }
}
