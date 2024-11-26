//package Server;
//
///*
//Simulates REST calls by directly invoking methods on the reservationSystem.
// */
//
//class MockHTTPClient implements HTTPClient {
//    private final ReservationSystem reservationSystem;
//
//    public MockHTTPClient(ReservationSystem reservationSystem) {
//        this. = reservationSystem;
//    }
//
//    @Override
//    public Response post(String url, Object body) {
//        if ("/tickets".equals(url)) {
////            Ticket ticket = (Ticket) body;
////            return reservationSystem.createTicket(ticket);
//        }
//        return new SimpleResponse(404, "Unknown URL");
//    }
//
//    @Override
//    public Response get(String url) {
//        if (url.startsWith("/tickets/")) {
////            String ticketId = url.replace("/tickets/", "");
////            return reservationSystem.getTicket(ticketId);
//        }
//        return new SimpleResponse(404, "Unknown URL");
//    }
//}
