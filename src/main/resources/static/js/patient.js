function searchPatient(){
    var nameValue = document.getElementById("patientSearch").value;
    document.getElementById("patientSearch").value = "";
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            formatPatientDetail(this.responseText)
        }
    });

    xhr.open("GET", "http://localhost:8080/api/patient/?patientName="+nameValue);

    xhr.send();
}

function formatPatientDetail(data){
    var jsonObject = JSON.parse(data);
    var jsonArray = jsonObject["patients"];
    $(".aside-patient-list").empty();
    jsonArray.forEach(function (patientData) {
        var keyValuePairs = patientData.split(", ");
        var jsonObject = {};

        keyValuePairs.forEach(function (pairs) {
            var parts = pairs.split("=");
            var key = parts[0];
            var value = parts[1];
            jsonObject[key.replace("{","").replace("}","")] = value.replace("{","").replace("}","")
        })
        $(".aside-patient-list").append("<div onclick='getPatientDetails("+jsonObject.PatientID+")' class='patient-card'><strong>"+jsonObject.PatientID+" </strong>"+ jsonObject.Name+"<span style='float: right'>&#x1F7E2;"+jsonObject.Gender+"</span></div>");
    })
}

function getPatientDetails(patienId){
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function() {
        if(this.readyState === 4) {
            formatPatientRecord(this.responseText)
        }
    });

    xhr.open("GET", "http://localhost:8080/api/patientDetails/?patientId="+patienId);

    xhr.send();
}
function formatPatientRecord(data){
    var jsonObjectPatient = JSON.parse(data);
    var jsonArray = jsonObjectPatient["patients"];
    $(".aside-patient-list").empty();
    jsonArray.forEach(function (patientData) {
        var keyValuePairs = patientData.split(", ");
        var jsonObject = {};

        keyValuePairs.forEach(function (pairs) {
            var parts = pairs.split("=");
            var key = parts[0];
            var value = parts[1];
            jsonObject[key.replace("{","").replace("}","")] = value.replace("{","").replace("}","")
        })
        $(".aside-patient-list").append("<div STYLE='font-size: 16px;font-family: sans-serif;'>"+
            "Patient ID : " + jsonObject.PatientID +"<br>"+
            "Name : " + jsonObject.Name +"<br>"+
            "Gender : " + jsonObject.Gender +"<br>"+
            "Age : " + jsonObject.Age +"<br><br><br>"+
            "Admission Date : " + jsonObject.AdmissionDate +"<br>"+
            "Admission Time : " + jsonObject.AdmissionTime +"<br>"+
            "Ward : " + jsonObject.Ward +"<br>"+
            "BedNumber : " + jsonObject.BedNumber +"<br>"+
            "</div>");
    })
}
function addPatient(){

}
