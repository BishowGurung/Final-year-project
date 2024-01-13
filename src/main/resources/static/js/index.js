function authenticateUser(){
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            localStorage.setItem("s_id",this.responseText)

            window.location.href = "http://localhost:8080/home.html";
        }
    });

    xhr.open("GET", "http://localhost:8080/authenticateUser?username="+username+"&password="+password);

    xhr.send();

}

function createUser(){
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            localStorage.setItem("s_id",this.responseText)
        }
    });

    xhr.open("GET", "http://localhost:8080/createUser?username="+username+"&password="+password);

    xhr.send();
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
}