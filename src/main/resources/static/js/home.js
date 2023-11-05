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
    $(".healthNews-container").empty();
    jsonArray.forEach(function (healthData){
        var keyValuePairs = healthData.split(", ");
        var jsonObject ={};

        keyValuePairs.forEach(function (pairs){
            var parts = pairs.split("=");
            var key = parts[0];
            var value = parts[1];
            jsonObject[key.replace("{","").replace("}","")] = value;
        })

        var newscardt = "<div class='news-card'><div style='background-image: url("+jsonObject.urlToImage+")' class='news-img'></div>"

        var newscardb = "<div class='news-body'><h5>"+jsonObject.title+"</h5><p>"+jsonObject.Description+
            "</p><br><p>Source:" +jsonObject.author+" - " + jsonObject.source +
            "<span style='float: right'>Published at: "+ jsonObject.PublishedAt.replace("}","").replace("Z","").replace("T"," ")+"</span></p></div></div>" ;
        $(".healthNews-container").append(newscardt+newscardb);

    })

}