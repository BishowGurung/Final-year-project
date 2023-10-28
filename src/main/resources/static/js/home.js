function loadHomeBody(){

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            displayNews(this.responseText);
        }
    });

    xhr.open("GET", "http://localhost:8080/api/healthnews");

    xhr.send();
}

function displayNews(data){
    var jsonObject = JSON.parse(data);
    var jsonArray = jsonObject["news"];

    jsonArray.forEach(function (healthData){
        var keyValuePairs = healthData.split(", ");
        var jsonObject ={};

        keyValuePairs.forEach(function (pairs){
            var parts = pairs.split("=");
            var key = parts[0];
            var value = parts[1];
            jsonObject[key.replace("{","").replace("}","")] = value;
        })

    })

}