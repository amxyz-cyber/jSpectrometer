package blue.macroLab.spectrometer.spectrum;

public abstract class AbstractSignal {
	private double p_solvent;
	private double p_solution;
	private double wavelength;
	
	
	public AbstractSignal(double p_0,double p,double w) {
		this.p_solvent = p_0;
		this.p_solution = p;
		this.wavelength = w;
	}
	
	public double getWavelength() {
		return wavelength;
	}

	public double getP_solvent() {
		return p_solvent;
	}

	public double getP_solution() {
		return p_solution;
	}
	
	public abstract void setSignal();
	public abstract double getSignal();
}
