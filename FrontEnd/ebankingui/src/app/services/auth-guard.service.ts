import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AccountService } from './account-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(public authService: AccountService, public router: Router) { }
  async canActivate() {
    if (!await this.authService.checkAuthenticated()) {
      await this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
