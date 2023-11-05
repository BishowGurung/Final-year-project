function getValue(){
    var nameValue = document.getElementById("querySearch").value;
    document.getElementById("querySearch").value = "";
    $(".aside-search-result-list").empty();
    $(".aside-search-result-list").append(nameValue);
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            console.log(this.responseText);
        }
    });

    xhr.open("GET", "http://localhost:8080/api/?query="+nameValue);

    xhr.send();
}

