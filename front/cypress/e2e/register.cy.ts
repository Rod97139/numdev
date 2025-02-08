/// <reference types="cypress" />

describe('Register spec', () => {
  it('should register and delete a new user', () => {
    cy.visit('/register')

    cy.get('input[formControlName=firstName]').type("test")
    cy.get('input[formControlName=lastName]').type("test")
    cy.get('input[formControlName=email]').type("test@test.com")
    cy.get('input[formControlName=password]').type(`${"test!1234"}{enter}{enter}`)

    cy.wait(500)
    cy.request({
      method: 'POST',
      url: 'localhost:8080/api/auth/login', // Adjust the URL to match your login API
      body: {
        email: 'test@test.com',
        password: 'test!1234'
      }
    }).then((response) => {
      const token = response.body.token;
      const id = response.body.id;
      window.localStorage.setItem('id', id); // Adjust the key if needed
      window.localStorage.setItem('token', token); // Adjust the key if needed
    });

    cy.wait(2000)

    // cy.intercept('POST', '/api/auth/login', {
    //   body: {
    //   "token":window.localStorage.getItem('token'),
    //   "type":"Bearer",
    //   "id": Number(window.localStorage.getItem('id')),
    //   "username":"test@test.com",
    //   "firstName":"test2",
    //   "lastName":"test2",
    //   "admin":false
    //   }
    // })


    cy.wait(500)
    cy.get('input[formControlName=email]').type("test@test.com")
    cy.get('input[formControlName=password]').type(`${"test!1234"}{enter}{enter}`)
    cy.wait(500)

    cy.get('span[routerlink="me"]').click();


    cy.contains('mat-icon', 'delete').first().click();
    cy.contains('span', 'Your account has been deleted !').should('be.visible')
  });
});
