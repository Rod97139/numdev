import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { expect } from '@jest/globals';
import { of, throwError } from 'rxjs';

import { RegisterComponent } from './register.component';
import { AuthService } from '../../services/auth.service';
import {Router} from "@angular/router";

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let authService: AuthService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      providers: [
        { provide: AuthService, useValue: { register: jest.fn() } }
      ],
      imports: [
        RouterTestingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        MatCardModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set onError to true if registration fails', () => {
    const registerRequest = {
      email: 'test@example.com',
      firstName: 'John',
      lastName: 'Doe',
      password: 'password'
    };
    component.form.setValue(registerRequest);
    jest.spyOn(authService, 'register').mockReturnValue(throwError(() => new Error('Registration failed')));

    component.submit();

    expect(component.onError).toBe(true);
  });
});
