package judgments.Functions;

import judgments.Judgment;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractFunction {
    HashMap<String, Judgment> JudgmentHashMap;
    String command;

    public AbstractFunction(HashMap<String, Judgment> JudgmentHashMap, String command){
        this.JudgmentHashMap = JudgmentHashMap;
        this.command = command;
    }

    public abstract String function(List<String> list);
}
