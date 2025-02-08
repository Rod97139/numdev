import { TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';
import { SessionInformation } from '../interfaces/sessionInformation.interface';

import { SessionService } from './session.service';

describe('SessionService', () => {
  let service: SessionService;
  const user: SessionInformation = {
    token: 'sampleToken',
    type: 'Bearer',
    id: 1,
    username: 'testUser',
    firstName: 'Test',
    lastName: 'User',
    admin: false
  };


  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return the current login status as an observable', (done) => {
    service.$isLogged().subscribe((isLogged) => {
      expect(isLogged).toBe(false);
      done();
    });
  });

  it('should update the login status when logIn is called', (done) => {
    service.logIn(user);
    service.$isLogged().subscribe((isLogged) => {
      expect(isLogged).toBe(true);
      done();
    });
  });

  it('should update the login status when logOut is called', (done) => {
    service.logOut();
    service.$isLogged().subscribe((isLogged) => {
      expect(isLogged).toBe(false);
      done();
    });
  });
});
