package section1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10878 {

	public static void main(String[] args) {
		p10878 obj = new p10878();
		obj.run();
	}
	
	private char parseLine(String line) {
		// The input will be of the following form:
		// |76543.210| where the position n has a value of 2^n.
		int value = 0;
		line = line.replaceAll("[|\\.]", ""); // Resulting is 76543210
		char[] chars = line.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == 'o') {
				value += Math.pow(2, (chars.length - 1) - i); // 2^(7-0) for i = 1, 2^(7-1) for i = 2, 2^(7-2) for i = 3, etc.
			}
		}
		return (char) value;
	}
	
	private void run() {
		/* The input is a sequence of bytes. Each byte contains 8 bits.
		 * For the sample input, the first line corresponds to the value 1 + 64 = 65 which is an A in UTF-8/Unicode. */
		BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		try {
			int counter = 0;
			do {
				String line = stream.readLine();
				if (line.equals("___________")) {
					counter++;
				} else {
					sb.append(parseLine(line));
				}
			} while (counter < 2);
			System.out.print(sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}