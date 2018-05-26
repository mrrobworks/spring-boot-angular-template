import { TestBed, inject } from '@angular/core/testing';
import { AuthorizationService } from './authorization.service';

describe('AuthorizationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthorizationService]
    });
  });

  it(
    'should be created',
    inject([AuthorizationService], (service: AuthorizationService) => {
      expect(service).toBeTruthy();
    })
  );
});
