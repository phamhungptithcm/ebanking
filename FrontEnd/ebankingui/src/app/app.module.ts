import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/account/login/login.component';
import { AccountinfoComponent } from './component/account/accountinfo/accountinfo.component';
import { CardinfoComponent } from './component/card/cardinfo/cardinfo.component';
import { TransferComponent } from './component/card/transfer/transfer.component';
import { TransactionHistoryComponent } from './component/card/transaction-history/transaction-history.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AccountinfoComponent,
    CardinfoComponent,
    TransferComponent,
    TransactionHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
