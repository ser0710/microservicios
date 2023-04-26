var app = (function(){
return{
    getTweets: async function(){
        const token = localStorage.getItem('token'); // Obtener el token almacenado
            if (!token) {
                // Redirigir al usuario a la página de inicio de sesión si no hay token
                window.location.href = "/index.html";
                return;
            }

            const options = {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ${token}'
                }
            };
                const response = await fetch("http://ec2-54-90-214-96.compute-1.amazonaws.com:8080/tweets", options);
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
        const response = await fetch("http://ec2-54-90-214-96.compute-1.amazonaws.com:8080/tweets" , options);
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
            const response = await fetch("http://ec2-54-90-214-96.compute-1.amazonaws.com:8080/users" , options);
            console.log(response)
            if (response.status!==201) {
                alert("La constraseña debe tener min 8 caracteres con mayusculas y numeros")
            }else{
                window.location.href = "/index.html";
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
                try{
                const response = await fetch("http://ec2-54-90-214-96.compute-1.amazonaws.com:8080/users/login" , options);
                if (response.status!==202) {
                    alert("Usuario y/o contraseña incorrectos")
                }else{
                    const data = await response.json();
                    const token = data.token; // Obtener el token del cuerpo de la respuesta
                    localStorage.setItem('token', token);
                    window.location.href = "/tweets.html";
                }
                }catch (error){
                    console.error(error)
                    alert("error al iniciar sesion")
                }
        },

    logOut:function(){
        window.location.href = "/index.html";
    },

    crearUsuario:function(){
            window.location.href = "/createUser.html";
        }



}


})();