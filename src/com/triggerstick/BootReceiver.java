/******************************************************************************
 * FILE: BootReceiver.java
 ******************************************************************************/

package com.triggerstick;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.oceusnetworks.divt_uploader.DebugLog;
import com.oceusnetworks.divt_uploader.SecureUploader;

public class BootReceiver extends BroadcastReceiver {

	boolean started = false;

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

			started = true;
			Intent intentForService = new Intent(context, ButtonCheck.class);
			context.startService(intentForService);
		}
	}

}
