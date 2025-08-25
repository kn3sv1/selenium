
window.onload = function () {
  loadMoviesFromServer();
};


function loadMoviesFromServer() {
    fetch("/api/weather/list")
      .then(response => {
        if (!response.ok) throw new Error("Network response was not ok");
        return response.json();
      })
      .then(data => {
        console.log("Weather data:", data);
        // let's build HTML because data we got from server
        showWeather(data);
      })
      .catch(error => console.error("Fetch error:", error));
}

function showWeather(cities) {
    const container = document.getElementById('cities');

    let cityRows = [];
    cities.forEach(function (city) {
        //let genres = movie.genres.join(', ');
        let row = `
            <div class="movie-card">
                <p>City: ${city.city}</p>
                <p>Temperature today: ${city.temperature}</p>
                <p>Windy: ${city.isWindy}</p>
                <img src= "${city.cityPhoto}" />
                <img src= "${city.weatherPhoto}" />
            </div>
        `;
        cityRows.push(row);
    });

    //array convert to string
    container.innerHTML = cityRows.join('') + '<div style="clear:both"></div>';
}