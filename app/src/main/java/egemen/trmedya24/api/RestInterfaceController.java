package egemen.trmedya24.api;

import egemen.trmedya24.model.MainResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestInterfaceController {
    @GET("mobil/programlist.asp")
    Call<MainResponse> getJsonValues();


}

