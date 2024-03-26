import { TestBed } from '@angular/core/testing';

import { TimeSocketService } from './time-socket.service';

describe('TimeServiceService', () => {
  let service: TimeSocketService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TimeSocketService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
