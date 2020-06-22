import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './component/account/login/login.component';
import { HomeComponent } from './component/home/home.component';
import { AuthGuardService } from './services/auth-guard.service';
import { TransferComponent } from './component/card/transfer/transfer.component';
import { TransactionHistoryComponent } from './component/card/transaction-history/transaction-history.component';
import { AccountinfoComponent } from './component/account/accountinfo/accountinfo.component';
import { CardinfoComponent } from './component/card/cardinfo/cardinfo.component';
import { ConfirmationComponent } from './component/card/confirmation/confirmation.component';
import { VerificationComponent } from './component/card/verification/verification.component';
import { OverviewComponent } from './component/card/overview/overview.component';
import { UpdatepasswordComponent } from './component/account/updatepassword/updatepassword.component';
import { BasicrequestGuard } from './guard/basicrequest.guard';


const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [ AuthGuardService,BasicrequestGuard ]
  },
  {
    path: 'transfer',
    component: TransferComponent,
    canActivate: [ AuthGuardService,BasicrequestGuard]
  },
  {
    path: 'history',
    component: TransactionHistoryComponent,
    canActivate: [ AuthGuardService,BasicrequestGuard ]
  },
  {
    path: 'account_infor',
    component: AccountinfoComponent,
    canActivate: [ AuthGuardService,BasicrequestGuard ]
  },
  {
    path: 'card_detail',
    component: CardinfoComponent,
    canActivate: [ AuthGuardService,BasicrequestGuard ]
  },
  {
    path: 'confirmation',
    component: ConfirmationComponent,
    canActivate: [ AuthGuardService,BasicrequestGuard ]
  },
  {
    path: 'verification',
    component: VerificationComponent,
    canActivate: [ AuthGuardService,BasicrequestGuard ]
  },
  {
    path: 'summary',
    component: OverviewComponent,
    canActivate: [ AuthGuardService,BasicrequestGuard ]
  },
  {
    path: 'change_password',
    component: UpdatepasswordComponent,
    canActivate: [ AuthGuardService ,BasicrequestGuard]
  },
];;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
