package judgments.Functions;

import judgments.Judgment;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractFunction {
    HashMap<String, Judgment> judgementHashMap;
    String command;

    public AbstractFunction(HashMap<String, Judgment> judgementHashMap, String command){
        this.judgementHashMap = judgementHashMap;
        this.command = command;
    }

    public abstract String function(List<String> list);
}
