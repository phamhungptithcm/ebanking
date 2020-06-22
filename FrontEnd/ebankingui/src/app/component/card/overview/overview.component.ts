import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TransferRequestDTO } from 'src/app/dtos/TransferRequestDTO';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css']
})
export class OverviewComponent implements OnInit {

  constructor(private router: Router) { }
  summaryDTO: TransferRequestDTO;
  ngOnInit() {
    this.summaryDTO = JSON.parse(sessionStorage.getItem('summaryDTO'));
  }
  onClick(action) {
    this.router.navigate([action]);
  }
}
