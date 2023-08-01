import { Session } from './session.interface';
import { expect } from '@jest/globals';

describe('Session', () => {
  it('should create an instance', () => {
    const session: Session = {
      name: 'Session 1',
      description: 'This is session 1',
      date: new Date(),
      teacher_id: 1,
      users: [1, 2, 3]
    };

    expect(session).toBeTruthy();
    expect(session.name).toBe('Session 1');
    expect(session.description).toBe('This is session 1');
    expect(session.date instanceof Date).toBe(true);
    expect(session.teacher_id).toBe(1);
    expect(Array.isArray(session.users)).toBe(true);
    expect(session.users).toEqual([1, 2, 3]);
  });
});
