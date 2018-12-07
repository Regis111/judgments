package judgments.Functions;

import judgments.Judgment;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractFunction {
    public HashMap<String, Judgment> JudgmentHashMap;
    private byte numberOfArgumentsDemanded;

    public AbstractFunction(HashMap<String, Judgment> JudgmentHashMap, int numberOfArgumentsDemanded){
        this.JudgmentHashMap = JudgmentHashMap;
        this.numberOfArgumentsDemanded = this.numberOfArgumentsDemanded;
    }

    public abstract String function(List<String> list);
}
