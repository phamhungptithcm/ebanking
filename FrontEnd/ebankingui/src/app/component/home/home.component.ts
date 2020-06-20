import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
   members: {action: string,title: string, subtitle: string}[] = [
      {action: 'transfer',title: 'credit_card', subtitle: 'Transfer'},
      {action: 'history',title: 'search', subtitle: 'Transaction History'},
      {action: 'account_infor',title: 'account_box', subtitle: 'Account Information'}
    ];

  constructor(private router: Router) { }

  ngOnInit() {
  }
  onClick(action) {
    this.router.navigate([action]);
  }
}
