
public class Layer {
	private Synapse[][] synapses;
	private Neuron[] neurons;
	private boolean sigmoid;
	
	public Layer (int numIn, int numOut, boolean sigmoid) {
		synapses = new Synapse[numOut][numIn];
		neurons = new Neuron[numOut];
		this.sigmoid = sigmoid;
		
		for (int i = 0; i < synapses.length; i++) {
			neurons[i] = new Neuron();
			for (int x = 0; x < synapses[i].length; x++) {
				synapses[i][x] = new Synapse();
			}
		}
	}
	
	public double[] getOut(double[] in) {
		double[][] synapseOut = new double[synapses.length][synapses[0].length];
		
		for (int i = 0; i < synapseOut.length; i++) {
			for (int x = 0; x < synapseOut[i].length; x++) {
				synapseOut[i][x] = synapses[i][x].getOutput(in[i]);
			}
		}
		
		double[] output = new double[neurons.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = neurons[i].output(synapseOut[i], sigmoid);
		}
		
		return output;
	} 
	
	public double getWeight(int r, int c) {
		return synapses[r][c].getWeight();
	}
	
	public void setWeight(int r, int c, double newWeight) {
		synapses[r][c].setWeight(newWeight);
	}
	
	public int getSynapseRows() {
		return synapses.length;
	}
	
	public int getSynapseColumns() {
		return synapses[0].length;
	}
}
