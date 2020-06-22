import { BranchDTO } from './BranchDTO';

 export class CardResponseDTO {
    cardNumber: string;
    balance: number;
	availableBalance: number;
	overdraftTinterRestrate: string;
	overdraftLimit: string;
	issueDate: string;
	fullname: string;
	branchDTO: BranchDTO;
 }