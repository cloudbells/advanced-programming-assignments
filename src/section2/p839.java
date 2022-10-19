package section2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p839 {

	/*
	 * This one was a ballache. The description is AWFUL:
	 * - It is NOT clear at all that you're supposed to only add the weights and not multiply by length to calculate weights.
	 * - It was NOT clear how the input was supposed to be read. "the following lines define the the sub-mobile"? This is ambiguous as hell.
	 * - In my mind, equilibrium simply meant whether the total weights (length * node weight) was the same on left as right at root, but apparently
	 *   it needs to also be an equilibrium in each subtree? Not clear at all. Overall, whoever made this assignment can go fuck themselves.
	 *   Picture a mobile that has a bunch of shitty submobiles that aren't balanced. If the total weight on the left of root is the same as the total
	 *   weight on the right of root, the mobile as a whole isn't wonky.
	 */
	
	public static void main(String[] args) {
		p839 obj = new p839();
		obj.run();
	}	
	
	BufferedReader stream;
	
	public void run() {
		stream = new BufferedReader(new InputStreamReader(System.in));
		try {
			int nbrOfTests = Integer.parseInt(stream.readLine());
			for (int i = 0; i < nbrOfTests; i++) {
				stream.readLine(); // Empty line between each test case.
				int[] rootWeights = DFS();
				if (rootWeights[0] != 0 && rootWeights[1] != 0) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
				if (i < nbrOfTests - 1) {
					System.out.print("\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int[] DFS() {
		try {
			String currentLine = stream.readLine();
			while (!currentLine.equals("") || currentLine.equals(null)) {
				String[] values = currentLine.split(" "); // Each line is a node.
				int leftWeight = Integer.parseInt(values[0]);
				int leftLength = Integer.parseInt(values[1]);
				int rightWeight = Integer.parseInt(values[2]);
				int rightLength = Integer.parseInt(values[3]);
				int[] childWeight;
				if (leftWeight == 0) { // Go down left.
					childWeight = DFS();
					leftWeight = childWeight[0] + childWeight[1]; // Weight of left subtree.
				}
				if (rightWeight == 0) { // Go down right.
					childWeight = DFS();
					rightWeight = childWeight[0] + childWeight[1]; // Weight of right subtree.
				}
				/* Check if weight of left subtree is exactly the same as right subtree.
				 * If it isn't, the tree is not in equilibrium and we return 0. 
				 * So, if leftWeight or rightWeight are 0 here, we return 0, 0 for this node as well.
				 */
				if (leftWeight * leftLength != rightWeight * rightLength || (leftWeight == 0 || rightWeight == 0)) {
					return new int[] { 0, 0 }; // This subtree is NOT in equilibrium.
				}
				return new int[] { leftWeight, rightWeight };
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new int[] { -1, 1 };
	}
}
