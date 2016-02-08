import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

class BTPNode {
	int key;

	BTPNode left;
	BTPNode right;

	BTPNode (int k) {
		key = k;
	}
}
	
class PrintBinaryTree {
	
	static void printList(BTPNode root) {
		ArrayList<BTPNode> l = new ArrayList<BTPNode>();
		l.add(root);
		printNode(l, 1, depth(root));
	}

	static void printNode(ArrayList<BTPNode> lOrig, int level, int depth) {
		if (lOrig.isEmpty()){
			return;
		}
		boolean allNull = true;
        for (Object object : lOrig) {
        	if (object != null){
        		allNull = false;
        		break;
        	}
        }
        if(allNull){
        	return;
        }

		int frontSp = (int) Math.pow(2, (depth - level)) - 1;
		int betweenSp = (int) Math.pow(2, (depth - level + 1)) - 1;
		
		printWS(frontSp);
		ArrayList<BTPNode> newNodes = new ArrayList<BTPNode>();
		for (BTPNode node : lOrig) {
			if (node != null) {
				System.out.print(node.key);
				newNodes.add(node.left);
				newNodes.add(node.right);
			} else {
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}
			printWS(betweenSp);
		}
		
		System.out.print("\n");
		printNode(newNodes, level + 1, depth);
	}

    static void printWS(int count){
        for (int i = 0; i < count; i++){
            System.out.print(" ");
        }
    }
    
	static int depth(BTPNode node) {
		if (node == null) {
			return 0;
		}else{
			return 1 + Math.max(depth(node.left), depth(node.right));
		}
	}
	
	public static void main(String[] args) {
		//create data set
		BTPNode a1 = new BTPNode(2);
		a1.left = new BTPNode(4);
		a1.right = new BTPNode(1);
		a1.left.left = new BTPNode(3);
		a1.left.right = new BTPNode(2);
		a1.right.left = new BTPNode(3);
		a1.right.right = new BTPNode(2);
		printList(a1);
	}
}
