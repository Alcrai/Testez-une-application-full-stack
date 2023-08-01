import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Session } from '../interfaces/session.interface';
import { SessionApiService } from './session-api.service';
import { expect } from '@jest/globals';

describe('SessionApiService', () => {
  let service: SessionApiService;
  let httpClient: HttpClient;

  const mockHttpClient = {
    get: jest.fn(),
    post: jest.fn(),
    put: jest.fn(),
    delete: jest.fn()
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [
        SessionApiService,
        { provide: HttpClient, useValue: mockHttpClient }
      ]
    });
    service = TestBed.inject(SessionApiService);
    httpClient = TestBed.inject(HttpClient);
  });

  afterEach(() => {
    jest.clearAllMocks();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call HttpClient get method for all sessions', () => {
    const mockSessions: Session[] = [ { id: 1, name: 'Session 1', description: 'Description 1', date: new Date(), teacher_id: 1, users: [] },
    { id: 2, name: 'Session 2', description: 'Description 2', date: new Date(), teacher_id: 2, users: [] }];
    mockHttpClient.get.mockReturnValue(of(mockSessions));

    service.all().subscribe((sessions: Session[]) => {
      expect(sessions).toEqual(mockSessions);
    });

    expect(httpClient.get).toHaveBeenCalledWith('api/session');
  });

  it('should call HttpClient get method for session detail', () => {
    const sessionId = '1';
    const mockSession: Session =  { id: 1, name: 'Session 1', description: 'Description 1', date: new Date(), teacher_id: 1, users: [] };
    mockHttpClient.get.mockReturnValue(of(mockSession));

    service.detail(sessionId).subscribe((session: Session) => {
      expect(session).toEqual(mockSession);
    });

    expect(httpClient.get).toHaveBeenCalledWith('api/session/1');
  });

  it('should call HttpClient delete method for session deletion', () => {
    const sessionId = '1';
    mockHttpClient.delete.mockReturnValue(of({}));

    service.delete(sessionId).subscribe(() => {
      expect(httpClient.delete).toHaveBeenCalledWith('api/session/1');
    });
  });

  it('should call HttpClient post method for session creation', () => {
    const session: Session = { id: 1, name: 'Session 1', description: 'Description 1', date: new Date(), teacher_id: 1, users: [] };
    mockHttpClient.post.mockReturnValue(of(session));

    service.create(session).subscribe((createdSession: Session) => {
      expect(createdSession).toEqual(session);
    });

    expect(httpClient.post).toHaveBeenCalledWith('api/session', session);
  });

  it('should call HttpClient put method for session update', () => {
    const sessionId = '1';
    const session: Session = { id: 1, name: 'Session 1', description: 'Description 1', date: new Date(), teacher_id: 1, users: [] };
    mockHttpClient.put.mockReturnValue(of(session));

    service.update(sessionId, session).subscribe((updatedSession: Session) => {
      expect(updatedSession).toEqual(session);
    });

    expect(httpClient.put).toHaveBeenCalledWith('api/session/1', session);
  });

  it('should call HttpClient post method for user participation', () => {
    const sessionId = '1';
    const userId = '1';
    mockHttpClient.post.mockReturnValue(of({}));

    service.participate(sessionId, userId).subscribe(() => {
      expect(httpClient.post).toHaveBeenCalledWith('api/session/1/participate/1', null);
    });
  });

  it('should call HttpClient delete method for user unparticipation', () => {
    const sessionId = '1';
    const userId = '1';
    mockHttpClient.delete.mockReturnValue(of({}));

    service.unParticipate(sessionId, userId).subscribe(() => {
      expect(httpClient.delete).toHaveBeenCalledWith('api/session/1/participate/1');
    });
  });
});

