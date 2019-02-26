package elements;

import rules.SimpleCondition;

import java.util.List;

public class RuleManager {
    String name;
    public RuleManager(String name){
        this.name = "INSPECTOR: "+name;
    }
    public SimpleCondition evaluar(Email email, List<Folder> folders) throws Exception{
        for (Folder folder: folders){
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Evaluando: "+folder.getName());
            if(folder.getConditions().size() > 0) {
                for (SimpleCondition condition : folder.getConditions()) {
                    System.out.println("regla: "+condition.getName());
                    return condition.apply(email, condition);
                }
            }else{
                return evaluar(email, folder.getFolders());
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        }
        throw new Exception("No aplican reglas!!");
    }
}
