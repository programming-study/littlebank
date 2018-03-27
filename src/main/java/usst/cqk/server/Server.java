package usst.cqk.server;

import usst.cqk.service.RemoteCall;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private Map remoteObjects = new HashMap();

    public void register(String className, Object remoteObject) {
        remoteObjects.put(className, remoteObject);
    }

    public void service(Socket client) throws Exception {
        System.out.println("starting...");
        Socket socket = client;
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        RemoteCall remoteCall = (RemoteCall) objectInputStream.readObject();
        System.out.println(remoteCall.getResult());
        remoteCall = invoke(remoteCall);
        System.out.println(remoteCall.getResult());
        objectOutputStream.writeObject(remoteCall);
        objectInputStream.close();
        objectOutputStream.close();
        socket.close();
    }

    public RemoteCall invoke(RemoteCall remoteCall){
        Object result=null;
        try {
            String className = remoteCall.getClassName();
            String methodName = remoteCall.getMethodName();
            Object[] params = remoteCall.getParams();
            Class[] paramTypes = remoteCall.getParamTypes();
            Class classType = Class.forName(className);
            Method method = classType.getMethod(methodName, paramTypes);

            Object remoteObject = remoteObjects.get(className);
            if (remoteObject == null) {
                throw new Exception(className + "not exist");
            } else {
                result = method.invoke(remoteObject, params);
            }
        } catch (Exception e) {
            result = e;
        }
        remoteCall.setResult(result);

        return remoteCall;
    }

    public static void main(String args[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Socket clientSocket = serverSocket.accept();

            ServerThread serverThread = new ServerThread(clientSocket);
            Thread thread = new Thread(serverThread);
            thread.start();
        }
    }

}
