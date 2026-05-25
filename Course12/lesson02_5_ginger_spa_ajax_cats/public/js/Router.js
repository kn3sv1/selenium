
import { CatController } from "./controller/CatController.js";

export const Router = {
  init(selector) {
    this.selector = selector;
    this.setListener();

    return this;
  },

  setListener() {
    document.addEventListener("click", e => {

      if (e.target.matches("[data-link]")) {

        e.preventDefault();

        this.navigate(e.target.href.replace(location.origin, ""));
      }
    });

    window.addEventListener("popstate", () => {
      this.render(location.pathname);
    });
  },
  
  navigate(url) {
    // change browser URL
    history.pushState({}, "", url);

    // render correct page
    this.render(url);
  },

  render(path) {
    const app = document.getElementById(this.selector);
    switch(path) {
      case "/":
        app.innerHTML = "<h1>Home Page</h1>";
        break;

      case "/about":
        app.innerHTML = "<h1>About Page</h1>";
        break;

      case "/products":
        app.innerHTML = "<h1>Products Page</h1>";
        break;

      case "/cats":
        const controller = CatController.init(this.selector);
        controller.showCats();
        break;

      default:
        app.innerHTML = "<h1>404 Not Found</h1>";
    }
  },
};