module.exports = {

    init: function () {
        var self = this;
        cordova.exec(
            function (result) {
                console.log('setUp succeeded.' + result);

                if (typeof result == "string") {
                    //
                    if (result == "onAdLoaded") {
                        if (self.onAdLoaded) {
                            self.onAdLoaded();
                        }
                    }
                    if (result == "onAdFailedToLoad") {
                        if (self.onAdFailedToLoad) {
                            self.onAdFailedToLoad();
                        }
                    }
                    if (result == "onInterstitialAdDisplayed") {
                        if (self.onInterstitialAdDisplayed) {
                            self.onInterstitialAdDisplayed();
                        }
                    }
                    if (result == "onRemoveAdsRequested") {
                        if (self.onRemoveAdsRequested) {
                            self.onRemoveAdsRequested();
                        }
                    }
                    if (result == "onInterstitialClosed") {
                        if (self.onInterstitialClosed) {
                            self.onInterstitialClosed();
                        }
					}
                }
                else {
                    
                }
            },
            function (error) {
                console.log('setUp failed.');
            },
            'AdadCordova',
            'init',
            []
        );
    },
    EnableBanner: function () {
        cordova.exec(
            null,
            null,
            'AdadCordova',
            'EnableBanner',
            []
        );
    },
    DisableBanner: function () {
        cordova.exec(
            null,
            null,
            'AdadCordova',
            'DisableBanner',
            []
        );
    },
    prepareInterstitialAd: function () {
        cordova.exec(
            null,
            null,
            'AdadCordova',
            'prepareInterstitialAd',
            []
        );
    },
    showInterstitialAd: function () {
        cordova.exec(
            null,
            null,
            'AdadCordova',
            'showInterstitialAd',
            []
        );
    },
    onAdLoaded: null,
    onAdFailedToLoad: null,
    onInterstitialAdDisplayed: null,
    onRemoveAdsRequested: null,
    onInterstitialClosed: null
};