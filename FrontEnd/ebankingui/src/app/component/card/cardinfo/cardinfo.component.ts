import { Component, OnInit } from '@angular/core';
import { CardService } from 'src/app/services/card-service.service';
import { Router } from '@angular/router';
import { JsonMessageResponseDTO } from 'src/app/dtos/JsonMessageResponseDTO';
import { CardResponseDTO } from 'src/app/dtos/CardResponseDTO';
import { CardRequestDTO } from 'src/app/dtos/CardRequestDTO';
import { LoginRequestDTO } from 'src/app/dtos/LoginRequestDTO';

@Component({
  selector: 'app-cardinfo',
  templateUrl: './cardinfo.component.html',
  styleUrls: ['./cardinfo.component.css']
})
export class CardinfoComponent implements OnInit {

  card: CardResponseDTO;
  jonMessageResponseDTO: JsonMessageResponseDTO;
  curUser: LoginRequestDTO;
  constructor( private router: Router,
    private cardService: CardService
    ) { }
  ngOnInit() {
      const cardNumber = sessionStorage.getItem('cardNumber');
      this.getCardInfo(cardNumber);
      
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
  backHome() {
    this.router.navigate['home'];
  }
}
