export class CardRequestDTO {
    cardNumber: string;
    constructor(cardNumber: string) {
        this.cardNumber = cardNumber === null ? '' : cardNumber;
    }
}