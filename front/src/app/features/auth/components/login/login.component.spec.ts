import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, throwError } from 'rxjs';
import { expect } from '@jest/globals';

import { LoginComponent } from './login.component';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { LoginRequest } from '../../interfaces/loginRequest.interface';
import { AuthService } from '../../services/auth.service';
import { SessionService } from 'src/app/services/session.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authServiceMock: Partial<AuthService>;
  let routerMock: Partial<Router>;
  let sessionServiceMock: Partial<SessionService>;

  beforeEach(async () => {
    authServiceMock = {
      login: jest.fn().mockReturnValue(of({} as SessionInformation))
    };
    routerMock = {
      navigate: jest.fn()
    };
    sessionServiceMock = {
      logIn: jest.fn()
    };

    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      providers: [
        { provide: AuthService, useValue: authServiceMock },
        { provide: Router, useValue: routerMock },
        { provide: SessionService, useValue: sessionServiceMock }
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

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form with empty email and password fields', () => {
    const form = component.form;
    const emailControl = form.get('email');
    const passwordControl = form.get('password');

    expect(form.value).toEqual({ email: '', password: '' });
    expect(emailControl?.value).toBe('');
    expect(passwordControl?.value).toBe('');
  });

  it('should have required validation on email field', () => {
    const emailControl = component.form.get('email');

    emailControl?.setValue('');
    expect(emailControl?.hasError('required')).toBe(true);
  

    emailControl?.setValue('test');
    expect(emailControl?.hasError('required')).toBe(false);
  });

  it('should have email validation on email field', () => {
    const emailControl = component.form.get('email');

    emailControl?.setValue('invalidemail');
    expect(emailControl?.hasError('email')).toBe(true);

    emailControl?.setValue('test@example.com');
    expect(emailControl?.hasError('email')).toBe(false);
  });

  it('should have required validation on password field', () => {
    const passwordControl = component.form.get('password');

    passwordControl?.setValue('');
    expect(passwordControl?.hasError('required')).toBe(true);

    passwordControl?.setValue('test');
    expect(passwordControl?.hasError('required')).toBe(false);
  });

  it('should call authService login method and navigate to "/sessions" on successful login', () => {
    const loginRequest: LoginRequest = {
      email: 'test@example.com',
      password: 'password'
    };

    component.form.setValue(loginRequest);
    component.submit();

    expect(authServiceMock.login).toHaveBeenCalledWith(loginRequest);
    expect(sessionServiceMock.logIn).toHaveBeenCalled();
    expect(routerMock.navigate).toHaveBeenCalledWith(['/sessions']);
    expect(component.onError).toBe(false);
  });

  it('should set onError to true on login error', () => {
    authServiceMock.login = jest.fn().mockReturnValue(throwError('error'));

    component.submit();

    expect(component.onError).toBe(true);
  });
});
