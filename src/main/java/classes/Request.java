package classes;

public class Request {

    private String userId;
    private RequestType requestType;
    private short requestValue;
    public Request() {

    }

    public Request(String userId, RequestType requestType, short requestValue) {
        this.userId = userId;
        this.requestType = requestType;
        this.requestValue = requestValue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public short getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(short requestValue) {
        this.requestValue = requestValue;
    }

    @Override
    public String toString() {
        return "Request{" +
                "userId=" + userId +
                ", requestType=" + requestType +
                ", requestValue=" + requestValue +
                '}';
    }
}
