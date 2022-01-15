package blue.macroLab.spectrometer.spectrum;

import java.util.ArrayList;
import java.util.List;
import com.panayotis.gnuplot.dataset.FileDataSet;

public class MySpectrometer {
	private FileDataSet fData;
	private MyData mydata;
	private List<AbstractSignal> myDataA;
	private List<AbstractSignal> myDataT;
	 
	 public MySpectrometer(MyData m) {
		 	mydata = m;
		    this.fData = m.getDataset();
			this.myDataA =  new ArrayList<>();
			this.myDataT =  new ArrayList<>();
			
	 }
	 
	 public void calculateSignals() {
		  int xCol = 0;
		  int p0_col = 1;
		  int p_col = 2;
		  		  
	      for(int i=0; i<this.fData.size(); i++) {
	    	  Double x = mydata.getDataPoint(xCol,i );
	    	  Double p0 = mydata.getDataPoint(p0_col,i );
	    	  Double p = mydata.getDataPoint(p_col,i );
	    	  MyAbsorbance a = new MyAbsorbance(p0,p,x);
	    	  a.setSignal();
	    	  MyTransmittance t = new MyTransmittance(p0,p,x);
	    	  t.setSignal();
	    	  this.myDataA.add(a);
	    	  this.myDataT.add(t);
	   }
	 }
	 
	 public double [][] toData(List<AbstractSignal> myData) {
			int index =0;
			int iSize = myData.size();
			double [] [] data =new double[iSize][2];
			int xCol = 0;
			int yCol = 1;
			
		    for (AbstractSignal s: myData) {
		    	  		  
		    	  data[index][xCol] = s.getWavelength() ;
		    	  data[index][yCol] = s.getSignal(); 
		    	  index++;
		   }
		    return data;
		}

	public List<AbstractSignal> getMyDataA() {
		return myDataA;
	}

	public List<AbstractSignal> getMyDataT() {
		return myDataT;
	}


}
