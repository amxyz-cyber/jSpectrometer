package blue.macroLab.spectrometer.utils;

import java.io.File;

import com.panayotis.gnuplot.style.NamedPlotColor;

public class Utility {
	
	public static boolean fileExists(String s) {
		File file = new File(s);
		boolean exists = file.exists();
		return exists;
	}
	
	public static void addNewLine(StringBuilder r) {
		r.append(System.getProperty("line.separator"));
	}
	
	public static NamedPlotColor getPlotColor(String s) {
		NamedPlotColor mycolor = null;
		for (NamedPlotColor c: NamedPlotColor.values()) {
			if (c.toString().equals(s)) {
				mycolor = c;
				break;
			}
		}
		return mycolor;
		}
}
