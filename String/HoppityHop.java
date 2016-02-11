package String;
import java.util.Scanner;

class HoppityHop {
	static String hop = "hop";
	static String hoppity = "hoppity";
	static String hophop = "hophop";
	
	static String[] genPattern = {
			hop, // 0 
			null, //1
			null, //2	
			hoppity, //3
			null, //4
			hophop, //5	
			hoppity, //6
			null, //7
			null, //8	
			hoppity, //9
			hophop, //10
			null, //11
			hoppity, //12
			null, //13
			null, //14
			hop, //15
			null, //16
			null, //17	
			hoppity, //18
			null, //19
			hophop, //20	
			hoppity, //21
			null, //22
			null, //23	
			hoppity, //24
			hophop, //25
			null, //26
			hoppity, //27
			null, //28
			null //29
	};
	
	static StringBuffer flatternPattern(String[] pattern, int upTo){
		StringBuffer flatPatBuf = new StringBuffer();
		for(int i = 0; i <= upTo; i++){
			if(pattern[i] != null){
				flatPatBuf.append(pattern[i] + "\n");
			}
		}
		return flatPatBuf;
	}
	
	static String printHoppityHop(int n){
		String[] pattern = genPattern;
		String[] firstPattern = genPattern.clone(); //it will not have 0;
		firstPattern[0] = null;
		
		if(n <= 30){
			if (n == 30){
				firstPattern[29] = hop;//a little hack - it's not actually at 29 but at 30 but it doesn't matter since non-significant ones are skipped
				n = 29;
			}
			return flatternPattern(firstPattern, n).toString();
		}
		
		StringBuffer total = new StringBuffer();
		total.append(flatternPattern(firstPattern, 29));
		
		StringBuffer repeated =  flatternPattern(pattern, 29);
		int repeatPattern = n / 30;
	
		for (int i = 1; i < repeatPattern; i++){
			total.append(repeated);
		}
	
		StringBuffer underThirty =  flatternPattern(pattern,  n % 30);
		total.append(underThirty);
		return total.toString();
	}
	
	static int scanNumber() {
		Scanner input = new Scanner (System.in);
        String str;
        int n = 0;
        str = input.nextLine();

        try{
	        n = Integer.parseInt(str);
        }catch (Exception e){
        	System.out.println(e.getMessage());
        }
        
        input.close();
        return n;
	}

	
	static void main (String args[]){
		int n = scanNumber();
		String patt = printHoppityHop(n);
		System.out.println(patt);
	}
}
