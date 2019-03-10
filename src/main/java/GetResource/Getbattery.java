package GetResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import performance.Main;

public class Getbattery {
	public static void main( String[] args ) throws IOException, InterruptedException
	{
		for(int i=0;i<100;i++){
		System.out.print( battery() );
		Thread.sleep(3000);
		}
	}
	public static double battery() throws IOException{
		double	batt	= 0;
		Runtime runtime = Runtime.getRuntime();
		Process proc	= runtime.exec( "adb -s "+ Main.devices+" shell dumpsys battery" );
		String	str3="0";
		try {
			if ( proc.waitFor() != 0 ){
				System.err.println( "exit value = " + proc.exitValue() );
			}
			BufferedReader in = new BufferedReader( new InputStreamReader(proc.getInputStream() ) );
			StringBuffer	stringBuffer	= new StringBuffer();
			String		line		= null;
			while ( (line = in.readLine() ) != null ){
				stringBuffer.append( line + " " );
				 if(line.contains("temperature")){
	            		String str=line.substring(line.indexOf(":")+2,line.length());
	               }
				 if(line.contains("level")){
	            		str3=line.substring(line.indexOf(":")+2,line.length());
	               }
			}
			batt = Double.parseDouble( str3.trim() );
			} catch ( InterruptedException e ) {
			batt=-0.1;
			System.err.println( e );
			} finally {
			try {
				proc.destroy();
			} catch ( Exception e2 ) {
			}
		}
		return(batt);
	}
	
	
	
}


