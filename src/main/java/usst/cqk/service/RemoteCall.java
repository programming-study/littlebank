package usst.cqk.service;

import java.io.Serializable;

public class RemoteCall implements Serializable {
    private String className;
    private String methodName;
    private Class[] paramTypes;
    private Object[] params;
    private Object result;

    public RemoteCall() {}

    public RemoteCall(String className, String methodName, Class[] paramTypes, Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.params = params;
        this.paramTypes = paramTypes;
    }


    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
