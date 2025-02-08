/// <reference types="cypress" />

describe('Login spec', () => {

  beforeEach(() => {
    cy.loginWithToken(); // Custom command to log in via token
  });
  it('Login successfull', () => {
    cy.visit('/login')

    cy.intercept('POST', '/api/auth/login', {
      body: {
        "token": window.localStorage.getItem('token'),
        "type":"Bearer",
        "id":1,"username":"yoga@studio.com",
        "firstName":"Admin",
        "lastName":"Admin",
        "admin":true
      },
    })

    cy.intercept('GET', '/api/session/1', {
      body:
    {"id":1,"name":"test","date":"2024-12-18T00:00:00.000+00:00","teacher_id":1,"description":"test","users":[],"createdAt":"2025-02-08T11:10:28","updatedAt":"2025-02-08T14:52:54"}


    })

    cy.intercept('GET', '/api/teacher', {
      body:
        [{"id":1,"lastName":"DELAHAYE","firstName":"Margot","createdAt":"2025-02-06T00:14:51","updatedAt":"2025-02-06T00:14:51"},{"id":2,"lastName":"THIERCELIN","firstName":"Hélène","createdAt":"2025-02-06T00:14:51","updatedAt":"2025-02-06T00:14:51"}]
    })

    cy.intercept(
      {
        method: 'GET',
        url: '/api/session',
      },
      [{
        "id": 1,
        "name": "test",
        "date": "2024-12-18T00:00:00.000+00:00",
        "teacher_id": 1,
        "description": "test",
        "users": [],
        "createdAt": "2025-02-08T11:10:28",
        "updatedAt": "2025-02-08T11:10:28"
      }]).as('session')



    cy.get('input[formControlName=email]').type("yoga@studio.com")
    cy.get('input[formControlName=password]').type(`${"test!1234"}{enter}{enter}`)

    cy.get('span[routerlink="me"]').click();
    cy.contains('mat-icon', 'arrow_back').first().click();
    cy.get('mat-card-header > button[tabindex="0"]').click();
    cy.get('input[formControlName="name"]').type("test");
    cy.get('input[formcontrolname="date"]').type("2025-01-01");
    cy.get('textarea[formcontrolname="description"]').type("test");
    cy.get('mat-select[formcontrolname="teacher_id"]').click();
    cy.get('mat-option').first().click();
    cy.get('button[type="submit"]').click();
    cy.get('span[routerlink="sessions"]').click();
    cy.contains('span', 'Detail').first().click();
    cy.contains('mat-icon', 'arrow_back').first().click();


    cy.contains('span', 'Edit').first().click();
    cy.get('button[type="submit"]').click();
    cy.get('span[routerlink="sessions"]').click();
    cy.contains('span', 'Detail').first().click();
    cy.contains('span', 'Delete').click();

    cy.get('span[routerlink="me"] + span').click();



  })
});
