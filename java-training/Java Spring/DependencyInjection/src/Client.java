public class Client implements InjectionMessage{
    //Lien ket rang buoc tigh-coupling
    //private EmailService emailService = new EmailService();

    private MessageService messageService;

    //contructor injection
//    public Client(MessageService messageService){
//        this.messageService= messageService;
//    }

    //setter injection
//    public void setMessageService(MessageService messageService){
//        this.messageService = messageService;
//    }

    public void process(String message){
        messageService.sendMessage(message);
        // tight-coupling
//        emailService.sendMessage(message);
    }

    @Override
    public void setService(MessageService messageService) {
        this.messageService = messageService;
    }
}
