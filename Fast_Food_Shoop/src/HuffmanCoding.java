import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    private static Map<Character , String> codes = new HashMap<>();
    private static Map<Character , Integer> freq = new HashMap<>();
    private static PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>();

    public void HuffmanCodes(int size){
        size = 52;
        freq.put('A' , 5);
        freq.put('B' , 6);
        freq.put('C' , 7);
        freq.put('D' , 8);
        freq.put('E' , 10);
        freq.put('F' , 16);
        for (Map.Entry<Character , Integer> entry : freq.entrySet()){
            minHeap.add(new MinHeapNode(entry.getKey(), entry.getValue()));
        }

        while (minHeap.size() != 1){
            MinHeapNode left = minHeap.poll();
            MinHeapNode right = minHeap.poll();
            MinHeapNode top = new MinHeapNode('$' , left.freq + right.freq);
            top.left = left;
            top.right = right;
            minHeap.add(top);
        }
        storeCodes(minHeap.peek() , "");
    }

    public void storeCodes(MinHeapNode root , String str){
        if (root == null){
            return;
        }
        if (root.data != '$'){
            codes.put(root.data , str);
        }
        storeCodes(root.left , str + "0");
        storeCodes(root.right , str + "1");
    }

    public String decode_file(MinHeapNode root , String s){
        String ans = "";
        MinHeapNode curr = root;
        int n = s.length();
        for (int i = 0; i < n; i++){
            if (s.charAt(i) == '0'){
                curr = curr.left;
            }else {
                curr = curr.right;
            }
            if (curr.left == null && curr.right == null){
                ans += curr.data;
                curr = root;
            }
        }
        return ans;
    }

    public ArrayList<String> Code(ArrayList<String> codes){
        String decode_string = "";
        ArrayList<String> discount_codes = new ArrayList<>();
        for (int i = 0; i < codes.size(); i++){
            decode_string = decode_file(minHeap.peek() , codes.get(i));
            discount_codes.add(decode_string);
        }
        return discount_codes;
    }
}
