package judgments.Functions;

import judgments.Judgment;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractFunction {
    HashMap<String, Judgment> JudgmentHashMap;

    public AbstractFunction(HashMap<String, Judgment> JudgmentHashMap){
        this.JudgmentHashMap = JudgmentHashMap;
    }

    public abstract String function(List<String> list);
}
