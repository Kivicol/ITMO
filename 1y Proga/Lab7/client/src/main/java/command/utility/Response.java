package command.utility;

import data.Route;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 123L;
    private ResponseStatuses responseStatus;
    private String response = "";
    private Collection<Route> collection;
    public Response(){
    }
    public Response(ResponseStatuses responseStatus, String response){
        this.responseStatus = responseStatus;
        this.response = response.trim() + "\n";
    }
    public Response(ResponseStatuses status, String response, Collection<Route> collection) {
        this.responseStatus = status;
        this.response = response.trim();
        this.collection = collection.stream()
                .sorted(Comparator.comparing(Route::getId))
                .toList();
    }

    public Response(ResponseStatuses responseStatus) {
        this.responseStatus = responseStatus;
    }

    public ResponseStatuses getStatus() {
        return responseStatus;
    }
    public String getResponse() {
        return response;
    }
    public Collection<Route> getCollection() {
        return collection;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response response1)) return false;
        return responseStatus == response1.responseStatus && Objects.equals(response, response1.response) && Objects.equals(collection, response1.collection);
    }
    @Override
    public int hashCode() {
        return Objects.hash(responseStatus, response, collection);
    }
    @Override
    public String toString(){
        return "Response[" + responseStatus +
                (response.isEmpty()
                        ? ""
                        :',' + response) +
                (collection == null
                        ? ']'
                        : ',' + collection.toString() + ']');
    }
}
