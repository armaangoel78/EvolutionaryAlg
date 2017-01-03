import java.util.Random;

public class Main {
	private static Random r = new Random();
	private static Genome[] genomes = new Genome[100];
	private static Breeder breeder = new Breeder(.001, .7);

	private static int[] layout = {1};
	private static double[][][] data = {
			{{0, 0}, {0}}, 
			{{0 ,1}, {0}}, 
			{{1, 0}, {0}}, 
			{{1, 1}, {1}}
			};
	private static int[] wheel;
	
	public static void main(String[] args) {
		while (true) {
			for (int i = 0; i < genomes.length; i++) {
				genomes[i] = new Genome(layout, data);
			}	
			
			setWheel();
			int one = getGenomeNum();
			int two = getGenomeNum();
			while (one == two) two = getGenomeNum();
			
			
			for (int i = 0; i < genomes.length; i++) {
				genomes[i] = breeder.breed(layout, data, genomes[one], genomes[two]);
			}
			break;
		}
	}
	
	public static void setWheel() {
		int len = 0;
		for (int i = 0; i < genomes.length; i++) {
			len += genomes[i].getFitness();
		}
		
		System.out.println(len/genomes.length);
		
		wheel = new int[len];
		int index = 0, val = genomes[0].getFitness(); 
		
		for (int i = 0; i < wheel.length; i++) {
			if (val > 0) {
				wheel[i] = index;
				val--;
			} else {
				index++; 
				val = genomes[index].getFitness();
				
				wheel[i] = index;
				val--;
			}
		}
	}
	
	public static int getGenomeNum() {
		return wheel[r.nextInt(wheel.length)];
	}
}
