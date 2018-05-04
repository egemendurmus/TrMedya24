package egemen.trmedya24.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProgramsResponse {
    @SerializedName("day")
    @Expose
    public String day;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("shortContent")
    @Expose
    public String shortContent;
    @SerializedName("startTime")
    @Expose
    public String startTime;
    @SerializedName("endTime")
    @Expose
    public String endTime;
}
