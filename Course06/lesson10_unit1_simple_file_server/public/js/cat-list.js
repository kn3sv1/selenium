
function showGalleryPreview(catName) {
    console.log('cat name: ' + catName);
    // here we still didn't send any request
    fetch("/api/" + catName + ".json")       // request to server
        .then(response => response.json())  // parse from string to JSON object
        .then(json => {
            // nowhere else are we allowed to initialize variables
            let gallery = [];
            for (i = 0; i < json.gallery.length; i++) {
                gallery.push(`<img height="100" src="${json.gallery[i]}" />`);
            }
            console.log(gallery);
            // we joined images
            document.getElementById("gallery").innerHTML =
            `Name: ${json.name}, Age: ${json.age}<br /> ${gallery.join("")}`;
    })
    .catch(err => console.error(err));
}

