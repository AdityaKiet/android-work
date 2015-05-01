package com.o9pathshala.logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;


public class ALogger {
	/*@SuppressLint("SdCardPath")
	public static void createLogFile(Context context) {
		try {
			File exst = Environment.getExternalStorageDirectory();
			String exstPath = exst.getPath();
			File folder = new File(exstPath + "/.O9 Pathshala");
			folder.mkdir();
			File file = new File("/sdcard/.O9 Pathshala/log.log");
			file.createNewFile();
			FileOutputStream stream = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(stream);
			writer.append("");
			writer.close();
			stream.close();
			SharedPreferences sharedPreferences = PreferenceManager
					.getDefaultSharedPreferences(context);
			SharedPreferences.Editor edit = sharedPreferences.edit();
			edit.putBoolean("isDirectory", true);
			edit.commit();
		} catch (Exception e) {
			Log.e("error", e.getMessage());
		}
	}

	@SuppressLint("SdCardPath")
	public static void writeInLog(String string) throws IOException {
		File file = new File("/sdcard/.O9 Pathshala/log.log");
		FileInputStream istream = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				istream));
		String row = "";
		String buffer = "";
		while ((row = reader.readLine()) != null) {
			buffer += row;
		}
		buffer += string;
		reader.close();
		file.createNewFile();
		FileOutputStream ostream = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(ostream);
		writer.append(buffer);
		writer.close();
		ostream.close();
	}*/
	public static void printLog(Context context){
	    String filename = context.getExternalFilesDir(null).getPath() + File.separator + "my_app.log";
	    String command = "logcat -d *:V";

	    Log.d("AndroidRuntime", "command: " + command);

	    try{
	        Process process = Runtime.getRuntime().exec(command);

	        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String line = null;
	        try{
	            File file = new File(filename);
	            file.createNewFile();
	            FileWriter writer = new FileWriter(file);
	            while((line = in.readLine()) != null){
	                writer.write(line + "\n");
	            }
	            writer.flush();
	            writer.close();
	        }
	        catch(IOException e){
	            e.printStackTrace();
	        }
	    }
	    catch(IOException e){
	        e.printStackTrace();
	    }
	}
}