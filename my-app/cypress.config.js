const { defineConfig } = require("cypress");

module.exports = defineConfig({
  projectId: "jgntho",
  e2e: {
    baseUrl: 'http://localhost:3000/',
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});