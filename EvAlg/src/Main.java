import java.util.Random;

public class Main {
	private static Random r = new Random();
	private static Genome[] genomes = new Genome[20000];
	private static Breeder breeder = new Breeder(.001, .7);

	private static int[] layout = {2, 1};
	private static double[][][] data = {
			{{0, 0}, {0}}, 
			{{0 ,1}, {0}}, 
			{{1, 0}, {0}}, 
			{{1, 1}, {1}}
			};
	private static int[] wheel;
	
	public static void main(String[] args) {
		for (int i = 0; i < genomes.length; i++) {
			genomes[i] = new Genome(layout, data, i);
		}	
		
		while (true) {
			int[] best = getBest(10);
			int index = 0;
			for (int i = 0; i < best.length; i++) {
				for (int x = 0; x < genomes.length/best.length; x++) {
					genomes[index] = breeder.breed(layout, data, genomes[best[i]], .2, index);
					index++;
				}
			} 
		} 
	}
	
	public static void setWheel() {
		int len = 0;
		for (int i = 0; i < genomes.length; i++) {
			len += genomes[i].getFitnessInt();
		}
			
		System.out.println(len/genomes.length);
		
		wheel = new int[len];
		int index = 0, val = genomes[0].getFitnessInt(); 
		
		for (int i = 0; i < wheel.length; i++) {
			if (val > 0) {
				wheel[i] = index;
				val--;
			} else {
				index++; 
				val = genomes[index].getFitnessInt();
				
				wheel[i] = index;
				val--;
			}
		}
	}
	
	public static int[] getBest(int num) {
		int[] indexes = new int[num];
		double[] values = new double[num];
		
		for (int i = 0; i < genomes.length; i++) {
			boolean found = false;
			int index = 0;
			double value = 0;
			
			for (int x = 0; x < values.length; x++) {
				if (values[x] < genomes[i].getFitnessRaw() && !found) { //insert fitness when it is larger than the one in values[x]					
					index = indexes[x];
					value = values[x];
					
					indexes[x] = i;
					values[x] = genomes[i].getFitnessRaw();
					
					found = true;
				} else if (found) { //shift the rest of values[] over					
					int indexTemp = indexes[x];
					double valueTemp = values[x];
					
					indexes[x] = index;
					values[x] = value;
					
					index = indexTemp;
					value = valueTemp;
				}
			}
		}

		System.out.println(genomes[indexes[0]].getFitnessRaw());
		
		return indexes;
	}
	
	public static int getGenomeNum() {	
		return wheel[r.nextInt(wheel.length)];
	}
}
