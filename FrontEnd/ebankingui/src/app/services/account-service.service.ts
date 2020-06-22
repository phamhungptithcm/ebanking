import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { Router } from '@angular/router';
import { tap, catchError, window } from 'rxjs/operators';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { WebServicesURL } from '../component/shared/WebServicesURL';
import { ForgotPasswordRequestDTO } from '../dtos/ForgotPasswordRequestDTO';
import { LoginRequestDTO } from '../dtos/LoginRequestDTO';
import { JsonMessageResponseDTO } from '../dtos/JsonMessageResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class AccountService extends WebServicesURL{

  public isAuthenticated = new BehaviorSubject<boolean>(false);

  constructor(private router: Router, private http: HttpClient) { 
    super();
  }
  
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa('ebanking:1')
    })
  };  
  private httpOptionsUser = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' })
  };

  forgot(request: ForgotPasswordRequestDTO): Observable<any>{
    const url =`${this.DOMAIN}/${this.ACCOUNT_SERVICE_CONTEXT_PATH}/${this.FORGOT_PASSWORD_ACTION_PATH}`;
    return this.http.post(url, JSON.stringify(request), this.httpOptionsUser).pipe(
      tap(),
      catchError(error => of(new ForgotPasswordRequestDTO()))
    );
  }
  saveToken(tokenName: string, tokenValue: string) {
    localStorage.setItem(tokenName, tokenValue);
  }
  getToken(tokenName: string) {
    return localStorage.getItem(tokenName);
  }
  removeToken(){
    localStorage.removeItem('access_token');
    localStorage.removeItem('expires_in');
    localStorage.removeItem('refresh_token');
    sessionStorage.removeItem('user');
  }
  isLogged() {
    return this.getToken('access_token') !== null;
  }
  loginBody(request: LoginRequestDTO): Observable<any> {
    let body = new URLSearchParams;
    body.set('username', request.username);
    body.set('password', request.password);
    body.set('grant_type', 'password');
    return this.http.post(this.OAUTH_TOKEN_URL, body.toString(), this.httpOptions);
  }
  loginUser(request: LoginRequestDTO): Observable<any> {
    const url = `${this.DOMAIN}/${this.ACCOUNT_SERVICE_CONTEXT_PATH}/${this.LOGIN_ACTION_PATH}`;
    return this.http.post(url, JSON.stringify(request), this.httpOptionsUser).pipe(
      tap(),
      catchError(error => of(null))
    );
  }
  logout() {
    localStorage.removeItem('access_token');
    localStorage.removeItem('expires_in');
    localStorage.removeItem('refresh_token');
    sessionStorage.removeItem('user');
    sessionStorage.clear();
    this.router.navigate(['/login']);
    location.reload();
  }
  async getAccountInfo(request: LoginRequestDTO): Promise<any>  {
    const url = `${this.DOMAIN}/${this.ACCOUNT_SERVICE_CONTEXT_PATH}/${this.GET_ACCOUNT_INFO_ACTION_PATH}?${this.TOKEN_PARAM}`;
    return await this.http.post<JsonMessageResponseDTO>(url, request, this.httpOptionsUser).toPromise();
  }
  
}
