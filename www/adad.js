module.exports = {
	Position: {
        TOP: 100,
        BOTTOM: 101
    },
    setUp: function() {
        cordova.exec(
			function (result) {
				console.log('setUp succeeded.');
			},
			null,
            'AdadAd',
            'setUp',
            []
        ); 
    },
    AddBanner: function(position) {
        cordova.exec(
			function (result) {
				console.log('Banner Success.');
			},
			null,
            'AdadAd',
            'addBanner',
            [position]
        ); 
    },
    EnableBanner: function() {
        cordova.exec(
			function (result) {
				console.log('Banner Enabled.');
			},
			null,
            'AdadAd',
            'enableBanner',
            []
        ); 
    },
    DisableBanner: function() {
        cordova.exec(
			function (result) {
				console.log('Banner Disabled.');
			},
			null,
            'AdadAd',
            'disableBanner',
            []
        ); 
    },
    LoadInterstitial: function () {
        var self = this;
        cordova.exec(
             function (result) {
                if (typeof result == "string") {
                    if (result == "onAdadAdLoaded") {
                        if (self.onAdLoaded) {
                            self.onAdLoaded();
                        }
                    }
                    if (result == "onAdadAdFailedToLoad") {
                        if (self.onAdFailedToLoad) {
                            self.onAdFailedToLoad();
                        }
                    }
                    if (result == "onAdadInterstitialAdDisplayed") {
                        if (self.onInterstitialAdDisplayed) {
                            self.onInterstitialAdDisplayed();
                        }
                    }
                    if (result == "onAdadRemoveAdsRequested") {
                        if (self.onRemoveAdsRequested) {
                            self.onRemoveAdsRequested();
                        }
                    }
                    if (result == "onAdadInterstitialClosed") {
                        if (self.onInterstitialClosed) {
                            self.onInterstitialClosed();
                        }
					}
                    if (result == "onAdadMessageReceive") {
                        if (self.onMessageReceive) {
                            self.onMessageReceive();
                        }
					}
                }
                console.log(result);
            },
            function (error) {
                console.log('failed.');
            },
            'AdadAd',
            'loadInterstitialAd',
            []
        );
    },
    ShowInterstitial: function () {
        cordova.exec(
            null,
            null,
            'AdadAd',
            'showInterstitialAd',
            []
        );
    },
    onAdLoaded: null,
    onAdFailedToLoad: null,
    onInterstitialAdDisplayed: null,
    onRemoveAdsRequested: null,
    onMessageReceive: null,
    onInterstitialClosed: null
};