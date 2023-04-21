var app = (function(){
return{
    getTweets: async function(){
        const options = {
                    method: 'GET',
                    headers: {
                      'Content-Type': 'application/json'
                    }
                  };
                const response = await fetch("http://localhost:8080/tweets", options);
                if (response.ok) {
                const jsonResponse = await response.json();
                jsonResponse.map((tweet) => {
                    document.getElementById("tweets").innerHTML += "<div style='display: grid; place-items: center; margin: 10px'>" + tweet.message + "</div>"
                })

                }else{
                    const errorReason = await response.json()
                    callback(errorReason);
        }
    }


}


})();