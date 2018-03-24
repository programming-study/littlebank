package service;

public class RPCServiceImplement implements RPCService {
    @Override
    public String getInformation(String s) {
        return "received information:" + s;
    }

    @Override
    public String checkRest() {
        return null;
    }

    @Override
    public String deposit() {
        return null;
    }

    @Override
    public String withdraw() {
        return null;
    }

    @Override
    public String transfer() {
        return null;
    }
}
