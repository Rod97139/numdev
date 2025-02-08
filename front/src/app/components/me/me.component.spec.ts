import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { expect } from '@jest/globals';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { SessionService } from 'src/app/services/session.service';

import { MeComponent } from './me.component';
import {UserService} from "../../services/user.service";
import {User} from "../../interfaces/user.interface";
import {of} from "rxjs";
import {Router} from "@angular/router";

describe('MeComponent', () => {
  let component: MeComponent;
  let fixture: ComponentFixture<MeComponent>;
  let service: SessionService;

  const mockSessionService = {
    sessionInformation: {
      admin: true,
      id: 1
    }
  }
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MeComponent],
      imports: [
        MatSnackBarModule,
        HttpClientModule,
        MatCardModule,
        MatFormFieldModule,
        MatIconModule,
        MatInputModule
      ],
      providers: [{ provide: SessionService, useValue: mockSessionService }],
    })
      .compileComponents();

    fixture = TestBed.createComponent(MeComponent);
    service = TestBed.inject(SessionService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch user data on init', () => {
    const userService = TestBed.inject(UserService);
    const user: User = {
      id: 1,
      firstName: 'Test',
      lastName: 'User',
      email: '',
      admin: false,
      password: '',
      createdAt: new Date(),
      updatedAt: undefined
    };

    jest.spyOn(userService, 'getById').mockReturnValue(of(user));
    component.ngOnInit();
    expect(component.user).toEqual(user);
  });

  it('should navigate back when back is called', () => {
    const windowSpy = jest.spyOn(window.history, 'back');
    component.back();
    expect(windowSpy).toHaveBeenCalled();
  });

  it('should delete user and navigate to home on delete', () => {
    const userService = TestBed.inject(UserService);
    const router = TestBed.inject(Router);
    const matSnackBar = TestBed.inject(MatSnackBar);
    const deleteSpy = jest.spyOn(userService, 'delete').mockReturnValue(of({}));
    // const logOutSpy = jest.spyOn(sessionService, 'logOut');
    const navigateSpy = jest.spyOn(router, 'navigate');
    const snackBarSpy = jest.spyOn(matSnackBar, 'open');

    component.delete();

    expect(deleteSpy).toHaveBeenCalledWith('1');
    expect(snackBarSpy).toHaveBeenCalledWith('Your account has been deleted !', 'Close', { duration: 3000 });
  });

});
