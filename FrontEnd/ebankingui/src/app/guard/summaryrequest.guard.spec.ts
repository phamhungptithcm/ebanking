import { TestBed, async, inject } from '@angular/core/testing';

import { SummaryrequestGuard } from './summaryrequest.guard';

describe('SummaryrequestGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SummaryrequestGuard]
    });
  });

  it('should ...', inject([SummaryrequestGuard], (guard: SummaryrequestGuard) => {
    expect(guard).toBeTruthy();
  }));
});
