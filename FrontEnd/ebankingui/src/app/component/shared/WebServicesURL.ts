export class WebServicesURL {
    DOMAIN = 'http://localhost:8089';
    // DOMAIN = 'http://192.168.1.9:8089';
    OAUTH_TOKEN_URL = this.DOMAIN + '/oauth/token';
    ACCOUNT_SERVICE_CONTEXT_PATH = 'user/api/ebanking/service/accountService';
    GET_ACCOUNT_INFO_ACTION_PATH = 'getAccountInfo';
    LOGIN_ACTION_PATH = 'login';
    LOGOUT_ACTION_PATH = 'logout';
    FORGOT_PASSWORD_ACTION_PATH = 'forgot';

    CARD_SERVICE_CONTEXT_PATH = 'user/api/ebanking/service/cardService';
    GET_CARD_INFO_ACTION_PATH ='getCardInfo';
    GET_CARDS_ACTION_PATH = 'getCards';
    GET_TRANSACTION_HISTORY_ACTION_PATH = 'getTransactionHistory';
    TRANSFER_ACTION_PATH = 'transfer';
    SEND_SMS_ACTION_PATH = "sendSMS";
    SEND_MAIL_ACTION_PATH = 'sendMail';
    SEND_TRANSACTION_INFO = 'sendTransactionInfo';
    CHECK_VERIFICATION_CODE = 'verifyCode';

    /// Get token
    TOKEN_PARAM = 'access_token=' + localStorage.getItem("access_token");

}