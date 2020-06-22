import { CMNDDTO } from './CMNDDTO';

export class AccountResponseDTO {
    accountId: string;
    lastName: string;
	firstName: string;
	address: string;
	city: string;
	phoneNum: string;
	cmndDTO: CMNDDTO;
	email: string;
}