package rules;

import behaviours.IBehavior;
import elements.Email;
import elements.Folder;

import java.util.ArrayList;
import java.util.List;

public class SimpleCondition {
    private int prioridad;
    private String name;
    private String operator;
    private String value;
    private Folder destiny;
    private IBehavior action;
    private List<SimpleCondition> body;
    public SimpleCondition(int prioridad, String name, String value,String operator,Folder attribute, IBehavior action) {
        this.setPrioridad(prioridad);
        this.setName(name);
        this.setDestiny(attribute);
        this.setAction(action);
        this.body = new ArrayList<SimpleCondition>();
        this.setOperator(operator);
        this.value = value;
    }
    public SimpleCondition apply(Email email, SimpleCondition condition) {
        SimpleCondition res = null;
        //TODO logica de operacion, por ahoras solo se implementar el igual y se comparar el campo from y cc
        if(condition.getOperator().equals("cc")){
            if(condition.getValue().equalsIgnoreCase(email.getCc())){
                res = condition;
            }
        }else{
            if(condition.getOperator().equals("from")){
                if(condition.getOperator().equalsIgnoreCase(email.getFrom())){
                    res = condition;
                }
            }
        }
        return res;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getDestiny() {
        return destiny;
    }

    public void setDestiny(Folder destiny) {
        this.destiny = destiny;
    }

    public IBehavior getAction() {
        return action;
    }

    public void setAction(IBehavior action) {
        this.action = action;
    }

    public List<SimpleCondition> getBody() {
        return body;
    }

    public void setBody(List<SimpleCondition> body) {
        this.body = body;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
