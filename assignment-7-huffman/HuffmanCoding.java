import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCoding {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter 3 lines to be encoded.\n");
        StringBuilder txt = new StringBuilder();        
        
        String [] t = new String [3];

        //read 3 lines
        for (int i = 0; i < 3; i++) {
            t[i] = scan.nextLine();
            txt.append(t[i]);
            txt.append(" ");
        }

        String text = txt.toString();
        System.out.println("\nThe text is :\n" + text + "\n");

        HuffmanCoding.variableLengthEncoding(text);
        HuffmanCoding.fixedLengthEncoding(text);
        
        //Part 3 
        t = new String[3];
        int countArr [][] = new int [3][2];

        for(int i = 0; i < 3; i++){
            System.out.println("Enter paragraph " + (i+1) + ":");
            t[i] = scan.nextLine();
            countArr[i][0] = variableLengthEncodeCount(t[i]);
            countArr[i][1] = fixedLengthEncodeCount(t[i]);
        }

        System.out.println("\nVariable Length Encoding Count\t\tFixed Length Encoding Count ");
        for(int i = 0; i < 3; i++){
            System.out.println("\t   " + countArr[i][0] + "\t\t\t\t\t   " + countArr[i][1]);
        }

        scan.close();

    }

    public static int variableLengthEncodeCount(String text){
        HashMap <Character, Integer> freq = getFreq(text);
        Node root = huffman(freq);
        HashMap <Character, String> varHuffmap = new HashMap<>();
        HashMap <String, Character> varRevHuffmap = new HashMap<>();
        varCharEncode(root, "", varHuffmap);

        //reverse of huffmap
        for(char c : varHuffmap.keySet()){
            varRevHuffmap.put(varHuffmap.get(c),c);
        }

        //Encoding the text
        String varEncode = encodeText(text, varHuffmap);
        return varEncode.length();
    }

    public static int fixedLengthEncodeCount(String text) {
        HashMap <Character, String> fixHuffmap = new HashMap<>();
        HashMap <String, Character> fixRevHuffmap = new HashMap<>();

        //char to binary string
        for(char c : text.toCharArray()){
            fixHuffmap.put(c, charToBinary(c));
        }

        //reverse huffmap
        for(char c : fixHuffmap.keySet()){
            fixRevHuffmap.put(fixHuffmap.get(c),c);
        }
        
        String fixEncode = encodeText(text, fixHuffmap);
        return fixEncode.length();
    }

    public static void variableLengthEncoding(String text) {
        //frequencies
        HashMap <Character, Integer> freq = getFreq(text);
    
        //huffman tree
        Node root = huffman(freq);
        
        //Var length
        HashMap <Character, String> varHuffmap = new HashMap<>();
        HashMap <String, Character> varRevHuffmap = new HashMap<>();
        //encoding of each char
        varCharEncode(root, "", varHuffmap);

        //reverse of huffmap
        for(char c : varHuffmap.keySet()){
            varRevHuffmap.put(varHuffmap.get(c),c);
        }

        System.out.println("Frequencies of each character:\n" + freq + "\n");
        System.out.println("Variable length code mapping for each character:");

        for(char c : varHuffmap.keySet()){
            System.out.println(c + " " + varHuffmap.get(c));
        }
        System.out.println();

        //Encoding the text
        String varEncode = encodeText(text, varHuffmap);

        System.out.println("Compressed encoded code: \nVariable length encoding: \n" + varEncode + "\n");
        
        //decoding the text
        String decoString = decode(varEncode, varRevHuffmap);
        System.out.println("Original text after decoding: \n" + decoString +"\n");

    }

    public static void fixedLengthEncoding(String text) {
        //Fixed Length

        HashMap <Character, String> fixHuffmap = new HashMap<>();
        HashMap <String, Character> fixRevHuffmap = new HashMap<>();

        //char to binary string
        for(char c : text.toCharArray()){
            fixHuffmap.put(c, charToBinary(c));
        }

        //reverse huffmap
        for(char c : fixHuffmap.keySet()){
            fixRevHuffmap.put(fixHuffmap.get(c),c);
        }

        System.out.println("Fixed length code mapping for each character:");

        for(char c : fixHuffmap.keySet()){
            System.out.println(c + " " + fixHuffmap.get(c));
        }
        System.out.println();

        //Encoding the text
        String fixEncode = encodeText(text, fixHuffmap);

        System.out.println("Compressed encoded code: \nFixed length encoding: \n" + fixEncode + "\n");

        //decoding the text
        String decoString = decode(fixEncode, fixRevHuffmap);
        System.out.println("Original text after decoding: \n" + decoString +"\n");
    }


    public static HashMap <Character, Integer> getFreq(String text){
        HashMap <Character, Integer> freq = new HashMap<>();
        for(char c : text.toCharArray()){
            freq.put(c,freq.getOrDefault(c, 0) + 1);        
        }
        return freq;
    }

    public static String encodeText(String txt, HashMap <Character, String> huffmap){
        StringBuilder encodeBuilder = new StringBuilder();
        for(char c : txt.toString().toCharArray()){
            encodeBuilder.append(huffmap.get(c));
        }
        return encodeBuilder.toString();
    }

    public static String charToBinary(char c){
        String temp = Integer.toBinaryString(c);
        if(temp.length() < 8){
            int length = temp.length();
            for(int i = 0; i < (8 - length); i++){
                temp = "0" + temp;
            }
        }
        return temp;
    }

    public static boolean isLeaf(Node root){
        return root.left == null && root.right == null;
    }

    public static void varCharEncode(Node root, String s, HashMap <Character, String> huffmap) {
        if(root == null) return;
        if(isLeaf(root)) huffmap.put(root.c,s.length() > 0 ? s : "1");
        varCharEncode(root.left, s + '0', huffmap);
        varCharEncode(root.right, s + '1', huffmap);
    }

    public static String decode(String encodeBuilder, HashMap <String, Character> revHuffmap){
        StringBuilder sb = new StringBuilder(), temp = new StringBuilder();
        char [] charArr = encodeBuilder.toCharArray();
        for(int i = 0; i < encodeBuilder.length(); i++){           
            temp.append(charArr[i]);
            if(revHuffmap.containsKey(temp.toString())){
                sb.append(revHuffmap.get(temp.toString()));
                temp = new StringBuilder();
            }

        }

        return sb.toString();
    }


    public static Node huffman(HashMap <Character,Integer> freq) {
        PriorityQueue <Node> q = new PriorityQueue<>( (a,b) -> a.freq - b.freq);
    
        for (char c : freq.keySet()) {
            Node tempNode = new Node(c, freq.get(c));
            q.add(tempNode);
        }

        while(q.size() > 1){
            Node left = q.poll();
            Node right = q.poll();
            Node newNode = new Node(left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            q.add(newNode);
        }

        return q.poll();
    }

}

class Node{
    char c;
    Node left;
    Node right;
    int freq;
    Node(char c, int freq){
        this.c = c;
        this.freq = freq;
    }
    Node(int freq){
        this.freq = freq;
    }
    
}