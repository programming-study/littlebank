package server;

import service.RPCServiceImplement;

import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket clientSocket;
    ServerThread(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        Server server = new Server();
        server.register("service.RPCService",new RPCServiceImplement());
        try {
            server.service(this.clientSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
