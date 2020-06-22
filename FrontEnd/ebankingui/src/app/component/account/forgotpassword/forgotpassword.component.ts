import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/services/account-service.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.css']
})
export class ForgotpasswordComponent implements OnInit {

  constructor(private fb: FormBuilder,
    private router: Router,
    private authService: AccountService,) { }
  form: FormGroup;
  account: LoginRequestDTO;
  isvalid =true;
  curUser: LoginRequestDTO;
  ngOnInit() {
    this.form = this.fb.group({
      username: ['', Validators.required],
    });
  }
  async onSubmit() {
    const username= this.form.get('username').value;
    if (this.form.valid) {
      const request  = new  LoginRequestDTO(username,'');
       this.authService.getAccountInfo(request).then(
        data => {
        if (data != null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
              this.account = data.jsonResponse;
              sessionStorage.setItem('user', JSON.stringify(this.account));
              this.router.navigate(['update_password'])
              this.isvalid = true;
            } else {
              this.isvalid = false;
            }
        } else {
          this.isvalid = false;
        }
      });
    }
  }
  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }
}
