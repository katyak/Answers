import java.util.Queue;
import java.util.LinkedList;

class BNode {
	char value;

	BNode left;
	BNode right;

	BNode(char v) {
		value = v;
	}
}

class SerilizeBinaryTree {
	
	static String serialize(BNode root) {
    	Queue<BNode> q = new LinkedList<BNode>();
    	StringBuilder sb = new StringBuilder();
    	q.add(root);

    	BNode f;
    	BNode space = new BNode(' ');

        while (!q.isEmpty()) {
        	f = q.poll();
        	sb.append(f.value);

        	if(f.value!=' '){
        		if (f.left != null){
        			q.add(f.left);
        		}else{
        			q.add(space);
        		}
        		if (f.right != null){
        			q.add(f.right);
        		}else{
        			q.add(space);
        		}
        	}
        }
        return sb.toString();
   }
    
    static BNode deserialize(String s) {
    	char[] chars = s.toCharArray();
    	BNode root = new BNode(chars[0]);
    	Queue<BNode> q = new LinkedList<BNode>();
    	q.add(root);
    	int i = 1;
    	
        while (i < chars.length && !q.isEmpty()) {
        	BNode f = q.poll();
        	char l = chars[i];
        	char r = chars[i+1];
        	
        	if(l != ' '){
        		f.left = new BNode(l);
        		q.add(f.left);
        	}else{
        		f.left = null;
        	}
        	if(r != ' '){
        		f.right = new BNode(r);
        		q.add(f.right);
        	}else{
        		f.right = null;
        	}
        	i += 2;
        }

    	return root;
   }
	
	public static void main(String[] args) {
		BNode node = new BNode('a');
		BNode node1 = new BNode('b');
		BNode node2 = new BNode('c');
		BNode node3 = new BNode('d');
		BNode node4 = new BNode('e');
		node.left = node1;
		node.right = node2;
		node.left.left = node3;
		node.left.right = node4;
		
		System.out.println(serialize(node));
		BNode ret = deserialize(serialize(node));
		System.out.println(ret.value);
		System.out.println(ret.left.value);
		System.out.println(ret.right.value);
		System.out.println(ret.left.left.value);
		System.out.println(ret.left.right.value);
	}
}
