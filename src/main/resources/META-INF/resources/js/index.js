var apiclient = apiclient;
var app = (function(){
    function getTweets(){
        apiclient.getTweets(putInDiv);
    }

    var putInDiv = function(data){
        console.log(data)
    }

    return{
        getTweets: getTweets
    }

})();
