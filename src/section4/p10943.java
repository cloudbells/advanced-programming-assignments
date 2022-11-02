package section4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10943 {

	public static void main(String[] args) {
		p10943 obj = new p10943();
		obj.run();
	}
	
	private BufferedReader stream;
	
	private void run() {
		stream = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = stream.readLine();
			while (!line.equals("0 0")) {
				String[] NK = line.split(" ");
				int N = Integer.parseInt(NK[0]);
				int K = Integer.parseInt(NK[1]);
				line = stream.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
