export class TransactionHistoryResponseDTO{
    transactionId: string;
	
	transactionDescription: string;
	
	transactionDate: Date;

	transactionMessage: string;
	
	transactionStatus: boolean;

	amount: number;
	dayOfWeek: string;
	dayOfMonth: string;
	date: string;
}