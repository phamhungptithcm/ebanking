export class LoginRequestDTO {
    username: string;
    password: string;
    constructor(username: string, password: string) {
        this.username = username === null ? '' : username;
        this.password = password === null ? '' : password;
    }
}