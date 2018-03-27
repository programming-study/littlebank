package usst.cqk.service;


public interface RPCService {

    public String getInformation(String s);

    public String checkRest(String cardId);

    public String deposit(String cardId,double money);

    public String withdraw(String cardId,double money);

    public String transfer(String cardId,String toCardId,double money);

    public String loginIn(String cardId, String password);

    public String register(String cardId,String password);
}
