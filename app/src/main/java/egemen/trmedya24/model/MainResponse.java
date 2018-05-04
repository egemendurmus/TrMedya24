package egemen.trmedya24.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TULPAR on 16.04.2018.
 */

public class MainResponse {

    @SerializedName("errorCode")
    @Expose
    public Integer errorCode;
    @SerializedName("errorMessage")
    @Expose
    public Object errorMessage;
    @SerializedName("data")
    @Expose
    public List<ProgramsResponse> data = null;
}