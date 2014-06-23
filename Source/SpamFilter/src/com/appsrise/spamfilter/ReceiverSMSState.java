package com.appsrise.spamfilter;

import java.util.Vector;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * ReceiverSMSState The Class ReceiverSMSState receives broadcast when a new sms
 * is received by the phone.
 * 
 * version 1.0
 */
public class ReceiverSMSState extends BroadcastReceiver {

	private Vector<SmsInfo> lstSMS;

	@Override
	public void onReceive(Context context, Intent intent) {

		//TODO move complete all code in an IntentService
		lstSMS = listenIncomingSMS(context, intent);
		if (lstSMS != null && lstSMS.size() > 0) {

			String smsBody = null;
			for (SmsInfo sms : lstSMS) {
				smsBody = sms.getBody();
				if (smsBody != null && smsBody.length() > 0) {
					
					Log.d("Yamba",smsBody );
					smsBody = smsBody.toUpperCase().trim();

					if (smsBody.contains("TAXI")) {
						try {
							
							NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
							nm.cancel(0);
							
							Log.d("Yamba","notification removed" );
							
							// try to be the only broadcast taking this SMS
							try{
								abortBroadcast();
							}
							catch(Throwable e){}
							//silent the SMS
							//TODO Intent(SessionValues.getContext(), SMSServiceSilentMode.class));

							// deactivate the system notification

						} catch (Throwable e) {
						}
					}
				}
			}
		}
	}

	/**
	 * listenIncomingSMS
	 * 
	 * Listen incoming sms.
	 * 
	 * @param context
	 *            the context
	 * @param intent
	 *            the intent
	 * @return the array list of one or more sms messages.
	 * 
	 */
	private Vector<SmsInfo> listenIncomingSMS(Context context, Intent intent) {
		try {

			Vector<SmsInfo> lst = new Vector<SmsInfo>();

			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++)
					messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

				SmsInfo sms;
				long person;
				for (SmsMessage message : messages) {
					sms = new SmsInfo();
					sms.setDate(java.lang.System.currentTimeMillis());
					sms.setAddress(message.getOriginatingAddress());
					sms.setBody(message.getMessageBody());

					sms.setType(1);
					if (message.getStatusOnIcc() == SmsManager.STATUS_ON_ICC_SENT
							|| message.getStatusOnIcc() == SmsManager.STATUS_ON_ICC_UNSENT) {
						sms.setType(2);
					}

					sms.setHidden(1);
					sms.setBackup(0);
					sms.setId(-1);

					lst.add(sms);
				}
				return lst;
			}

		} catch (Throwable e) {
		}

		return null;
	}

}