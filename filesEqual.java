package DataCompression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class filesEqual {
	
	public static boolean filesEqual(File expectedFileName,
	        File resultFileName) {
	    boolean equal;
	    BufferedReader bExp;
	    BufferedReader bRes;
	    String expLine ;
	    String resLine ;

	    equal = false;
	    bExp = null ;
	    bRes = null ;

	    try {
	        bExp = new BufferedReader(new FileReader(expectedFileName));
	        bRes = new BufferedReader(new FileReader(resultFileName));

	        if ((bExp != null) && (bRes != null)) {
	            expLine = bExp.readLine() ;
	            resLine = bRes.readLine() ;

	            equal = ((expLine == null) && (resLine == null)) || ((expLine != null) && expLine.equals(resLine)) ;

	            while(equal && expLine != null)
	            {
	                expLine = bExp.readLine() ;
	                resLine = bRes.readLine() ; 
	                equal = expLine.equals(resLine) ;
	            }
	        }
	    } catch (Exception e) {

	    } finally {
	        try {
	            if (bExp != null) {
	                bExp.close();
	            }
	            if (bRes != null) {
	                bRes.close();
	            }
	        } catch (Exception e) {
	        }

	    }

	    return equal;

	}
}
	