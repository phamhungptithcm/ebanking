import { AccountResponseDTO } from './../dtos/AccountResponseDTO';
import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AccountService } from './account-service.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private router: Router) {
  }
  user: AccountResponseDTO = null;
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      this.user = JSON.parse(sessionStorage.getItem('user'));
    if (this.user === null) {
      // this.router.navigate(['/']);
      return true;
    }
    return true;
  }
}
