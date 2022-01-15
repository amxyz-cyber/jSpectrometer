package blue.macroLab.spectrometer.spectrum;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.plot.FunctionPlot;
import com.panayotis.gnuplot.style.*;
import com.panayotis.gnuplot.terminal.PostscriptTerminal;

public class BivariatePlot {
	
	public static void paintLineChart(String path,double[][] data1,double[][] data2,String t1,String t2,String xT,String t,NamedPlotColor cAbsorbance, NamedPlotColor cTransmission) {
		JavaPlot p = new JavaPlot();
		PostscriptTerminal epsf = new PostscriptTerminal(path);
		epsf.setColor(true);
		p.setTerminal(epsf);
		p.setTitle(t);
		
		p.getAxis("x").setLabel(xT);
		p.setKey(JavaPlot.Key.BELOW );
		
		
		plotLines(t1,p, data1,cAbsorbance,Smooth.FREQUENCY);
		plotLines(t2,p, data2,cTransmission,Smooth.FREQUENCY);
		
		p.plot();
	}
	
	
	private static PlotStyle styleForm(PlotColor color, AbstractPlot a, Style s ) {
		PlotStyle stl = a.getPlotStyle();
        stl.setStyle(s);
        stl.setLineType(color);
        return stl;
	}
	
	private static void plotLines(String title,JavaPlot p, double[][] data,NamedPlotColor c,Smooth smooth) {
		DataSetPlot sPoints = new DataSetPlot(data);
		sPoints.setTitle(title);
		p.addPlot(sPoints);
		AbstractPlot absplot = (AbstractPlot)p.getPlots().get(p.getPlots().size() - 1);
		absplot.setSmooth(smooth);
		PlotStyle stlFunc = styleForm(c, absplot, Style.LINES );
		stlFunc.setLineWidth(3);
	}
	
	
}
