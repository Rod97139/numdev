import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { SessionApiService } from './session-api.service';
import { Session } from '../interfaces/session.interface';
import { expect } from '@jest/globals';

describe('SessionApiService Integration Tests', () => {
  let service: SessionApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SessionApiService]
    });
    service = TestBed.inject(SessionApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve all sessions', () => {
    const dummySessions: Session[] = [
      { id: 1, name: 'Session 1', description: 'Description 1', date: new Date(), teacher_id: 1, users: [] },
      { id: 2, name: 'Session 2', description: 'Description 2', date: new Date(), teacher_id: 2, users: [] }
    ];

    service.all().subscribe(sessions => {
      expect(sessions.length).toBe(2);
      expect(sessions).toEqual(dummySessions);
    });

    const req = httpMock.expectOne('api/session');
    expect(req.request.method).toBe('GET');
    req.flush(dummySessions);
  });

  it('should retrieve session details', () => {
    const dummySession: Session = { id: 1, name: 'Session 1', description: 'Description 1', date: new Date(), teacher_id: 1, users: [] };

    service.detail('1').subscribe(session => {
      expect(session).toEqual(dummySession);
    });

    const req = httpMock.expectOne('api/session/1');
    expect(req.request.method).toBe('GET');
    req.flush(dummySession);
  });

  it('should create a new session', () => {
    const newSession: Session = { name: 'New Session', description: 'New Description', date: new Date(), teacher_id: 1, users: [] };

    service.create(newSession).subscribe(session => {
      expect(session).toEqual({ ...newSession, id: 1 });
    });

    const req = httpMock.expectOne('api/session');
    expect(req.request.method).toBe('POST');
    req.flush({ ...newSession, id: 1 });
  });

  it('should update an existing session', () => {
    const updatedSession: Session = { id: 1, name: 'Updated Session', description: 'Updated Description', date: new Date(), teacher_id: 1, users: [] };

    service.update('1', updatedSession).subscribe(session => {
      expect(session).toEqual(updatedSession);
    });

    const req = httpMock.expectOne('api/session/1');
    expect(req.request.method).toBe('PUT');
    req.flush(updatedSession);
  });

  it('should delete a session', () => {
    service.delete('1').subscribe(response => {
      expect(response).toBeNull();
    });

    const req = httpMock.expectOne('api/session/1');
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });

  it('should participate in a session', () => {
    service.participate('1', '1').subscribe(response => {
      expect(response).toBeUndefined();
    });

    const req = httpMock.expectOne('api/session/1/participate/1');
    expect(req.request.method).toBe('POST');
    req.flush(null);
  });

  it('should unParticipate from a session', () => {
    service.unParticipate('1', '1').subscribe(response => {
      expect(response).toBeUndefined();
    });

    const req = httpMock.expectOne('api/session/1/participate/1');
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
