import { JsonMessageResponseDTO } from './../../dtos/JsonMessageResponseDTO';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CardResponseDTO } from 'src/app/dtos/CardResponseDTO';
import { CardService } from 'src/app/services/card-service.service';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
   members: {color: string,action: string,title: string, subtitle: string}[] = [
      {color: '',action: 'transfer',title: 'credit_card', subtitle: 'Transfer'},
      {color: '',action: 'history',title: 'search', subtitle: 'Transaction History'},
      {color: '',action: 'account_infor',title: 'account_box', subtitle: 'Account Information'}
    ];
  cards: CardResponseDTO[] = [];
  card: CardResponseDTO;
  curUser: LoginRequestDTO;
  constructor(
    private router: Router,
    private cardService: CardService
    ) { 
      
    }

  async ngOnInit() {
    this.curUser = JSON.parse(sessionStorage.getItem('user'));
    await this.getCards(this.curUser.username);
    
  }
  onClick(action) {
    if(this.curUser != null) {
      this.router.navigate([action]);
     }
    
  }
  async getCards(accountId: string) {
    await this.cardService.getCards(accountId).then(
      data => {
        this.cards = data.jsonResponse;
        this.card = this.cards[0];
        sessionStorage.setItem('cardNumber',this.card.cardNumber);
      });
      await this.delay(300)
  }
  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=>console.log("fired"));
  }
}
