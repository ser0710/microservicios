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
                    return errorReason;
        }
    },
    createTweet: async function(event){
    var usuario = document.getElementById("user").value;
    var mensaje = document.querySelector('textarea[name="message"]').value
    var tweet = {
        message: mensaje,
        user: usuario
    }
        const options = {
            method: 'POST',
            headers: {
                  'Content-Type': 'application/json'
            },
            body: JSON.stringify(tweet)
        };
        const response = await fetch("http://localhost:8080/tweets" , options);
        location.reload();
    },
    createUser: async function(){
    var usuario = document.getElementById("user").value;

    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var user = {
            name: usuario,
            email: email,
            password: password
        }
    const options = {
                method: 'POST',
                headers: {
                      'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            };
            const response = await fetch("http://localhost:8080/users" , options);
            console.log(response)
            if (response.status!==201) {
                alert("La constraseña debe tener min 8 caracteres con mayusculas y numeros")
            }else{
                window.location.href = "/";
            }
//            window.location.href = "/";
    },

    loginUser: async function(){
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;
        var user = {
                email: email,
                password: password
            }
        const options = {
                    method: 'POST',
                    headers: {
                          'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(user)
                };
                const response = await fetch("http://localhost:8080/users/login" , options);
                if (response.status!==202) {
                    alert("Usuario y/o contraseña incorrectos")
                }else{
                    window.location.href = "/tweets.html";
                }
        },

    logOut:function(){
        window.location.href = "/";
    },

    crearUsuario:function(){
            window.location.href = "/createUser.html";
        }



}


})();