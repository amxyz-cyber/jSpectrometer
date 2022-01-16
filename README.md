# jSpectrometer - It visualizes the data of your spectrometer!

## What is it?
[jSpectrometer](https://github.com/amxyz-cyber/jSpectrometer/tree/master/screenshots)
plots the data taken from a **diode array spectrophotometer**. To 
plot the data `jSpectrometer` needs the signal `P_Solution` obtained 
with sample solution in the cell after subtraction of the dark signal. 
Additionally, it requires the reference signal `P_Solvent` obtained with
only solvent in the cell after subtraction of the dark signal. After the
data file has been read in it calculates the absorbance `A` and 
transmittance `T` based on the data retrieved from the two signals and 
the wavelength. The plot contains the spectra of the absorption and 
transmittance stored in a single [`.eps`](https://github.com/amxyz-cyber/jSpectrometer/blob/master/screenshots/spectrometer.png) file.

**jSpectrometer** is a Java app which was compiled using a 64 bit Java
compiler. Although it runs on the console it comes with a **textGUI**
made with the **Lanterna** library. Additionally, it supports the
languages **English** as well as **German**. **jSpectrometer** will work
on any Linux system, including Raspberry Pi and on Windows.

## System Requirements
**JDK**:
* 9 or above. Both the Java compiler and Java runtime (JRE) need to 
support 64-bit and contain the package **JavaFX**. Compatible Java
compilers as well as JREs can be downloaded from [Bellsoft](https://bell-sw.com/pages/downloads/).
    
**Memory**:
* No minimum requirement.

**Disk**:
* Approximately 1.1 MiB is required for the jSpectrometer installation 
  itself. In addition to that, 2.8 MiB disk space will be used for 
  the jSpectrometer. 

**Operating System**:
* Windows:
      Windows 2000 or above.
* Unix based systems (Linux, Solaris and Mac OS X) and others:
      No minimum requirement.
      
 **Gnuplot**:
 * jSpectrometer uses [**gnuplot**](http://www.gnuplot.info/) to plot the diagram. So you need
 to install gnuplot first before using jSpectrometer.
 
## Compiling jSpectrometer
If you decide to compile this project then you can do it with [maven](https://maven.apache.org/).
Under Linux open a terminal and go to the directory of this project. 
Then run the following command which will compile the source files:
```bash
$ mvn clean package
```

After a successful compilation the `.jar` file together with the 
libraries will be placed in the subfolder called `target`.

## Installing jSpectrometer
If you want to use the provided binaries, i.e. the release package
instead, then the installation process is simple:

1. Unpack the archive where you would like to store the binaries, e.g.:
* Unix-based operating systems (Linux, Solaris and Mac OS X)
	```bash
	$ tar zxvf jSpectrometer-x.y.z.tar.gz
	```
* Windows
	```bash
	unzip jSpectrometer-x.y.z.zip
	```
2. A directory called "jSpectrometer-3.x.y" will be created.

## Starting jSpectrometer
* Unix-based operating systems (Linux, Solaris and Mac OS X)
	```bash
	$ java -jar jSpectrometer-x.y.z.jar
	```
* Windows
> Right-click on `jSpectrometer-x.y.z.exe` > **Open**.

## Usage
1. To create a diagram you'll need a **data file** with three columns in
the following order:
`Wavelength`, `P_Solvent`, `P_Solution` [see example file](https://github.com/amxyz-cyber/jSpectrometer/blob/master/spectrometer.dat)

2. The jSpectrometer's jar file, the settings file 
[jspectrometer.properties](https://github.com/amxyz-cyber/jSpectrometer/blob/master/jspectrometer.properties), 
the Windows .exe file as well as the library folder `libs` need to share the same directory.

3. Additionally, you'll need to edit the settings file 
`jspectrometer.properties`. Especially, the path to the data file has
to be entered. Without a correct path to the data file, jSpectrometer 
won't work.

4. When starting the textGUI you can hit the `Test` button to check 
whether the data file could be found and read in.

5. Go to **Chart** > **New** and make the necessary settings to
create the diagram. 
* Select the directory for the new diagram
* Enter the file name of the diagram. Please note, the file name has to
end on `.eps`.
* Select the absorbance and transmission colors. 
* Hit the `submit` button

6. Hit the `Export` button to finally create the diagram.

7. Hitting the `Start Window` button will get you back to the main window.

8. To quit **jSpectrometer** go to **Help** > **Exit**
