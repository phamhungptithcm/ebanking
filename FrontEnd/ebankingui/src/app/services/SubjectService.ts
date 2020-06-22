import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  constructor() { }
  isLogin =new Subject<boolean>();
  isSearch =new Subject<boolean>();
  isCancel =new Subject<boolean>();
  isChoose = new Subject<boolean>();
  isSignup = new Subject<boolean>();
  logged()
  {
    this.isLogin.next(true);
  }
  searched() {
    this.isSearch.next(true);
  }
  canceled() {
    this.isCancel.next(true);
  }
  signed(){
    this.isSignup.next(true);
  }
  choose() {
    this.isChoose.next(true);
  }
  getStatusTicketDetail() {
    return this.isCancel.asObservable();
  }
  getDeparture() {
    return this.isSearch.asObservable();
  }
  getObservable()
  {
    return this.isLogin.asObservable();
  }
  getChooseSeat(){
    return this.isChoose.asObservable();
  }
  getRegister() {
    return this.isSignup.asObservable();
  }
}