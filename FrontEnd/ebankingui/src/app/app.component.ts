import { Component, OnInit } from '@angular/core';
import { AccountService } from './services/account-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'EBanking';
  isAuthenticated: boolean;

  constructor(public authService: AccountService) {
  }

  async ngOnInit() {
  }

  logout() {
   
  }
}
