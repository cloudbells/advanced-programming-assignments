package section1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p591 {

	public static void main(String[] args) {
		p591 obj = new p591();
		obj.run();
	}
	
	private void run() {
		BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		try {
			int testCase = 1;
			int n = Integer.parseInt(stream.readLine()); // The number of stacks.
			while (n > 0) { // For each test case.
				String[] input = stream.readLine().split(" ");
				int[] stacks = new int[input.length];
				int total = 0;
				// First, we need to count the total number of bricks.
				for (int i = 0; i < input.length; i++) {
					stacks[i] = Integer.parseInt(input[i]);
					total += stacks[i];
				}
				/* Since it can be assumed that we will end up with n stacks in the end, we don't even need to distribute anything at all.
				 * We increment a counter by (stack size) - maxStack for each stack with a stack size bigger than maxStack.
				 * The result is the minimum number of moves needed to evenly stack all bricks in n stacks. */
				// First, determine how high each stack should be. We can assume the total number of bricks is evenly divisible.
				int maxStack = total / n;
				int counter = 0;
				for (int i = 0; i < stacks.length; i++) {
					if (stacks[i] > maxStack) {
						counter += stacks[i] - maxStack; // E.g. if the stack size is 8 and maxStack is 3, counter += 5 (we are removing 5 from the stack).
					}
				}
				sb.append("Set #" + testCase++ + "\nThe minimum number of moves is " + counter + ".\n\n");
				n = Integer.parseInt(stream.readLine());
			}
			System.out.print(sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
