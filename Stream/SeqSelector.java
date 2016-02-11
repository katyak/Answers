package Stream;
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