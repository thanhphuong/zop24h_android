package vn.fiosoft.zop.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.util.Log;

public class FileManager {
	
	/**
	 * Read all content of file
	 * @param path
	 * @return all content of file, otherwise return empty. 
	 */
	public String readFromFile(Context context, String fileName){		

	    String ret = "";

	    try {
	        InputStream inputStream = context.openFileInput(fileName);

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	        }
	    }
	    catch (FileNotFoundException e) {
	        Log.e("login activity", "File not found: " + e.toString());
	    } catch (IOException e) {
	        Log.e("login activity", "Can not read file: " + e.toString());
	    }

	    return ret;
	}
	
	/**
	 * save data in memory of system
	 * @param path
	 * @param content
	 * @return true if success, otherwise return false.
	 */
	public boolean writeToFile(Context context, String fileName, String data){
		try {
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	        return true;
	    }
	    catch (IOException e) {
	        Log.e("Exception", "File write failed: " + e.toString());
	        return false;
	    } 
	}

}
