document.getElementById("fetchWeather").addEventListener("click", () => {
    fetch("/api/weather")
    //fetch("/data/weather.json")
        .then(response => response.json())
        .then(data => {
            document.getElementById("result").innerHTML =
                `<p>Temperature: ${data.temp}°C</p>
                 <p>Condition: ${data.condition}</p>
                 <p>Date: ${data.date}</p>`;
        })
        .catch(err => {
            console.error("Error fetching weather:", err);
        });
});