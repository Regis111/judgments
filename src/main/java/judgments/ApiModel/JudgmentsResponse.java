package judgments.ApiModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class JudgmentsResponse implements Serializable {
    private transient Object links;
    @SerializedName("items")
    private List<Judgment> judgments;
    private transient Object queryTemplate;
    private transient Object info;

    public List<Judgment> getJudgments() {
        return judgments;
    }
}
