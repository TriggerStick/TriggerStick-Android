/******************************************************************************
 * FILE: RunUtils.java
 ******************************************************************************/

package com.triggerstick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
//import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

/**
 * Collection of miscellaneous methods to support the SERVICE.
 */
public class RunUtils {

	private static Context _context;
	private static final String LOG_TAG = "RunUtils";

	public RunUtils(Context context) {
		RunUtils._context = context;
	}

	public static String sourceDir() {
		Context appContext = SecureUploader.getAppContext();
		String sourceDirectory = appContext.getFilesDir().toString();
		return sourceDirectory;
	}

	/**
	 * Returns custom-formatted current time.
	 * 
	 * @return Custom-formatted current time.
	 */
	 @SuppressLint("SimpleDateFormat")
	public static String getDateAsString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"HH:mm:ss:SS ';' MM dd yyyy");
		return simpleDateFormat.format(new Date());
	}

	 @SuppressLint("SimpleDateFormat")
	public static String getDateAsStringShort() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMdd_HHmmss");
		return simpleDateFormat.format(new Date());
	}

	/*
	 * Exposed interface to log an Info message.
	 */
	public static void LogInfo(String msg) {
		Log.d(LOG_TAG, msg);
	}

	public static List<String> exec2CommandLineString(String command) {
		Runtime runtime = Runtime.getRuntime();
		Process proc = null;
		OutputStreamWriter osw = null;
		List<String> listy = new ArrayList<String>();
		try {
			proc = runtime.exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				// System.out.println(line);
				// Log.e("execCommandLine()", line);
				listy.add(line);
			}
			osw = new OutputStreamWriter(proc.getOutputStream());
			// osw.write(command);
			osw.flush();
			osw.close();
		} catch (IOException ex) {
			Log.e("execCommandLine()", "Command resulted in an IO Exception: "
					+ command);
			return listy;
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
				}
			}
		}
		try {
			proc.waitFor();
		} catch (InterruptedException e) {
		}
		if (proc.exitValue() != 0) {
			Log.e("execCommandLine()", "Command returned error: " + command
					+ "\n  Exit code: " + proc.exitValue());
		}
		return listy;
	}

	public static void execCommandLine(String command) {
		Runtime runtime = Runtime.getRuntime();
		Process proc = null;
		OutputStreamWriter osw = null;
		try {
			proc = runtime.exec(command);
			osw = new OutputStreamWriter(proc.getOutputStream());
			// osw.write(command);
			osw.flush();
			osw.close();
		} catch (IOException ex) {
			Log.e("execCommandLine()", "Command resulted in an IO Exception: "
					+ command);
			return;
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
				}
			}
		}
		try {
			proc.waitFor();
		} catch (InterruptedException e) {
		}
		if (proc.exitValue() != 0) {
			Log.e("execCommandLine()", "Command returned error: " + command
					+ "\n  Exit code: " + proc.exitValue());
		}
	}

	public static List<String> exec2CommandLine(String command, String search) {
		Runtime runtime = Runtime.getRuntime();
		Process proc = null;
		OutputStreamWriter osw = null;
		List<String> listy = new ArrayList<String>();
		List<String> outprops = new ArrayList<String>();
		try {
			proc = runtime.exec(command);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				// System.out.println(line);
				// Log.e("execCommandLine()", line);
				listy.add(line);
			}
			osw = new OutputStreamWriter(proc.getOutputStream());
			// osw.write(command);
			osw.flush();
			osw.close();
		} catch (IOException ex) {
			Log.e(Constants.APP_NAME, "Command resulted in an IO Exception: "
					+ command);
			return outprops;
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
				}
			}
		}
		try {
			proc.waitFor();
		} catch (InterruptedException e) {
		}
		if (proc.exitValue() != 0) {
			Log.e(Constants.APP_NAME, "Command returned error: " + command
					+ "\n  Exit code: " + proc.exitValue());
		}
		if (listy != null) {
			for (int i = 0; i < listy.size(); i++) {
				String hold = listy.get(i);
				int p = hold.indexOf(search);
				if (p > 0) {
					outprops.add(hold);
				}
			}
		}
		return outprops;
	}
}
