package ir.adad.cordova;

import ir.adad.client.Adad;
import ir.adad.client.InterstitialAdListener;
import ir.adad.client.Banner;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class AdadAd extends CordovaPlugin {
	private static final String LOG_TAG = "MilaDesign Adad";
	private static CallbackContext callbackContextKeepCallback = null;
	private static Activity mActivity = null;
	public CordovaInterface cordova = null;
	protected Banner adadBanner;
	private static final int POSITION_TOP = 100;
	private static final int POSITION_BOTTOM = 101;
	
	@Override
	public void initialize (CordovaInterface initCordova, CordovaWebView webView) {
		 Log.e (LOG_TAG, "initialize");
		  cordova = initCordova;
		  super.initialize (cordova, webView);
	}
	
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext CallbackContext) throws JSONException {
		if (action.equals("setUp")) {
			try {
				setUp(action, args, CallbackContext);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			return true;
		}
		if (action.equals("addBanner")) {
			addBanner(action, args, CallbackContext);
			return true;
		}
		if (action.equals("enableBanner")) {
			enableBanner(action, args, CallbackContext);
			return true;
		}
		if (action.equals("disableBanner")) {
			disableBanner(action, args, CallbackContext);
			return true;
		}
		if (action.equals("loadInterstitialAd")) {
			loadInterstitialAd(action, args, CallbackContext);
		    return true;
		}
		if (action.equals("showInterstitialAd")) {
			showInterstitialAd(action, args, CallbackContext);
		    return true;
		}
	    return false;
	}
	
	private void setUp(String action, JSONArray args, CallbackContext callbackContext) throws JSONException, NameNotFoundException {
		callbackContextKeepCallback = callbackContext;
		try {
            ApplicationInfo ai = cordova.getActivity().getPackageManager().getApplicationInfo(
            		cordova.getActivity().getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String AdadToken = bundle.getString("AdadToken");
            System.out.println("AdadToken : " + AdadToken);
        } catch (NameNotFoundException e) {}
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				_setUp();
			}
		});
	}


	private void addBanner(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		callbackContextKeepCallback = callbackContext;
		final int position = args.getInt(0);
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					_addBanner(position);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void enableBanner(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		callbackContextKeepCallback = callbackContext;
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				_enableBanner();
			}
		});
	}
	
	private void disableBanner(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		callbackContextKeepCallback = callbackContext;
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				_disableBanner();
			}
		});
	}
	
	private void loadInterstitialAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		callbackContextKeepCallback = callbackContext;
		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_loadInterstitialAd(callbackContextKeepCallback);
			}
		});
	}
	
	private void showInterstitialAd(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		cordova.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				_showInterstitialAd();
			}
		});
	}
	
	private void _setUp() {
	    mActivity = cordova.getActivity();
		Adad.initialize(mActivity.getApplicationContext());
		callbackContextKeepCallback.success();
	}
	
	private void _addBanner(int position) throws JSONException {
	    mActivity = cordova.getActivity();
	    int i = position;
	    FrameLayout bannerLayout = new FrameLayout(mActivity);
	    FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(-2, -2);
	    switch (i) {
		    case POSITION_TOP:
		    	fLayoutParams.gravity = 49;
		    	break;
		    case POSITION_BOTTOM:
		    	fLayoutParams.gravity = 81;
	    }
	    bannerLayout.setLayoutParams(fLayoutParams);
	    ((ViewGroup)AdadAd.this.getParentGroup().getParent()).addView(bannerLayout, 1);
	    Banner banner = new Banner(mActivity);
	    bannerLayout.addView(banner);
		callbackContextKeepCallback.success();
	}

	private ViewGroup getParentGroup() {
	    try {
	      return (ViewGroup)this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
	    } catch (Exception ex) {
	    	try {
	    		return (ViewGroup)this.webView.getClass().getMethod("getParent", new Class[0]).invoke(this.webView, new Object[0]);
	    	} catch (Exception e) {
	    		e.printStackTrace(); 
	        }
	    }
	    return null;
	}
	
	private void _enableBanner() {
	    mActivity = cordova.getActivity();
		Adad.enableBannerAds();
		callbackContextKeepCallback.success();
	}

	private void _disableBanner() {
	    mActivity = cordova.getActivity();
		Adad.disableBannerAds();
		callbackContextKeepCallback.success();
	}
	
	private void _loadInterstitialAd(CallbackContext callbackContext) {
	    mActivity = cordova.getActivity();
	    Adad.prepareInterstitialAd(new InterstitialAd());
	    callbackContextKeepCallback = callbackContext;
	}

	private void _showInterstitialAd() {
	    mActivity = cordova.getActivity();
	    Adad.showInterstitialAd(mActivity);
	}
	
	class InterstitialAd implements InterstitialAdListener {
		
		@Override
		public void onAdLoaded() {
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdadAdLoaded");
			pr.setKeepCallback(true);
			AdadAd.callbackContextKeepCallback.sendPluginResult(pr);
		}

		@Override
		public void onAdFailedToLoad() {
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdadAdLoaded");
			pr.setKeepCallback(true);
			AdadAd.callbackContextKeepCallback.sendPluginResult(pr);
		}

		@Override
		public void onMessageReceive(JSONObject paramJSONObject) {
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdadMessageReceive");
			pr.setKeepCallback(true);
			AdadAd.callbackContextKeepCallback.sendPluginResult(pr);
		}

		@Override
		public void onRemoveAdsRequested() {
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdadRemoveAdsRequested");
			pr.setKeepCallback(true);
			AdadAd.callbackContextKeepCallback.sendPluginResult(pr);
		}

		@Override
		public void onInterstitialAdDisplayed() {
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdadInterstitialAdDisplayed");
			pr.setKeepCallback(true);
			AdadAd.callbackContextKeepCallback.sendPluginResult(pr);
		}

		@Override
		public void onInterstitialClosed() {
			PluginResult pr = new PluginResult(PluginResult.Status.OK, "onAdadInterstitialClosed");
			pr.setKeepCallback(true);
			AdadAd.callbackContextKeepCallback.sendPluginResult(pr);
		}
	}
}
