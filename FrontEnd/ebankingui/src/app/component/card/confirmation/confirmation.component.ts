import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TransferRequestDTO } from 'src/app/dtos/TransferRequestDTO';
import { CardService } from 'src/app/services/card-service.service';
import { OTPRequestDTO } from 'src/app/dtos/OTPRequestDTO';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';
import { AccountService } from 'src/app/services/account-service.service';
import { AccountResponseDTO } from 'src/app/dtos/AccountResponseDTO';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {

  constructor(private router: Router, private cardService: CardService
    ,private accountService: AccountService) { }

  transferDTO: TransferRequestDTO;
  verifyCode: string;
  curUser: LoginRequestDTO;
  accountResponseDTO: AccountResponseDTO;
  ngOnInit() {
    this.curUser = JSON.parse(sessionStorage.getItem('user'));
    this.transferDTO = JSON.parse(sessionStorage.getItem('transferDTO'));
  }
  async onClick(action) {
    if(action === 'verification') {
      const username = this.curUser.username;
      const requestEmail  = new  LoginRequestDTO(username,'');
      await  await this.accountService.getAccountInfo(requestEmail).then(
        data => {
          if (data !== null) {
              if (data.jsonResponse != '' && data.jsonResponse != undefined) {
              this.accountResponseDTO = data.jsonResponse;
              }
          }
        });
      await this.delay(1000);
      const request = new OTPRequestDTO('','hungpham12344321@gmail.com','');
      await this.cardService.sendOTPViaMail(request).then(
          data => {
            if (data != null) {
                if (data.jsonResponse != '' && data.jsonResponse != undefined) {
                  this.verifyCode = data.jsonResponse;
                  alert('Send mail successfully')
                }
            }
          });
      await this.delay(300);
    }
    this.router.navigate([action]);
  }
  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }
}
