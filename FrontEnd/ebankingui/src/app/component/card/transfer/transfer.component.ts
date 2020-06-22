import { CardRequestDTO } from './../../../dtos/CardRequestDTO';
import { CardResponseDTO } from './../../../dtos/CardResponseDTO';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CardService } from 'src/app/services/card-service.service';
import { JsonMessageResponseDTO } from 'src/app/dtos/JsonMessageResponseDTO';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { TransferRequestDTO } from 'src/app/dtos/TransferRequestDTO';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  form: FormGroup;
  public loginInvalid: boolean;
  private formSubmitAttempt: boolean;
  card: CardResponseDTO;
  cardTo: CardResponseDTO;
  jonMessageResponseDTO: JsonMessageResponseDTO;
  cardValid = false;
  curUser: LoginRequestDTO;
  constructor(
    private router: Router,
    private cardService: CardService,
    private fb: FormBuilder,
    ) { }

   ngOnInit() {
    const cardNumber = sessionStorage.getItem('cardNumber');
    if(cardNumber != null && cardNumber != '' && cardNumber != undefined) {
      this.getCardInfo(cardNumber);
    } else {
      this.router.navigate['/'];
    } 
    const numericNumberReg= '^-?[0-9]\\d*(\\.\\d{1,2})?$';
    this.form = this.fb.group({
      cardNumber: ['', Validators.required],
      amount: ['', [Validators.required,Validators.pattern(numericNumberReg)]],
      message: ['', Validators.required]
    });
  }
  async getCardInfo(cardNumber: string) {
    const cardRequestDTO = new CardRequestDTO(cardNumber);
    this.cardService.getCardInfo(cardRequestDTO).then(
      data => {
        this.jonMessageResponseDTO = data;
        if (data != null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
              this.card = data.jsonResponse;
            }
        }
      });
      await this.delay(300);
  }
  async onSubmit() {
    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    if (this.form.valid && this.cardValid) {
        this.transfer();
    }
       
  }
  transfer() {
    const cardNumber = this.form.get('cardNumber').value;
    const amount = this.form.get('amount').value;
    const message = this.form.get('message').value;
    const transferRequestDTO = new TransferRequestDTO(this.cardTo.cardNumber, this.cardTo.fullname,
    this.card.cardNumber, this.card.fullname,amount,this.cardTo.branchDTO.branchName,message);
    sessionStorage.setItem('transferDTO',JSON.stringify(transferRequestDTO));
    this.router.navigate(['confirmation']);

  }
  backHome() {
    this.router.navigate(['home']);
  }
  async verifyCardNumber() {
    const cardNumber = this.form.get('cardNumber').value;
    const cardRequestDTO = new CardRequestDTO(cardNumber);
    this.cardService.getCardInfo(cardRequestDTO).then(
      data => {
        this.jonMessageResponseDTO = data;
        if (data != null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
              this.cardTo = data.jsonResponse;
              this.cardValid = true;
            }
        }
      });
      await this.delay(1000);
  }
  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }
}
