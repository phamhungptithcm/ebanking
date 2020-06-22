import { tap, catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { WebServicesURL } from '../component/shared/WebServicesURL';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { CardRequestDTO } from '../dtos/CardRequestDTO';
import { JsonMessageResponseDTO } from '../dtos/JsonMessageResponseDTO';
import { Observable, of } from 'rxjs';
import { TransferRequestDTO } from '../dtos/TransferRequestDTO';
import { OTPRequestDTO } from '../dtos/OTPRequestDTO';
import { TransactionRequestDTO } from '../dtos/TransactionRequestDTO';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class CardService extends WebServicesURL{

  constructor(private http: HttpClient) {
    super();
   }
  async getCardInfo(request: CardRequestDTO): Promise<any> {
    const url = `${this.DOMAIN}/${this.CARD_SERVICE_CONTEXT_PATH}/${this.GET_CARD_INFO_ACTION_PATH}?${this.TOKEN_PARAM}`;
    return this.http.post<JsonMessageResponseDTO>(url, request, httpOptions).toPromise();
   }
   async getCards(accountId: string): Promise<any>{
    const url = `${this.DOMAIN}/${this.CARD_SERVICE_CONTEXT_PATH}/${this.GET_CARDS_ACTION_PATH}?accountId=${accountId}&${this.TOKEN_PARAM}`;
    return await this.http.get<JsonMessageResponseDTO>(url).pipe(
      tap(),
      catchError(error => of(new JsonMessageResponseDTO()))
    ).toPromise();;
   }
   async getTransactionHistory(request: TransactionRequestDTO): Promise<any>{
    const url = `${this.DOMAIN}/${this.CARD_SERVICE_CONTEXT_PATH}/${this.GET_TRANSACTION_HISTORY_ACTION_PATH}?${this.TOKEN_PARAM}`;
    return this.http.post<JsonMessageResponseDTO>(url, request, httpOptions).toPromise();;
   }
   async transferMoney(request: TransferRequestDTO): Promise<any>{
    const url = `${this.DOMAIN}/${this.CARD_SERVICE_CONTEXT_PATH}/${this.TRANSFER_ACTION_PATH}?${this.TOKEN_PARAM}`;
    return this.http.post<JsonMessageResponseDTO>(url, request, httpOptions).toPromise();;
   }
   async sendOTPViaSMS(request: OTPRequestDTO): Promise<any>{
    const url = `${this.DOMAIN}/${this.CARD_SERVICE_CONTEXT_PATH}/${this.SEND_SMS_ACTION_PATH}?${this.TOKEN_PARAM}`;
    return this.http.post<JsonMessageResponseDTO>(url, request, httpOptions).toPromise();;
   }

   async sendOTPViaMail(request: OTPRequestDTO): Promise<any>{
    const url = `${this.DOMAIN}/${this.CARD_SERVICE_CONTEXT_PATH}/${this.SEND_MAIL_ACTION_PATH}?${this.TOKEN_PARAM}`;
    return this.http.post<JsonMessageResponseDTO>(url, request, httpOptions).toPromise();;
   }
   async checkVerificationCode(request: OTPRequestDTO): Promise<any>{
    const url = `${this.DOMAIN}/${this.CARD_SERVICE_CONTEXT_PATH}/${this.CHECK_VERIFICATION_CODE}?${this.TOKEN_PARAM}`;
    return this.http.post<JsonMessageResponseDTO>(url, request, httpOptions).toPromise();;
   }
   async sendTransactionInfo(transactionId: string): Promise<any>{
    const url = `${this.DOMAIN}/${this.CARD_SERVICE_CONTEXT_PATH}/${this.SEND_TRANSACTION_INFO}?transactionId=${transactionId}&${this.TOKEN_PARAM}`;
    return this.http.get<JsonMessageResponseDTO>(url).pipe(
      tap(),
      catchError(error => of(new JsonMessageResponseDTO()))
    ).toPromise();;
   }
}
