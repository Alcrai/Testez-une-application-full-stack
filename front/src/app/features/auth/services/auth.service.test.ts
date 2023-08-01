import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthService } from './auth.service';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { SessionInformation } from 'src/app/interfaces/sessionInformation.interface';
import { Observable } from 'rxjs';
import { expect } from '@jest/globals';

interface SessionInformationResponse {
  token: string;
  id: number;
  type: string;
  username: string;
  firstName: string;
  lastName: string;
  admin: boolean;
}

describe('AuthService', () => {
  let authService: AuthService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthService]
    });

    authService = TestBed.inject(AuthService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(authService).toBeTruthy();
  });

  it('should make a POST request to register', () => {
    const registerRequest: RegisterRequest = { email: 'test@example.com', firstName: 'John', lastName: 'Doe', password: 'password123' };

    authService.register(registerRequest).subscribe();

    const req = httpTestingController.expectOne('api/auth/register');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(registerRequest);

    req.flush(null);
  });

  it('should make a POST request to login', () => {
    const loginRequest: LoginRequest = { email: 'test@example.com', password: 'password123' };
    const sessionInformation: SessionInformationResponse = { token: 'abc123', id: 1, type: '', username: '', firstName: '', lastName: '', admin: false };

    authService.login(loginRequest).subscribe((response: SessionInformation) => {
      expect(response).toEqual(sessionInformation);
    });

    const req = httpTestingController.expectOne('api/auth/login');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(loginRequest);

    req.flush(sessionInformation);
  });
});
