package Point.Of.Sale.POS.RESPONSE;

public class ApiResponseBuilder {
    private ApiResponseBuilder() {}
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response=new ApiResponse<>();
        response.setData(data);
        response.setStatus("success");
        response.setMessage("successfully retrieved data");
        response.setCode(200);
        return response;
    }
    public static <T> ApiResponse<T> success(String message) {
        ApiResponse<T> response=new ApiResponse<>();
        response.setData(null);
        response.setStatus("deleted");
        response.setMessage(message);
        response.setCode(200);
        return response;
    }
    public static <T> ApiResponse<T> create(T data) {
        ApiResponse<T> response=new ApiResponse<>();
        response.setData(data);
        response.setStatus("success");
        response.setMessage("created");
        response.setCode(201);
        return response;
    }
    public static <T> ApiResponse<T> updated(T data) {
        ApiResponse<T> response=new ApiResponse<T>();
        response.setData(data);
        response.setStatus("success");
        response.setMessage("updated");
        response.setCode(200);
        return response;
    }
    public static <T> ApiResponse<T> deleted() {
        ApiResponse<T> response=new ApiResponse<T>();
        response.setData(null);
        response.setStatus("success");
        response.setMessage("deleted");
        response.setCode(200);
        return response;
    }
    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus("error");
        response.setCode(400); // unaweza kubadilisha kulingana na aina ya error
        response.setMessage(message);
        response.setData(null);
        return response;
    }

    // Optional: unaweza kuongeza error kwa code tofauti
    public static <T> ApiResponse<T> error(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus("error");
        response.setCode(code);
        response.setMessage(message);
        response.setData(null);
        return response;
    }

}
