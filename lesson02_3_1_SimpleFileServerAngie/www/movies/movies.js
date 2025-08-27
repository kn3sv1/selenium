// Your JSON data
//const movies = [
//    {
//      "photo": "/file/movies/movie07.png",
//      "name": "Alien 3",
//      "likes": 6,
//      "popular": true,
//      "genres": ["horror","adventure"]
//    },
//    {
//      "photo": "/file/movies/movie08.png",
//      "name": "Batman",
//      "likes": 10,
//      "popular": true,
//      "genres": ["adventure","mystery"]
//    },
//    {
//      "photo": "/file/movies/movie09.png",
//      "name": "Mr Deeds",
//      "likes": 8,
//      "popular": true,
//      "genres": ["adventure","comedy"]
//    },
//     {
//       "photo": "/file/movies/movie01.png",
//       "name": "Mr Deeds 2",
//       "likes": 1,
//       "popular": true,
//       "genres": ["adventure","comedy2"]
//     }
//];

window.onload = function () {
  loadMoviesFromServer();
};


function loadMoviesFromServer() {
    fetch("/api/movie/list")
      .then(response => {
        if (!response.ok) throw new Error("Network response was not ok");
        return response.json();
      })
      .then(data => {
        console.log("Movies data:", data);
        // let's build HTML because data we got from server
        showMovies(data);
      })
      .catch(error => console.error("Fetch error:", error));
}

function showDetails(id) {
    fetch("/api/movie/item/" + id)
      .then(response => {
        if (!response.ok) throw new Error("Network response was not ok");
        return response.json();
      })
      .then(movie => {
        console.log("Movie: ", movie);

        let genres = movie.genres.join(', ');
        let html = `
                    <h2>Details of Movie</h2>
                    <div class="movie-card">
                        <p>Title: ${movie.name}</p>
                        <p>Amount of likes: ${movie.likes}</p>
                        <p>Is popular: ${movie.popular}</p>
                        <p>Genres: ${genres}</p>
                        <img src="${movie.photo}" />
                    </div>
                `;
        document.getElementById('movie_details').innerHTML = html;
      })
      .catch(error => console.error("Fetch error:", error));
}

function showMovies(movies) {
    const container = document.getElementById('movies');

    let movieRows = [];
    movies.forEach(function (movie) {
        let genres = movie.genres.join(', ');
        let row = `
            <div class="movie-card">
                <p>Title: ${movie.name}</p>
                <p>Amount of likes: ${movie.likes}</p>
                <p>Is popular: ${movie.popular}</p>
                <p>Genres: ${genres}</p>
                <p><a href="javascript:showDetails(${movie.id})">Details</a></p>
                <img src="${movie.photo}" />
            </div>
        `;
        movieRows.push(row);
    });

    //array convert to string
    container.innerHTML = movieRows.join('') + '<div style="clear:both"></div>';
}