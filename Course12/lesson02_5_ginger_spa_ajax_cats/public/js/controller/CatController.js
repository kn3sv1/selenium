/**
 * @typedef {Object} Cat
 * @property {string} name
 * @property {number} age
 * @property {string} photo
 */

export const CatController = {
  init(selector) {
    this.selector = selector;

    return this;
  },

  /**
   * Fetch cats from API.
   * @returns {Promise<void>}
   */
  async showCats() {
    const app = document.getElementById(this.selector);
    //app.innerHTML = "<h1>Cats Controller</h1>";

    // fetch data from server
    const response = await fetch("/api/cats");

    /**
     * @type {Cat[]}
     */
    const cats = await response.json();
    // console.log(cats);

    const container = document.getElementById(this.selector);

    // render cats
    container.innerHTML =
      cats
        .map(
          (cat) => `
        <div style="float:left" class="cat">
            <h2>${cat.name}</h2>
            <p>Age: ${cat.age}</p>
            <img height="100" src="${cat.photo}" alt="${cat.name}" />
        </div>
    `,
        )
        .join("") + '<div style="clear:both"></div>';
  },
};
