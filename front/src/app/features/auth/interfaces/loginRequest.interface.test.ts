import { LoginRequest } from './loginRequest.interface';
import { expect } from '@jest/globals';

describe('LoginRequest', () => {
  it('should create an instance', () => {
    const loginRequest: LoginRequest = {
      email: 'test@example.com',
      password: 'password123'
    };

    expect(loginRequest).toBeTruthy();
    expect(loginRequest.email).toBe('test@example.com');
    expect(loginRequest.password).toBe('password123');
  });
});
