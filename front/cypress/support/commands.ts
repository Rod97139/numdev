// ***********************************************
// This example namespace declaration will help
// with Intellisense and code completion in your
// IDE or Text Editor.
// ***********************************************
// declare namespace Cypress {
//   interface Chainable<Subject = any> {
//     customCommand(param: any): typeof customCommand;
//   }
// }
//
// function customCommand(param: any): void {
//   console.warn(param);
// }
//
// NOTE: You can use it like so:
// Cypress.Commands.add('customCommand', customCommand);
//
// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })
Cypress.Commands.add('loginWithToken', () => {

  let token = null;
  cy.request({
    method: 'POST',
    url: 'localhost:8080/api/auth/login', // Adjust the URL to match your login API
    body: {
      email: 'yoga@studio.com',
      password: 'test!1234'
    }
  }).then((response) => {
    token = response.body.token;
    const id = response.body.id;
    window.localStorage.setItem('id', id); // Adjust the key if needed
    window.localStorage.setItem('token', token); // Adjust the key if needed
  });
});

Cypress.Commands.add('register', () => {
  cy.request({
    method: 'POST',
    url: 'localhost:8080/api/auth/login', // Adjust the URL to match your login API
    body: {
      email: 'yoga@studio.com',
      password: 'test!1234'
    }
  }).then((response) => {
    const token = response.body.token;
    const id = response.body.id;
    window.localStorage.setItem('id', id); // Adjust the key if needed
    window.localStorage.setItem('token', token); // Adjust the key if needed
  });
});

Cypress.Commands.add('idSession', () => {

  cy.request({
    method: 'GET',
    url: 'localhost:8080/api/session',
    headers: {
      Authorization: 'Bearer ' + window.localStorage.getItem('token'),
    }
  }).then((response) => {
    const maxId = response.body.reduce((max, session) => {
      return session.id > max ? session.id : max;
    }, 0);
    window.localStorage.setItem('idSession', maxId === 0 ? 1 : maxId);
  });
});
