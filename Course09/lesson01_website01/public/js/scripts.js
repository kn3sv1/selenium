document.getElementById("fetchWeather").addEventListener("click", () => {
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
            document.getElementById("result").innerHTML = weatherRows.join('') + '<div style="clear:both"></div>';


    }); // end of fetch
}); // click event


let fetchNews = () => {
    fetch("/api/news/list")
        .then(response => response.json())
        .then(data => {


            let newsRows = [];
            data.forEach(function (news) {
                let row = `
                    <div class="weather-card">
                        <p>News ID: ${news.id}</p>
                        <h2>Title: ${news.title}</h2>
                        <p>Description: ${news.description}</p>
                        <p>Text: ${news.text}</p>
                        <img src= "${news.image}" />
                        <p style="color: red;">Published at: ${news.publishedAt}</p>
                        <p>Likes: ${news.likes}</p>
                        <p><a href="/api/news/item/${news.id}">Detail page</a></p>
                        <p>Category: ${news.category}</p>
                    </div>
                `;
                newsRows.push(row);
            });

            document.getElementById("news").innerHTML = newsRows.join('<br /><br />') + '<div style="clear:both"></div>';
    });
};
document.getElementById("fetchNews").addEventListener("click", fetchNews);

// without pressing any button - as soon as page is loaded we call function
window.onload = () => {
  fetchNews();
};