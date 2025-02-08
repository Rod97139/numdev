import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { expect } from '@jest/globals';
import { SessionService } from 'src/app/services/session.service';
import { SessionApiService } from '../../services/session-api.service';
import { TeacherService } from '../../../../services/teacher.service';
import { FormComponent } from './form.component';
import { ActivatedRoute, Router } from '@angular/router';

describe('FormComponent', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;
  let sessionApiService: SessionApiService;
  let router: Router;

  const mockSessionService = {
    sessionInformation: {
      admin: true
    }
  };

  const mockTeacherService = {
    all: jest.fn().mockReturnValue(of([]))
  };

  const mockSessionApiService = {
    detail: jest.fn().mockReturnValue(of({})),
    create: jest.fn().mockReturnValue(of({})),
    update: jest.fn().mockReturnValue(of({}))
  };

  const mockActivatedRoute = {
    snapshot: {
      paramMap: {
        get: jest.fn().mockReturnValue('1')
      }
    }
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatCardModule,
        MatIconModule,
        MatFormFieldModule,
        MatInputModule,
        ReactiveFormsModule,
        MatSnackBarModule,
        MatSelectModule,
        BrowserAnimationsModule
      ],
      providers: [
        { provide: SessionService, useValue: mockSessionService },
        { provide: TeacherService, useValue: mockTeacherService },
        { provide: SessionApiService, useValue: mockSessionApiService },
        { provide: ActivatedRoute, useValue: mockActivatedRoute }
      ],
      declarations: [FormComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    sessionApiService = TestBed.inject(SessionApiService);
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form on create', () => {
    component.onUpdate = false;
    component.ngOnInit();
    expect(component.sessionForm).toBeDefined();
    expect(component.sessionForm?.controls['name']).toBeDefined();
  });

  it('should initialize form on update', () => {
    component.onUpdate = true;
    component.ngOnInit();
    expect(sessionApiService.detail).toHaveBeenCalled;
  });
});
