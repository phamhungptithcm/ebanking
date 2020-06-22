export class TransactionRequestDTO {
    cardNumber: string;
	from: Date;
	to: Date;
	maxResult: number;
	constructor(cardNumber: string, from: Date, to: Date, maxResult: number) {
		this.cardNumber = cardNumber === null ? '' : cardNumber;
		this.from = from === null ? null: from;
		this.to = to === null ? null : to;
		this.maxResult = maxResult === null ? 0 : maxResult;

	}
}