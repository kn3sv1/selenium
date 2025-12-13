const fetchWeather = () => {
      fetch("/api/weather/list")
            .then(response => response.json())
            .then(data => {

            let weatherRows = [];
            data.forEach(function (weather) {
                let row = `
                    <div class="weather-card">
                        <p>City: <a href="/weather/city/${weather.city}">${weather.city}</a></p>
                        <p>Temperature today: ${weather.temperature}</p>
                        <p>Windy: ${weather.isWindy}</p>
                        <img src= "${weather.cityPhoto}" />
                        <img src= "${weather.weatherPhoto}" />
                    </div>
                `;
                weatherRows.push(row);
            });
            //array convert to string
            document.getElementById("weather-data").innerHTML = weatherRows.join('') + '<div style="clear:both"></div>';

            });
}

window.onload = () => {
  fetchWeather();
};