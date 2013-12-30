package netgest.bo.xwc.build;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Concatenates all of the files found inside a file named javascriptIncludes and
 * cssIncludes
 * 
 */
public class Concatenator {
	
	public static void main(String[] args) throws IOException{

		if (args.length < 4 ){
			System.out.println(
					"Usage Concatenator en,pt,es '/path/to/root/' 'path/to/write' 'path/to/files/where/files/containing/resources/are'"
			);
		}
		
		String csvLanguages = args[0];
		String realPath = args[1];
		String writeTo = args[2];
		String pathToResources = args[3];
		
		String[] languages = csvLanguages.split(",");
		String jsFiles = pathToResources + File.separator + "javascriptIncludes";
		String cssFile = pathToResources + File.separator +  "cssIncludes";
		
		
		//Read File Line By Line
		for (String currentLang : languages) {
			
			InputStream includesStream = new FileInputStream(new File(jsFiles));
			DataInputStream in = new DataInputStream(includesStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			StringBuilder content = new StringBuilder(10000);
			while ((strLine = br.readLine()) != null)   {
				if (strLine != null && strLine.length() > 0){
					String[] file = strLine.split(":");
					String fileName = file[1];
					String correctFilename = fileName.replace("%LANG%", currentLang);
					String filePath = realPath + File.separator +correctFilename;
					content.append(readContent(new FileInputStream(new File(filePath))));
				}
			}
			
			String output = writeTo + File.separator + "concatenated_" + currentLang + ".js";
			BufferedWriter writer = new BufferedWriter( new FileWriter( output));
		    writer.write( content.toString() );
		    writer.close();
		    br.close();
		}
		
		InputStream includesStreamCss =  new FileInputStream(new File(cssFile)); 
		DataInputStream inCss = new DataInputStream(includesStreamCss);
		BufferedReader brCss = new BufferedReader(new InputStreamReader(inCss));
		String strLineCss;
		StringBuilder contentCss = new StringBuilder(10000);
		while ((strLineCss = brCss.readLine()) != null)   {
			if (strLineCss != null && strLineCss.length() > 0){
				String[] file = strLineCss.split(":");
				String fileName = realPath + File.separator + file[1];
				contentCss.append(readContent(new FileInputStream(new File(fileName))));
			}
		}
		
		String output = writeTo + File.separator + "concatenated.css";
		BufferedWriter writer = new BufferedWriter( new FileWriter( output));
	    writer.write( contentCss.toString() );
	    writer.close();
	    brCss.close();
		
	}
	
	private static String readContent(InputStream stream) throws IOException{
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		
		StringBuilder content = new StringBuilder(10000);
		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
			if (strLine != null && strLine.length() > 0){
				content.append(strLine);
				content.append( System.getProperty("line.separator"));
			}
		}
		return content.toString();
	}

}
