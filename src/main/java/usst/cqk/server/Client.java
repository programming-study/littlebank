package usst.cqk.server;

import usst.cqk.service.RemoteCall;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private String className="usst.cqk.service.RPCService";
    private String methodName;
    private Class[] classes;
    private Object[] objects;

    public Client(String methodName,Class[] classes,Object[] objects) {
        this.methodName = methodName;
        this.classes = classes;
        this.objects = objects;
    }

    public String invoke() throws Exception {
        Socket socket = new Socket("localhost", 8000);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        RemoteCall remoteCall = new RemoteCall(className, methodName,
                classes, objects);
        System.out.println(remoteCall.getResult());
        objectOutputStream.writeObject(remoteCall);
        RemoteCall remoteCall_new = (RemoteCall) objectInputStream.readObject();
        System.out.println(remoteCall_new.getResult());
        objectInputStream.close();
        objectOutputStream.close();
        inputStream.close();
        outputStream.close();
        return String.valueOf(remoteCall_new.getResult());
    }

    public static void main(String[] args) throws Exception {
        new Client("getInformation",new Class[]{String.class},new Object[]{"test success!"}).invoke();
    }
}
