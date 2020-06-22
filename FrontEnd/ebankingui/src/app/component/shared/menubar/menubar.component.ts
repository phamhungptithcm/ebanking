import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';
import { AccountService } from 'src/app/services/account-service.service';
import { AccountResponseDTO } from 'src/app/dtos/AccountResponseDTO';

@Component({
  selector: 'app-menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.css']
})
export class MenubarComponent implements OnInit {

  accountResponseDTO: AccountResponseDTO;
  isLogined= false;
  constructor(private router: Router,
    private accountService: AccountService
    ) { }
  curUser = JSON.parse(sessionStorage.getItem('user'));
  ngOnInit() {
    if(this.curUser != null && this.curUser != undefined) {
      // alert(this.curUser.username)
      this.isLogined = true;
      this.getAccountInfo(this.curUser.username);
    }
  }
  onClick(action) {
    this.router.navigate([action]);
  }
  async getAccountInfo(username: string) {
    const request  = new  LoginRequestDTO(username,'');
    this.accountService.getAccountInfo(request).then(
      data => {
        if (data !== null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
             this.accountResponseDTO = data.jsonResponse;
            }
        }
      });
      await this.delay(300);
  }
  onClickLogout() {
    this.accountService.logout();
  }
  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }
}
