function getValue(){
    var nameValue = document.getElementById("querySearch").value;
    document.getElementById("querySearch").value = "";
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            $(".nhs-api").empty();
            $(".nhs-api").append(this.responseText);
        }
    });

    xhr.open("GET", "http://localhost:8080/api/?query="+nameValue);

    xhr.send();
}

