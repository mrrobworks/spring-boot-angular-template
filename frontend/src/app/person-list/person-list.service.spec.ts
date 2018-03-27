import { TestBed, inject } from '@angular/core/testing';

import { PersonListService } from './person-list.service';

describe('PersonListService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PersonListService]
    });
  });

  it('should be created', inject([PersonListService], (service: PersonListService) => {
    expect(service).toBeTruthy();
  }));
});
