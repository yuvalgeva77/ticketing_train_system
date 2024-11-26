package Server;

interface Response {
    int getStatusCode();
    Object getBody();
}

interface HTTPClient {
    Response post(String url, Object body);
    Response get(String url);
}

