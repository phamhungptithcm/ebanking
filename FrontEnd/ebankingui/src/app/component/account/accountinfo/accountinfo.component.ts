import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from 'src/app/services/account-service.service';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';
import { JsonMessageResponseDTO } from 'src/app/dtos/JsonMessageResponseDTO';
import { AccountResponseDTO } from 'src/app/dtos/AccountResponseDTO';

@Component({
  selector: 'app-accountinfo',
  templateUrl: './accountinfo.component.html',
  styleUrls: ['./accountinfo.component.css']
})
export class AccountinfoComponent implements OnInit {

  constructor(
    private router: Router,
    private accountService: AccountService
  ) { }
    curUser: LoginRequestDTO;
    jsonMessageResponseDTO: JsonMessageResponseDTO;
    accountResponseDTO: AccountResponseDTO;
  ngOnInit() {
    this.curUser = JSON.parse(sessionStorage.getItem('user'));
    this.getAccountInfo(this.curUser.username);
  }
  async getAccountInfo(username: string) {
    const request  = new  LoginRequestDTO(username,'');
    await this.accountService.getAccountInfo(request).then(
      data => {
        this.jsonMessageResponseDTO = data
        if (data !== null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
             this.accountResponseDTO = data.jsonResponse;
            }
        }
      });
  }
  backHome() {
    this.router.navigate(['home']);
  }
}
