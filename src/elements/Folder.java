package elements;

import behaviours.IBehavior;
import rules.ICondition;
import rules.SimpleCondition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class Folder implements IContainer, ICondition{
    private String name;
    private List<Folder> folders;
    private List<IBehavior> behaviors;
    private List<Email> emails;
    private List<SimpleCondition> conditions;
    private RuleManager inspector;

    public Folder(String name) {
        this.setName(name);
        setEmails(new ArrayList<Email>());
        setBehaviors(new ArrayList<IBehavior>());
        setFolders(new ArrayList<Folder>());
        setConditions(new ArrayList<SimpleCondition>());
        setInspector(new RuleManager(name));
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addRule(SimpleCondition condition){
        this.getConditions().add(condition);
    }
    public void addBehavior(IBehavior behavior){
        getBehaviors().add(behavior);
    }
    public Folder addFolder(String name){ 
        Folder folder = new Folder(name);
        getFolders().add(folder);
        return folder;
    }
    public Folder addFolder(String name, String parent) throws Exception{
        Folder parentFolder = this.find(parent);
        if(parentFolder != null)
            return parentFolder.addFolder(name);
        return null;
    }
    public void addEmail(Email email) throws Exception {
        //TODO reglas para seleccionar folder
        try{
            inspector.evaluar(email, getFolders());
        }catch(Exception e){
            System.out.println("ERROR CONDICION " + e.getMessage());
        }

    }
    public List<Email> getEmails(){
        return emails;
    }
    public void print(){
        System.out.println(this.getName());
        printRules();
        printMessages();
        for (Folder folder : getFolders()) {
            folder.print();    
        }
    }
    public Folder find(String name) throws Exception{
            for (Folder folder : getFolders()) {
                if(folder.getName().equals(name))
                    return folder;
                else 
                    if(folder.getFolders().size() > 0)
                        return folder.find(name);
            }    
        throw new Exception("No existe el folder!!");
    }
    public void printMessages(){
        System.out.println("Mensajes en bandeja: "+ getEmails().size()+" ");
        for (Email mail : getEmails()) {
            System.out.println(" ["+mail.getSubject()+"]: "+ mail.getFrom());
        }
        System.out.println("------------------------------------------------");
    }
    public void printRules(){
        System.out.println(" [ "+getConditions().size()+" ] REGLAS");
    }
    /*GETTER SETTER */
    public String getName() {
        return name;
    }
    public List<Folder> getFolders() {
        return folders;
    }
    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
    public List<IBehavior> getBehaviors() {
        return behaviors;
    }
    public void setBehaviors(List<IBehavior> behaviors) {
        this.behaviors = behaviors;
    }
    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
    public List<SimpleCondition> getConditions() {
        return conditions;
    }
    public void setConditions(List<SimpleCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean apply() {
        return true;
    }

    public RuleManager getInspector() {
        return inspector;
    }

    public void setInspector(RuleManager inspector) {
        this.inspector = inspector;
    }
}
