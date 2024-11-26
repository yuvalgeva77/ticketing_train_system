package Server;

/*
Encapsulates the status code and body of a response, mimicking HTTP responses.
 */

class SimpleResponse implements Response {
    private final int statusCode;
    private final Object body;

    public SimpleResponse(int statusCode, Object body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public Object getBody() {
        return body;
    }
}

