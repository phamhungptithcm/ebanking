export class OTPRequestDTO{
    phoneNumber: string;
	email: string;
	verifyCode: string
	constructor(phoneNumber: string, email: string,verifyCode: string){
		this.phoneNumber = phoneNumber === null ? '' : phoneNumber;
		this.email = email === null ? '' : email;
		this.verifyCode = verifyCode === null ? '' : verifyCode;
	}
}