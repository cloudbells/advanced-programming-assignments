package section3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Very simple and straight-forward algorithm: simply iterate through the "encrypted" (target) string and look for each character
 * in the pattern in sequence. In other words, if the input is:
 * 
 * BANA BabANbA
 * 
 * We check for a B from left to right (ignoring any character that isn't B) until it is found, then we look for an A,
 * then N, and lastly A. If we have found the full word before or upon reaching the end of BabAnbA, we can break and print "Yes",
 * if on the other hand we reach the end of BabAnbA and the word still isn't fully found, we print "No".
 * 
 * Run time is then O(n).
 */
public class p10340 {

	public static void main(String[] args) {
		p10340 obj = new p10340();
		obj.run();
	}
	
	private BufferedReader stream;
	
	private void run() {
		stream = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = stream.readLine();
			while (line != null) {
				String res = "No";
				String words[] = line.split(" ");
				String s = words[0];
				String t = words[1];
				int index = 0, equalCount = 0, count = s.length();
				for (int i = 0; i < t.length(); i++) {
					char c = t.charAt(i);
					if (c == s.charAt(index)) {
						index++;
						equalCount++;
						if (equalCount == count) {
							res = "Yes";
							break;
						}
					}
				}
				System.out.println(res);
				line = stream.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
