
public class Genome {
	private Layer[] layers;
	private double[][][] data;
	private double[][] outputs;

	public Genome (int[] layout, double[][][] data) {
		this.data = data;
		
		layers = new Layer[layout.length];
		
		layers[0] = new Layer(data[0][0].length, layout[0], true);
		for (int i = 1; i < layers.length; i++) {
			layers[i] = new Layer(layout[i-1], layout[i], true);
		}
		outputs = new double[data.length][layout[layout.length-1]];
	}
	
	public void run() {
		for (int i = 0; i < data.length; i++) {
			outputs[i] = layers[0].getOut(data[i][0]);
			for (int x = 1; x < layers.length; x++) {
				outputs[i] = layers[x].getOut(outputs[i]);
			}
		} 
	}
	
	public int getFitness() {
		run();
		double netError = 0;
		for (int i = 0; i < outputs.length; i++) {
			for (int x = 0; x < outputs[i].length; x++) {
				netError +=  1 - Math.abs(data[i][1][0] - outputs[i][x]);
				System.out.println(i + " " + x + " " + outputs[i][x]);
			}
		}
		
		return ((int) netError);
	}
	
	public double getWeight(int layer, int r, int c) {
		return layers[layer].getWeight(r, c);
	}
	
	public void changeWeight(int layer, int r, int c, double newWeight) {
		layers[layer].setWeight(r, c, newWeight);
	}
	
	public int getLayers() {
		return layers.length;
	}
	
	public int getLayerSynapseRows(int layer) {
		return layers[layer].getSynapseRows();
	}
	
	public int getLayerSynapseColumns(int layer) {
		return layers[layer].getSynapseColumns();
	}
}
