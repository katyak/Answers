import java.util.Stack;
	
class XMLcheck {
	
	static boolean isValid(String s){
		Stack<String> stack = new Stack<String>();
		String tag = "";
		boolean buildingTag = false;
		boolean opening = true;
	    
		char[] a = s.toCharArray();
		for(int i=0; i < a.length; i++){
			char c = a[i];
			switch(c){
				case '<':
					if(i+1 >= a.length){
						return false; 
					}
					if(a[i+1]!='/'){
						opening = true;
					}else{
						opening = false;
					}
					buildingTag = true;
					break;
				case '>':
					if(opening){
						stack.push(tag);
					}else{
						if(stack.isEmpty()){
							return false;
						}
						String openTag = stack.pop();
						if(!openTag.equals(tag) || tag == ""){
							return false;
						}  
					}
					tag = "";
					buildingTag = false;
					break;
				default:
					if(c == '/'){
						if( i > 1 && a[i - 1] == '<'){
							break;
						}
					}
					if(buildingTag){
						tag += c;
					}
					break;
			}
		}
		if(!stack.isEmpty()){
			return false;
		}
		return true;
	}
	  
	  
	public static void main(String[] args) {
		String s = "<abc>Valid html</abc>";//true
		String s1 = "<a>invalid html </b>";//false
		String s2 = "<a> <b> invalid </a> </b>";//false
		String s3 = "<a> invalid no closing tag";//false
		String s4 = "<> invalid </>";//false
		String s5 = "<s> som <s>";//false
		
		System.out.println(isValid(s));
		System.out.println(isValid(s1));
		System.out.println(isValid(s2));
		System.out.println(isValid(s3));
		System.out.println(isValid(s4));
		System.out.println(isValid(s5));
	}
}


