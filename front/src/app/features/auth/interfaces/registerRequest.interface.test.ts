import { RegisterRequest } from './registerRequest.interface';
import { expect } from '@jest/globals';

describe('RegisterRequest', () => {
  it('should create an instance', () => {
    const registerRequest: RegisterRequest = {
      email: 'test@example.com',
      firstName: 'John',
      lastName: 'Doe',
      password: 'password123'
    };

    expect(registerRequest).toBeTruthy();
    expect(registerRequest.email).toBe('test@example.com');
    expect(registerRequest.firstName).toBe('John');
    expect(registerRequest.lastName).toBe('Doe');
    expect(registerRequest.password).toBe('password123');
  });
});
