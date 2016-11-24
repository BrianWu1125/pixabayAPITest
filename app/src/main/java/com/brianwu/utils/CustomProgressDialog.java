package com.brianwu.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class CustomProgressDialog {
	private ProgressDialog pd1;
	private boolean isMyDialogShowing;
	private Context mContext;
	public boolean isMyDialogShowing(){
		if(pd1 == null)
			return false;
		return pd1.isShowing();
	}
	
	public ProgressDialog getDialog(){
		return pd1;
	}
	
	public void setCancelable(boolean isCancelable){
		if(pd1 != null)
			pd1.setCancelable(isCancelable);
	}
	public void setUpMyDialog(Context context, String message) {
		//if (pd1 == null) {
			mContext = context;
			pd1 = new ProgressDialog(context );
			pd1.setMessage(message);
			pd1.setIndeterminate(true);
			pd1.setCancelable(true);

			mContext = context;
	//	}
	}
	
	public void setTextDialog(Context context, String message) {
		if (pd1 != null) {
			
			pd1.setMessage(message);
			
		}
	}

	public void killMyDialog() {
		dismissMyDialog();
		pd1 = null;
	}
	public void setNotCancelable(){
		pd1.setCancelable(false);
	}

	public void showMyDialog() {
		isMyDialogShowing = true;
		try {
			if(pd1 != null)
				pd1.show();
		} catch (Exception e) {
			e.printStackTrace();
			try{
				if (mContext != null){
					((Activity) mContext).runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if(pd1 != null)
								pd1.show();
						}
					});
				}
			}catch(Exception ex){
				ex.printStackTrace();
				
			}
		}

	}

	public void dismissMyDialog() {
		// pd1.dismiss();
		try {
			if(pd1 != null)
				pd1.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
			try{
				if (mContext != null){
				((Activity) mContext).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if(pd1 != null)
							pd1.dismiss();
					}
				});
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		isMyDialogShowing = false;
	}

	
}
