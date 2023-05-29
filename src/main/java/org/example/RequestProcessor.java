package org.example;

public class RequestProcessor {
    public static boolean processRequest(Request request) {
        switch (request.requestType) {
            case WINDOW_CONTROL_SERVICE_REQUEST: {
                return processWindowControlServiceRequest(request);
            }
            case MUSIC_CONTROL_SERVICE_REQUEST: {
                return processMusicControlServiceRequest(request);
            }
            case CLIMATIZATION_SERVICE_REQUEST: {
                return processClimatizationServiceRequest(request);
            }
            case TRIP_HISTORY_SERVICE_REQUEST: {
                return processTripHistoryServiceRequest(request);
            }
            default:
                return false;
        }
    }

    private static boolean processWindowControlServiceRequest(Request request) {
        System.out.println("Processing WINDOW_CONTROL_SERVICE_REQUEST ...");
        return true;
    }

    private static boolean processMusicControlServiceRequest(Request request) {
        System.out.println("Processing MUSIC_CONTROL_SERVICE_REQUEST ...");
        return true;
    }

    private static boolean processClimatizationServiceRequest(Request request) {
        System.out.println("Processing CLIMATIZATION_SERVICE_REQUEST ...");
        return true;
    }

    private static boolean processTripHistoryServiceRequest(Request request) {
        System.out.println("Processing TRIP_HISTORY_SERVICE_REQUEST ...");
        return true;
    }

}
