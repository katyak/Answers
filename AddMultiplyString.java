class AddMultiplyString {
	static int addMultiply(String a) {
		char[] aElem = a.toCharArray();
		int res = 0;
		int multRes = 1;
		boolean newNum = true;
		int n = 0;
		for(int i = 0; i < aElem.length; i++){
			switch(aElem[i]){
			case '+':
				res = res + multRes * n;
				multRes = 1;
				newNum = true;
				break;
			case '*':
				multRes = multRes * n;
				newNum = true;
				break;
			default:
				if (aElem[i] < '0' || aElem[i] > '9'){
					return 0;
				}
				if (newNum){
					n = aElem[i] - '0';
					newNum = false;
				}else{
					n = n * 10 + aElem[i] - '0';
				}
				break;
			}
		}
		res = res + multRes * n;
		
		return res;
	}
	
	public static void main(String[] args) {
		String a = "3+4*15*2+8+9*2";
		System.out.println(addMultiply(a));
	}
}