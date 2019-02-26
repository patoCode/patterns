package rules;
import behaviours.IBehavior;
import elements.IContainer;

import java.util.ArrayList;
import java.util.List;


public class GroupCondition implements ICondition {
    int prioridad;
    String name;
    IContainer attribute;
    Operator operator;
    IBehavior action;
    List<SimpleCondition> conditions;
    public GroupCondition(int prioridad, String name, IBehavior action, Operator operator, IContainer attribute){
        this.prioridad = prioridad;
        this.name = name;
        this.attribute = attribute;
        conditions = new ArrayList<>();
        this.action = action;
        this.operator = operator;
    }

    IBehavior getAction(){
        return action;
    }
    @Override
    public boolean apply() {
        return false;
    }
}
