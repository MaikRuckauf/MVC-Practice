package comp533.mvc;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class TokenCounter implements TokenCounterInt {
	
	Scanner scanner;
	
	public TokenCounter() {
		this.scanner = new Scanner(System.in);
	}
	@Override
	public void processInput() {
		while (true) {
			System.out.println("Please enter quit or a line of tokens to be processed separated by spaces");
			final String tokens = scanner.nextLine();
			if (tokens.equals("quit")) {
				break;
			}
			final String[] tokenList = tokens.split(" ");
			final Map<String, Integer> tokenHashMap = new HashMap<String, Integer>();
			for (String word: tokenList) {
				final Integer integer = tokenHashMap.get(word);
				if (integer == null) {
					tokenHashMap.put(word, 1);
				}
				else {
					tokenHashMap.put(word,  integer + 1);
				}
			}
			System.out.println(tokenHashMap);
		}
	}
}
	
