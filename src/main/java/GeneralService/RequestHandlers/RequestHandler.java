package GeneralService.RequestHandlers;

import GeneralService.Request;

public interface RequestHandler {
    void sendRequest(Request request) throws Exception;
}
