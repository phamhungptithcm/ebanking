import { TestBed, async, inject } from '@angular/core/testing';

import { BasicrequestGuard } from './basicrequest.guard';

describe('BasicrequestGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BasicrequestGuard]
    });
  });

  it('should ...', inject([BasicrequestGuard], (guard: BasicrequestGuard) => {
    expect(guard).toBeTruthy();
  }));
});
