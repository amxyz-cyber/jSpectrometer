package blue.macroLab.spectrometer.spectrum;

public class MyTransmittance extends AbstractSignal {
	private double transmittance;
	
	
	public MyTransmittance(double p_0,double p,double w) {
		super(p_0,p,w);
	}

	public void setSignal() {
		this.transmittance = this.getP_solution()/this.getP_solvent();
		
	}
	
	public double getSignal() {
		return transmittance;
	}

	
}
