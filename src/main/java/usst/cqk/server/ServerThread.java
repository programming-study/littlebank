package usst.cqk.server;

import usst.cqk.service.RPCServiceImplement;

import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket clientSocket;
    ServerThread(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        Server server = new Server();
        server.register("usst.cqk.service.RPCService",new RPCServiceImplement());
        try {
            server.service(this.clientSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
