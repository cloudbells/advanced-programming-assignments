package section3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Substitution rule (c means left side is congruent to right side):
 * 
 * If x c x' (mod N) and y c y' (mod N) then xy c x'y' (mod N)
 * 
 * For example:
 * 
 * If 5 c 2 (mod 3) and 22 c 1 (mod 3) then 5*22 c 2*1 (mod 3)
 * In the above case, 5*22 = 110 is congruent to 2*1 = 2 (mod 3) because both 110%3 and 2%3 result in 2.
 * 
 * Because of the substitution rule, any intermediate multiplication can be replaced with modulo the result.
 * For example, (5*5*20) is congruent with (5%3) * (5%3) * (20%3) = 2*2*2 = 8, mod 3. In other words, 5*5*20 c 8 (mod 3).
 * In yet more words, the original calculation (5*5*20)%3 is reduced to only having to calculate 8%3 which is 2.
 */
public class p374 {
	
	public static void main(String[] args) {
		p374 obj = new p374();
		obj.run();
	}
	
	private BufferedReader stream;
	
	private void run() {
		stream = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line;
			do {
				int b = Integer.parseInt(stream.readLine());
				int p = Integer.parseInt(stream.readLine());
				int m = Integer.parseInt(stream.readLine());
				line = stream.readLine(); // Will be null when EOF is reached.
				System.out.println(mod(b, p, m));
			} while (line != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private long mod(long b, long p, long m) {
		if (p == 0) {
			return 1 % m;
		}
		long z = mod(b, p / 2, m);
		if ((p % 2) == 0) {
			return (z * z) % m;
		} else {
			return (b * z * z) % m;
		}
	}
}
