import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/services/account-service.service';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';
import { OTPRequestDTO } from 'src/app/dtos/OTPRequestDTO';
import { CardService } from 'src/app/services/card-service.service';

@Component({
  selector: 'app-updatepassword',
  templateUrl: './updatepassword.component.html',
  styleUrls: ['./updatepassword.component.css']
})
export class UpdatepasswordComponent implements OnInit {

  constructor(private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private cardService: CardService,
    private authService: AccountService,) { }
  curUser: LoginRequestDTO;
  verifyCode: string;
  form: FormGroup;
  ngOnInit() {
    this.curUser = JSON.parse(sessionStorage.getItem('user'));
    this.form = this.fb.group({
      oldPassword: ['', Validators.required],
      newPassword: ['', [Validators.required,Validators.minLength(6),Validators.maxLength(30)]],
      confirmPassword: ['', [Validators.required,Validators.minLength(6),Validators.maxLength(30)]]
    },  { 
      validators: this.password.bind(this)
    });
  }
  password(formGroup: FormGroup) {
    const { value: password } = formGroup.get('newPassword');
    const { value: confirmPassword } = formGroup.get('confirmPassword');
    return password === confirmPassword ? null : { passwordNotMatch: true };
  }
  async onSubmit() {
    const oldPassword= this.form.get('oldPassword').value;
    if (this.form.valid) {
      if(this.curUser.password == oldPassword){
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
        this.router.navigate(['verification']);
      } else {
        return;
      }
    } 
  }
}
