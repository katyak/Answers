/*
 *  Given a stream of numbers, generate a random number from the stream. 
 *  You are allowed to use only O(1) space and the input is in the form of stream, so can’t store the previously seen numbers.
 */

class SeqSelector{
	
	static int current;
	static float count = 0; 

	public int getSelection(){
		return current;
	}

	public void addNext(int item){
		count++;
		if(count == 1 || Math.random() < (1.0 / count)){
			current = item;
		}
		System.out.println(current);
	}

	public static void main(String args[]){
		SeqSelector s = new  SeqSelector();
		for (int i = 0; i < 100; i++){
			s.addNext(i);
		}
	}
}