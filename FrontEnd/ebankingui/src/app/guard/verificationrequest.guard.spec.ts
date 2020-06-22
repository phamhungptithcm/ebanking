import { TestBed, async, inject } from '@angular/core/testing';

import { VerificationrequestGuard } from './verificationrequest.guard';

describe('VerificationrequestGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VerificationrequestGuard]
    });
  });

  it('should ...', inject([VerificationrequestGuard], (guard: VerificationrequestGuard) => {
    expect(guard).toBeTruthy();
  }));
});
