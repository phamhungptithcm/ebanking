import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginRequestDTO } from '../dtos/LoginRequestDTO';

@Injectable({
  providedIn: 'root'
})
export class BasicrequestGuard implements CanActivate {
  constructor(private router: Router){}
  user: LoginRequestDTO = null;
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      this.user = JSON.parse(sessionStorage.getItem('user'));
      if (this.user == null || this.user != undefined) {
        // this.router.navigate['/login'];
        return true;
      }
      return true;
    }
}
