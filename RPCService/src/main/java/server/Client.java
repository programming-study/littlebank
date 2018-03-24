package server;

import service.RemoteCall;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public void invoke() throws Exception {
        Socket socket = new Socket("localhost", 8000);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        RemoteCall remoteCall = new RemoteCall("service.RPCService", "getInformation",
                new Class[]{String.class}, new Object[]{"test success!"});
        System.out.println(remoteCall.getResult());
        objectOutputStream.writeObject(remoteCall);
        RemoteCall remoteCall_new = (RemoteCall) objectInputStream.readObject();
        System.out.println(remoteCall_new.getResult());
        objectInputStream.close();
        objectOutputStream.close();
        inputStream.close();
        outputStream.close();
    }

    public static void main(String[] args) throws Exception {
        new Client().invoke();
    }
}
