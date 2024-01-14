function formatPatientMonitor(data){
    var jsonObjectPatient = JSON.parse(data);
    var jsonArray = jsonObjectPatient["patients"];
    $(".p-mon-container").empty();
    jsonArray.forEach(function (patientData) {
        var keyValuePairs = patientData.split(", ");
        var jsonObject = {};

        keyValuePairs.forEach(function (pairs) {
            var parts = pairs.split("=");
            var key = parts[0];
            var value = parts[1];
            jsonObject[key.replace("{","").replace("}","")] = value.replace("{","").replace("}","")
        })
        $(".p-mon-container").append(
            "<span style='width: 2px;'></span><div className='p-mon'>"+
                "<div className='p-mon-img' style='background-image: url('../img/mainlogo.png');width: 10rem;height: 9rem;padding: 0.5rem 0rem;background-size:contain;background-repeat: no-repeat;'></div>"+
                    "<div style='width: 20rem;height: 9rem;background-color: antiquewhite;padding: 0.5rem 1rem;font-size: 13px;font-weight: bold;'>"+
                    "<p style='width: 90%;'>Name: "+jsonObject.Name+"</p>"+
                    "<p style='width: 90%;'><span>Blood Pressure : </span><span style='color: red;right: 0;'>"+jsonObject.BloodPressure+"</span></p>"+
                    "<p style='width: 90%;'><span>Body Temperature : </span><span style='color: red;right: 0;'>"+jsonObject.BodyTemperature+"</span></p>"+
                    "<p style='width: 90%;'><span>Pulse : </span><span style='color: red;right: 0;'>"+jsonObject.Pulse+"</span></p>"+
                    "<p style='width: 90%;'><span>Respiratory Rate : </span><span style='color: red;right: 0;'>"+jsonObject.RespiratoryRate+"</span></p></div></div>"
        );
    })
}

function runPatientCheck() {
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === 4) {
            formatPatientMonitor(this.responseText)
        }
    });

    xhr.open("GET", "http://localhost:8080/api/patientMonitor");

    xhr.send();
    setTimeout(runPatientCheck, 10000)
}