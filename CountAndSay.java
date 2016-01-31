/*
 * Find the n’th term in Look-and-say (Or Count and Say) Sequence. The look-and-say sequence is the sequence of below integers:
	1, 11, 21, 1211, 111221, 312211, 13112221, 1113213211
 */
import java.util.*;

class CountAndSay {
    static void countAndSay(Queue<Integer> q, int k, int n) {
        if (n == k) {
        	print(q);
            return;
        }
        Queue<Integer> nQ = new LinkedList<Integer>();
        int c, curr, prev;
        curr = prev = q.poll();
        c = 1;
        while (!q.isEmpty()) {
            curr = q.poll();
            if (curr == prev) {
                c++;
            }else{
                setCount(nQ, c, prev);
                c = 1;
                prev = curr;
            }
        }
        setCount (nQ, c, prev);
        countAndSay(nQ, k+1, n);
    }
    
    static void setCount(Queue<Integer> q, int c, int n){
        if(c < 10){
            q.add(c);
        }else{
            char[] cc = String.valueOf(c).toCharArray();
            for (char x : cc) {
                q.add(x-'0');
            }
        }
        q.add(n);
    }

    static void print(Queue<Integer> q){
       StringBuilder sb = new StringBuilder();
       for (Integer s : q){
          sb.append(s);
       }
       
       System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<Integer>(); 
        q.add(1);
        countAndSay(q, 1, 10);
    }
}
