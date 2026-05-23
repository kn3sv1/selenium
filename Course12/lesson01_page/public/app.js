const API_URL = "/posts";

const postList = document.getElementById("postList");
const addBtn = document.getElementById("addBtn");
const titleInput = document.getElementById("titleInput");

async function loadPosts() {
  const response = await fetch(API_URL);
  const posts = await response.json();

  postList.innerHTML = "";

  posts.forEach(post => {
    const li = document.createElement("li");
    li.textContent = post.title;
    postList.appendChild(li);
  });
}

async function addPost() {
  const title = titleInput.value.trim();

  if (!title) return;

  await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ title })
  });

  titleInput.value = "";

  loadPosts();
}

addBtn.addEventListener("click", addPost);

loadPosts();
