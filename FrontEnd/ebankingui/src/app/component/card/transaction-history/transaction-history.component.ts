import { Component, OnInit } from '@angular/core';
import { PerfectScrollbarConfigInterface,
  PerfectScrollbarComponent, PerfectScrollbarDirective } from 'ngx-perfect-scrollbar';
import { CardResponseDTO } from 'src/app/dtos/CardResponseDTO';
import { JsonMessageResponseDTO } from 'src/app/dtos/JsonMessageResponseDTO';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';
import { Router } from '@angular/router';
import { CardService } from 'src/app/services/card-service.service';
import { CardRequestDTO } from 'src/app/dtos/CardRequestDTO';
import { TransactionHistoryResponseDTO } from 'src/app/dtos/TransactionHistoryResponseDTO';
import { TransactionRequestDTO } from 'src/app/dtos/TransactionRequestDTO';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {

  card: CardResponseDTO;
  jonMessageResponseDTO: JsonMessageResponseDTO;
  jonMessageHistory: JsonMessageResponseDTO;
  transactionHistories: TransactionHistoryResponseDTO[] = [];
  curUser: LoginRequestDTO;
  constructor( private router: Router,
    private cardService: CardService
    ) { }
  public config: PerfectScrollbarConfigInterface = {};

  ngOnInit() {
    const cardNumber = sessionStorage.getItem('cardNumber');
      this.getCardInfo(cardNumber);
      this.getTransactionHistory(cardNumber);
  }
  onClickSearch() {
    
  }
  getCardInfo(cardNumber: string): void {
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
  }
  getTransactionHistory(cardNumber) {
    const from = null;
    const to = null;
    const maxResult = 10;
    const request = new TransactionRequestDTO(cardNumber,from,to,maxResult);
    this.cardService.getTransactionHistory(request).then(
      data => {
        this.jonMessageResponseDTO = data;
        if (data != null) {
            if (data.jsonResponse != '' && data.jsonResponse != undefined) {
              this.transactionHistories = data.jsonResponse;
            }
        }
      });
  }
  backHome() {
    this.router.navigate['home'];
  }
}
