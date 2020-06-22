import { TestBed, async, inject } from '@angular/core/testing';

import { ConfirmationrequestGuard } from './confirmationrequest.guard';

describe('ConfirmationrequestGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConfirmationrequestGuard]
    });
  });

  it('should ...', inject([ConfirmationrequestGuard], (guard: ConfirmationrequestGuard) => {
    expect(guard).toBeTruthy();
  }));
});
