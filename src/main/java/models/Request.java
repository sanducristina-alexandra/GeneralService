package models;

public class Request {

    private String userId;
    private Topic topic;
    private short requestValue;
    public Request() {

    }

    public Request(String userId, Topic topic, short requestValue) {
        this.userId = userId;
        this.topic = topic;
        this.requestValue = requestValue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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
                ", topic=" + topic +
                ", requestValue=" + requestValue +
                '}';
    }
}
