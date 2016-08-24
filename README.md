Adad Cordova plugin
====================
Adad plugin for Cordova and Phonegap.<br/>

- Adad android SDK 3.1<br/>
- Cordova version >3.0<br/>

## Install plugin ##

#### You need to add 3 parameters in plugin:
```
ADAD_TOKEN_CODE - your adad token
ADAD_TEST_MODE - true/false the adad test mode
ADAD_LOG_MODE - true/false log mode
```

### Cordova cli ###
https://cordova.apache.org/docs/en/edge/guide_cli_index.md.html#The%20Command-Line%20Interface - npm install -g cordova@6.0.0
```c
cordova plugin add cordova-plugin-adad
(when build error, use github url: cordova plugin add cordova plugin add https://github.com/miladesign/cordova-plugin-adad)
```

### Xdk ###
https://software.intel.com/en-us/intel-xdk - Download XDK - XDK PORJECTS - [specific project] - CORDOVA HYBRID MOBILE APP SETTINGS - Plugin Management - Add Plugins to this Project - Third Party Plugins -
```c
Plugin Source: Cordova plugin registry
Plugin ID: cordova-plugin-adad
```

### Cocoon ###
https://cocoon.io - Create project - [specific project] - Setting - Plugins - Custom - Git Url: https://github.com/miladesign/cordova-plugin-adad.git - INSTALL - Save<br>

### Phonegap build service (config.xml) ###
https://build.phonegap.com/ - Apps - [specific project] - Update code - Zip file including config.xml
```c
<gap:plugin name="cordova-plugin-adad" source="npm" />
```

### Construct2 ###
Download construct2 plugin<br>
https://miladesign.ir/adad-construct


## Use Plugin ##

### 1.Initialize adad cordova plugin
init plugin after deviceready event <br />

    window.adad.setUp();
    
### 2.Add banner 

you can add the banner at the TOP/BOTTOM of your app.
windows.adad.Position holds  positions const .

    window.adad.AddBanner(window.adad.Position.BOTTOM);

### 3.Enable banner 

    window.adad.EnableBanner();

### 4.Disable banner 

    window.adad.DisableBanner();

###  5.Show interstitial 
load Interstitial ,and then show it in onAdLoaded function or show it when your game over.

```
function loadInterstitial(){
	window.adad.LoadInterstitial();
}
	
function showInterstitial(){
	window.adad.ShowInterstitial();
}

function onAdLoaded(){
	alert('Interstitial is ready to show');
	// or call showInterstitial()
}

function onAdFailedToLoad(){
	alert('Interstitial is failed to load');
}

function onInterstitialClosed(){
	alert('Interstitial is closed);
}
```
## Examples ##
<a href="https://github.com/miladesign/cordova-plugin-adad/blob/master/example/index.html">Click to see!</a><br>

## Test ##
You can run following test apk.
http://s2.picofile.com/file/8264956568/adad_cordova.apk.html

## Useful links ##
Cordova Plugins<br>
http://miladesign.ir/cordova

# Credite and donate #
Created by: Milad Mohammadi
Email: Rezagah.Milad@Gmail.com
Web: http://miladesign.ir
Telegram ID: @MilaDesign

donate: http://miladesign.ir/donate