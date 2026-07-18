public class Main {
    public static void main(String[] args) {
//        lien ket chat che
//        Client client = new Client();
//        client.process("Hello world");

        MessageService emailService = new EmailService();
        MessageService smsService = new SMSService();

        //constructor injection
//        Client client = new Client(emailService);
//        client.process("Hello world");

        //setter injection
        Client client = new Client();
//        client.setMessageService(emailService);

        // interface injection
        client.setService(emailService);
        client.process("Hello world");
    }
}