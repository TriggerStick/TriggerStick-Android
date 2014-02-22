package com.oceusnetworks.divt_uploader;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.content.SharedPreferences;

import com.triggerstick.RunUtils;

public class SecureUploader extends Service {

	/** ID value passed to onStartCommand */
	private int startId;

	public static boolean isServiceRunning = false;

	private static Context _context;

	private boolean wait;

	private Handler mHandler = new Handler();

	private Handler sHandler = new Handler();
	
	private SocketSend mTcpClient;
	
	private int runs = 0; 
	
	private static String serverReply = null;  

	public static Context getAppContext() {
		return _context;
	}

	private int rand_Int(int range) {
		Random rand = new Random();
		int randInt = rand.nextInt(range);
		return randInt;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		_context = getApplicationContext();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		this.startId = startId;
		// Check if this service is running
		if (isServiceRunning) {
			RunUtils.LogInfo("ButtonCheck::onStartCommand() **** SERVICE IS ALREADY RUNNING ***");
		} else {
			isServiceRunning = true;
			// start the Runnables here!!!
			// Start the Main runnable.
			mHandler.postDelayed(main_runnable, rand_Int(Constants.RAND_RANGE));

		}
		//Cannot be Stopped - use StartID to make something stoppable; 
		return START_STICKY;
	}

	
	public static void setMessage(String reply){
		serverReply = reply; 
	}

	public String Combo(){

	}

	public void KeyCodr(String btMessage){ 
		

	    Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            con.startActivity(startMain);

	

	}  


