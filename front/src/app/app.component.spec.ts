import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';
import { expect } from '@jest/globals';
import { SessionService } from './services/session.service';
import { AuthService } from './features/auth/services/auth.service';
import { of } from 'rxjs';

import { AppComponent } from './app.component';
import {Router} from "@angular/router";


describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatToolbarModule
      ],
      declarations: [
        AppComponent
      ],
    providers: [
      AuthService,
      SessionService
    ]
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('should return the logged-in status', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    const sessionService = TestBed.inject(SessionService);
    jest.spyOn(sessionService, '$isLogged').mockReturnValue(of(true));
    app.$isLogged().subscribe(status => {
      expect(status).toBe(true);
    });
  });

  it('should log out and navigate to home', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    const sessionService = TestBed.inject(SessionService);
    const router = TestBed.inject(Router);
    const navigateSpy = jest.spyOn(router, 'navigate');
    jest.spyOn(sessionService, 'logOut');
    app.logout();
    expect(sessionService.logOut).toHaveBeenCalled();
    expect(navigateSpy).toHaveBeenCalledWith(['']);
  });
});
