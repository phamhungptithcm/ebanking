export class TransferRequestDTO{
    cardNumberReceiver: string;
	
	nameReceiver: string;
	
	cardNumberTransfer: string;
	
	nameTransfer: string;
	
	transferAmount: number;
	
	branchNameReceiver: string;
	
	message: string;
	
	transactionId: string;
	
	transactionDate: Date;
	status: boolean;
	
	constructor(cardNumberReceiver: string, nameReceiver: string,cardNumberTransfer: string,
		nameTransfer: string,transferAmount: number,branchNameReceiver: string,message: string){
			this.cardNumberReceiver = cardNumberReceiver === null ? '' : cardNumberReceiver;
			this.nameReceiver = nameReceiver === null ? '' : nameReceiver;
			this.cardNumberTransfer = cardNumberTransfer === null ? '' : cardNumberTransfer;
			this.nameTransfer = nameTransfer === null ? '' : nameTransfer;
			this.transferAmount = transferAmount === null ? 0 : transferAmount;
			this.branchNameReceiver = branchNameReceiver === null ? '' : branchNameReceiver;
			this.message = message === null ? '' : message;
		}
		
	
}