import java.util.Random;

public class Breeder {
	private double mutationRate, crossoverRate;
	private Random r = new Random();
	
	public Breeder (double mutationRate, double crossoverRate) {
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
	}
	
	public Genome breed(int[] layout, double[][][] data, Genome one, double amountOfChange, int num) {
		Genome newGenome = new Genome(layout, data, num);
		for (int i = 0; i < one.getLayers(); i++) {
			for (int r = 0; r < one.getLayerSynapseRows(i); r++){
				for (int c = 0; c < one.getLayerSynapseColumns(i); c++) {
					if (Math.random() <= crossoverRate) {
						double w1 = one.getWeight(i, r, c); 
						double adjustment = (Math.random() * (amountOfChange*2)) - amountOfChange; //change to weight can be between -amountOfChange and +amountOfChange
						newGenome.changeWeight(i, r, c, w1 + adjustment);
					}
				}
			}
		}
		
		return newGenome;
	}
	
	
	public Genome breed(int[] layout, double[][][] data, Genome one, Genome two, int num) {
		Genome newGenome = new Genome(layout, data, num);
		for (int i = 0; i < one.getLayers(); i++) {
			for (int r = 0; r < one.getLayerSynapseRows(i); r++){
				for (int c = 0; c < one.getLayerSynapseColumns(i); c++) {
					if (Math.random() <= crossoverRate) {
						double w1 = one.getWeight(i, r, c); 
						double w2 = two.getWeight(i, r, c);
						newGenome.changeWeight(i, r, c, splice(w1, w2));
					}
				}
			}
		}
		
		return newGenome;
	}
	
	private double splice(double w1, double w2) {
		String wOne, wTwo;
		if (Math.random() <= .5) {
			wOne = Long.toBinaryString(Double.doubleToRawLongBits(w1));
			wTwo = Long.toBinaryString(Double.doubleToRawLongBits(w2)); 
		} else {
			wTwo = Long.toBinaryString(Double.doubleToRawLongBits(w1));
			wOne = Long.toBinaryString(Double.doubleToRawLongBits(w2)); 
		}
		
		int position = r.nextInt(Math.min(wOne.length(), wTwo.length())+1);
		
		String newWeight = wOne.substring(0, position) + wTwo.substring(position);
		
		for (int i = 0; i < newWeight.length(); i++) {
			if (mutationRate * newWeight.length() >= Math.random()) {
				String bit = newWeight.substring(i, i+1);
				bit = (bit.equals("1") ? "0" : "1");
				newWeight = newWeight.substring(0, i) + bit + newWeight.substring(i+1);
			}
		}
		
		double weight = Double.longBitsToDouble(Long.parseLong(newWeight, 2));
		return weight;
	}
}
