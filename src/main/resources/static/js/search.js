function getValue(){
    var nameValue = document.getElementById("querySearch").value;
    $(".aside-search-result-list").empty();
    $(".aside-search-result-list").append(nameValue);

}

