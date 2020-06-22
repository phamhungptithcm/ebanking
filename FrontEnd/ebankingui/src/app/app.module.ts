import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/account/login/login.component';
import { AccountinfoComponent } from './component/account/accountinfo/accountinfo.component';
import { CardinfoComponent } from './component/card/cardinfo/cardinfo.component';
import { TransferComponent } from './component/card/transfer/transfer.component';
import { TransactionHistoryComponent } from './component/card/transaction-history/transaction-history.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './component/home/home.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatToolbarModule,
  MatMenuModule,
  MatIconModule,
  MatButtonModule,
  MatTableModule,
  MatDividerModule,
  MatProgressSpinnerModule,
  MatInputModule,
  MatCardModule,
  MatSlideToggleModule,
  MatListModule,
  MatSelectModule,
  MatSidenavModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatOptionModule} from '@angular/material';
import { MenubarComponent } from './component/shared/menubar/menubar.component';
import { FooterComponent } from './component/shared/footer/footer.component';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { ConfirmationComponent } from './component/card/confirmation/confirmation.component';
import { VerificationComponent } from './component/card/verification/verification.component';
import { OverviewComponent } from './component/card/overview/overview.component';
import { UpdatepasswordComponent } from './component/account/updatepassword/updatepassword.component';
import { ForgotpasswordComponent } from './component/account/forgotpassword/forgotpassword.component';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  wheelPropagation: true
};

@NgModule({ 
  declarations: [
    AppComponent,
    LoginComponent,
    AccountinfoComponent,
    CardinfoComponent,
    TransferComponent,
    TransactionHistoryComponent,
    HomeComponent,
    MenubarComponent,
    FooterComponent,
    ConfirmationComponent,
    VerificationComponent,
    OverviewComponent,
    UpdatepasswordComponent,
    ForgotpasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatInputModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatDividerModule,
    MatDatepickerModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatOptionModule,
    MatProgressSpinnerModule ,
    PerfectScrollbarModule,
    HttpClientModule,
    MatNativeDateModule
  ],
  providers: [
    {
      provide: PERFECT_SCROLLBAR_CONFIG,
      useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
