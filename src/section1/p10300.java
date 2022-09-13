package section1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10300 {
	
	public static void main(String[] args) {
		p10300 obj = new p10300();
		obj.run();
	}
	
	private TestCase[] readInput() {
		BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
		TestCase[] tests;
		try {
			int nbrOfTests = Integer.parseInt(stream.readLine());
			tests = new TestCase[nbrOfTests];
			int count = 0;
			while (count < nbrOfTests) {
				int nbrOfFarmers = Integer.parseInt(stream.readLine());
				Farmer[] farmers = new Farmer[nbrOfFarmers];
				for (int i = 0; i < nbrOfFarmers; i++) {
					String[] values = stream.readLine().split(" ");
					farmers[i] = new Farmer(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
				}
				tests[count] = new TestCase(farmers);
				count++;
			}
			return tests;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void run() {
		TestCase[] tests = readInput();
		for (int i = 0; i < tests.length; i++) {
			int totalPremium = 0;
			Farmer[] farmers = tests[i].getFarmers();
			for (int j = 0; j < farmers.length; j++) {
				// Number of animals doesn't matter so just multiply farm size by the environmental factor.
				totalPremium += farmers[j].farmSize * farmers[j].envFactor;
			}
			System.out.println(totalPremium);
		}
	}
	
	private class TestCase {
		
		private Farmer[] farmers;
		
		public TestCase(Farmer[] farmers) {
			this.farmers = farmers;
		}
		
		public Farmer[] getFarmers() {
			return farmers;
		}
		
		public void setFarmers(Farmer[] farmers) {
			this.farmers = farmers;
		}
	}
	
	private class Farmer {
		
		private int farmSize;
		private int numberOfAnimals;
		private int envFactor;
		
		public Farmer(int farmSize, int numberOfAnimals, int envFactor) {
			this.farmSize = farmSize;
			this.numberOfAnimals = numberOfAnimals;
			this.envFactor = envFactor;
		}
		
		public int getFarmSize() {
			return farmSize;
		}
		
		public int getNumberOfAnimals() {
			return numberOfAnimals;
		}
		
		public int getEnvFactor() {
			return envFactor;
		}
		
		public void setFarmSize(int farmSize) {
			this.farmSize = farmSize;
		}
		
		public void setNumberOfAnimals(int numberOfAnimals) {
			this.numberOfAnimals = numberOfAnimals;
		}
		
		public void setEnvFactor(int envFactor) {
			this.envFactor = envFactor;
		}
	}
}
