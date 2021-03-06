import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CardService } from 'src/app/services/card-service.service';
import { TransferRequestDTO } from 'src/app/dtos/TransferRequestDTO';
import { AccountResponseDTO } from 'src/app/dtos/AccountResponseDTO';
import { AccountService } from 'src/app/services/account-service.service';
import { OTPRequestDTO } from 'src/app/dtos/OTPRequestDTO';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TransactionHistoryResponseDTO } from 'src/app/dtos/TransactionHistoryResponseDTO';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.css']
})
export class VerificationComponent implements OnInit {

  constructor(private router: Router, private cardService: CardService
    ,private accountService: AccountService,
    private fb: FormBuilder) { }
    form: FormGroup;
  transferDTO: TransferRequestDTO;
  verifyCode: string;
  curUser: LoginRequestDTO;
  accountResponseDTO: AccountResponseDTO;
  codeIsValid = true;
  result: TransferRequestDTO;
  action: string;
  title: string;
  ngOnInit() {
    this.action = sessionStorage.getItem('action');
    this.title = this.action != null || this.action != undefined ? this.action : 'Money Transfer In TPBank';
    this.transferDTO = JSON.parse(sessionStorage.getItem('transferDTO'));
    this.form = this.fb.group({
      verifyCode: ['', Validators.required],
    });
  }
  async resendMail() {
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
                  sessionStorage.setItem('verifyCodeRoot',this.verifyCode);
                  alert('Send mail successfully')
                }
            }
          });
      await this.delay(300);
  }
  async onSubmit() {
    if (this.form.valid) {
       const verifyCode = this.form.get('verifyCode').value;
      await this.checkVerificationCode(verifyCode);
      if(this.codeIsValid){
        const root = sessionStorage.getItem('verifyCodeRoot');
        if(root == verifyCode) {
          if(this.action != null || this.action != undefined) {
            await this.updatePassword();
            await this.delay(100);
            sessionStorage.removeItem('action');
            this.router.navigate(['home']);
          } else {
            await this.transferMoney();
            await this.delay(300);
            sessionStorage.removeItem('transferDTO');
            this.router.navigate(['summary']);
          }
        } else {
          this.codeIsValid = false;
        }
       
      }
    }
       
  }
  async checkVerificationCode(verifyCode: string) {
    const request = new OTPRequestDTO('','',verifyCode);
    await this.cardService.checkVerificationCode(request).then(
      data => {
        if (data != null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
              this.codeIsValid = data.jsonResponse;
            }
        }
      });
    await this.delay(300);
  }
  async transferMoney() {
    
    await this.cardService.transferMoney(this.transferDTO).then(
      data => {
        if (data != null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
              this.result = data.jsonResponse;
              sessionStorage.setItem('SummaryDTO',JSON.stringify(this.result));
            }
        }
      });
    await this.delay(300);
  }
  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }
  async updatePassword() {
    const account = JSON.parse(sessionStorage.getItem('user'));
    await this.accountService.updatePassword(account).then(
      data => {
        if (data != null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
              this.result = data.jsonResponse;
              sessionStorage.removeItem('updatePasswordDTO');
            }
        }
      });
    await this.delay(300);
  }
}
