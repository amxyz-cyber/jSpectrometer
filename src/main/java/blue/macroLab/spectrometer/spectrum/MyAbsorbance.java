package blue.macroLab.spectrometer.spectrum;

public class MyAbsorbance extends AbstractSignal {
	private double absorbance;
	
	
	public MyAbsorbance(double p_0,double p,double w) {
		super(p_0,p,w);
	}

	public void setSignal() {
		this.absorbance = Math.log10(this.getP_solvent()/this.getP_solution());
		
	}

	public double getSignal() {
		return absorbance;
	}

	
	
	
	
}
