import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from 'src/app/services/account-service.service';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';
import { AccountResponseDTO } from 'src/app/dtos/AccountResponseDTO';
import { JsonMessageResponseDTO } from 'src/app/dtos/JsonMessageResponseDTO';
import { SubjectService } from 'src/app/services/SubjectService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  public loginInvalid: boolean;
  private formSubmitAttempt: boolean;
  private returnUrl: string;
  private curUser: LoginRequestDTO;
  private jsonMessageResponseDTO: JsonMessageResponseDTO;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AccountService,
    private subjectService: SubjectService
  ) {
  }

  async ngOnInit() {
    // this.returnUrl = this.route.snapshot.queryParams.returnUrl || '/game';
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  async onSubmit() {
    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    if (this.form.valid) {
        this.login();
    }
       
  }

  login() {
    const username = this.form.get('username').value;
    const password = this.form.get('password').value;
    const account = new LoginRequestDTO(username, password);
    this.authService.removeToken();
    this.authService.loginUser(account).subscribe(data => {
      this.jsonMessageResponseDTO = data;
      if (data != null) {
        if (data.jsonResponse != '' && data.jsonResponse != undefined) {
          this.curUser = data.jsonResponse;
          if(this.curUser != null) {
             // tslint:disable-next-line:no-shadowed-variable
            this.authService.loginBody(account).subscribe(data => {
              this.authService.saveToken('access_token', data.access_token);
              this.authService.saveToken('refresh_token', data.refresh_token);
              this.authService.saveToken('expires_in', data.expires_in);
           });
           this.loginInvalid = false;
            this.subjectService.logged();
            sessionStorage.setItem('user', JSON.stringify(this.curUser));
            this.form.reset();
            this.router.navigate(['home']);
            setTimeout(function () {
                location.reload()
            }, 300);
            clearTimeout();
          } else {
            this.loginInvalid = true;
          }
        } else {
          this.loginInvalid = true;
        }
      } else {
        this.loginInvalid = true;
      }
    });
  }
  async forgotPassword(){
    this.router.navigate(['/forgotPassword'])
  }
}
