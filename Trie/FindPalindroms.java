package Trie;
//Create a trie out of the words. While creating it also check for existing matches 
//(this way we only traverse through the word once using front -> end iterator to add it to trie
//and end -> front iterator to look for matches.
//a  word can match current if it is full match, could be prefix, could be shorter suffix
///or longer suffix. All except for the last can be found in no extra time
//The final one can be avoided if we sort the array by length first (then no longer prefixes can exist for current)
//sorting by length can add up to n log n time. This way the added time is n * m where m is the number of branches 
//where second char matches the current. Either one can be shorter or loner than the current depending on the circumstances
import java.util.Comparator;
import java.util.HashMap;
import java.util.Arrays;

class PTrieNode {
	
	HashMap<Character, PTrieNode> children;
	boolean terminates = false;
	char character;
	
	PTrieNode() {
		children = new HashMap<Character, PTrieNode>();
	}

	public PTrieNode(char character) {
		this();
		this.character = character;
	}
}

class FindPalindroms{
	
	static void findPalinomialsWithSort(String[] a){
		Arrays.sort(a, new SampleComparator());
		PTrieNode root = new PTrieNode();
		for(String st: a){
			checkAndAddWithSort(st, root);
		}
	}
	
	//Add the node to trie and simultaneously check if there are any matches for it
	//for word abcd there can be matches of types:
	// -full match (will work as both prefix and suffix, we will call it prefix)
	//   dcba
	// -prefix
	//   dcb
	// -suffix
	//   cda
	static void checkAndAddWithSort(String word, PTrieNode root) {
		if (word == null || word.isEmpty()) {
			return;
		}
		
		char[] wordCh = word.toCharArray();
		int wordL = wordCh.length;
		
		//tracks insertion location
		PTrieNode currAdd = root;
		
		//looks for prefixes (prefix and full)
		PTrieNode prefixSearch = root;
		StringBuffer prefix = new StringBuffer();
		
		//looks for suffix
		PTrieNode suffixSearch = (wordL-2 >= 0 && root.children.containsKey(wordCh[wordL-2])) ? root : null;
		StringBuffer suffix = new StringBuffer();

		//iterate through each char add - look for matches and add it to trie 
		for(int i = 0; i < wordL; i++){
			char chFront = wordCh[i];
			char chEnd = wordCh[wordL - i - 1];	
			char chSecToEnd = ' ';
			if(wordL >= i + 2){
				chSecToEnd = wordCh[wordL - i - 2];
			}
			
			if(prefixSearch != null && prefixSearch.children.containsKey(chEnd)){
				prefix.append(chEnd);
				prefixSearch = prefixSearch.children.get(chEnd);
				if(prefixSearch.terminates){
					if(i == wordL - 1 || i == wordL - 2){
						System.out.println("pair: " + word + " " + prefix.toString());
					}
				}
			}else{
				prefixSearch = null;
			}
			
			if(suffixSearch != null && suffixSearch.children.containsKey(chSecToEnd)){
				suffix.append(chSecToEnd);
				suffixSearch = suffixSearch.children.get(chSecToEnd);
				if(suffixSearch.terminates){
					if(i == wordL - 2){
						System.out.println("pair: " + word + " " + suffix.toString());
					}
				}
			}else{
				suffixSearch = null;
			}
			
			if(!currAdd.children.containsKey(chFront)){
				currAdd.children.put(chFront, new PTrieNode(chFront));
			}
			currAdd = currAdd.children.get(chFront);
			if(i == wordL - 1){
				currAdd.terminates = true;
			}
		}
	}
	
	static void findPalinomialsWithoutSort(String[] a){
		PTrieNode root = new PTrieNode();
		for(String st: a){
			checkAndAdd(st, root);
		}
	}
	
	//Add the node to trie and simultaneously check if there are any matches for it
	//for word abcd there can be matches of types:
	// -full match (will work as both prefix and suffix, we will call it prefix)
	//   dcba
	// -prefix
	//   dcb
	// -suffix
	//   cda
	// -long suffix (one character in the middle can be anything)
	//   Xdcba
	static void checkAndAdd(String word, PTrieNode root) {
		if (word == null || word.isEmpty()) {
			return;
		}
		
		char[] wordCh = word.toCharArray();
		int wordL = wordCh.length;
		
		//tracks insertion location
		PTrieNode currAdd = root;
		
		//looks for prefixes (prefix and full)
		PTrieNode prefixSearch = root;
		StringBuffer prefix = new StringBuffer();
		
		//looks for short suffix
		PTrieNode suffixSearch = (wordL-2 >= 0 && root.children.containsKey(wordCh[wordL-2])) ? root : null;
		StringBuffer suffix = new StringBuffer();
		
		//looks for long suffixes: something + last char of word
		//keep track of them in a HashMap 
		//like 'x' -> PTrieNode that will iterate through this branch 
		HashMap<Character, PTrieNode> longSuffixMap = new HashMap<Character, PTrieNode>();
		char chFirst = wordCh[0];
		char chLast = wordCh[wordL-1];
		for(char firstTrie : root.children.keySet()){
			PTrieNode test = root.children.get(firstTrie);
			if(test.children.containsKey(chLast)){
				longSuffixMap.put(firstTrie, root.children.get(firstTrie));
			}
		}
		
		for(int i = 0; i < wordL; i++){
			char chFront = wordCh[i];
			char chEnd = wordCh[wordL - i - 1];	
			char chSecToEnd = ' ';
			if(wordL >= i + 2){
				chSecToEnd = wordCh[wordL - i - 2];
				suffix.append(chSecToEnd);
			}
			
			if(prefixSearch != null && prefixSearch.children.containsKey(chEnd)){
				prefix.append(chEnd);
				prefixSearch = prefixSearch.children.get(chEnd);
				if(prefixSearch.terminates){
					if(i == wordL - 1 || i == wordL - 2){
						System.out.println("pair: " + word + " " + prefix.toString());
					}
				}
			}else{
				prefixSearch = null;
			}
			
			if(suffixSearch != null && suffixSearch.children.containsKey(chSecToEnd)){
				suffixSearch = suffixSearch.children.get(chSecToEnd);
				if(suffixSearch.terminates){
					if(i == wordL - 2){
						System.out.println("pair: " + word + " " + suffix.toString());
					}
				}
			}else{
				suffixSearch = null;
			}
			
			if(!longSuffixMap.isEmpty()){
				for(char firstTrie : longSuffixMap.keySet()){
					PTrieNode longSuffixSearch = longSuffixMap.get(firstTrie);
					if(longSuffixSearch.children.containsKey(chEnd)){
						longSuffixSearch = longSuffixSearch.children.get(chEnd);
						if(longSuffixSearch.terminates){
							if(i == wordL - 1){
								System.out.println("pair: " + word + " " + firstTrie + chFirst + suffix.toString());
							}else{
								longSuffixMap.remove(firstTrie);
							}
						}else{
							longSuffixMap.put(firstTrie, longSuffixSearch);
						}
					}else{
						longSuffixMap.remove(firstTrie);
					}
					
				}
				
			}
			
			if(!currAdd.children.containsKey(chFront)){
				currAdd.children.put(chFront, new PTrieNode(chFront));
			}
			currAdd = currAdd.children.get(chFront);
			if(i == wordL - 1){
				currAdd.terminates = true;
			}
		}
	}
	
	
	public static void main(String[] args) {

		String[] input = {"b", "ab", "ba", "abc", "a", "bad"};
		findPalinomialsWithSort(input);
		//findPalinomialsWithoutSort(input);
	}
}

class SampleComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return new Integer(o1.length()).compareTo(o2.length());
   }
}
