package elements;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import behaviours.HardDelete;
import behaviours.IBehavior;
import behaviours.Moveable;
import behaviours.SoftDelete;
import rules.ICondition;
import rules.SimpleCondition;
import rules.Subject;

import java.util.List;


/**
 *
 * @author
 */
public class EmailClient implements Subject {
    private Folder inbox;
    private Folder sent;
    private Folder trash;
    private Folder root;
    private List<Folder> folders;

    IBehavior hardDelete = new HardDelete();
    IBehavior softDelete = new SoftDelete();
    IBehavior move = new Moveable();

    public EmailClient() {
        root = new Folder("root");
        setInbox(root.addFolder("imbox"));
        setSent(root.addFolder("sent"));
        setTrash(root.addFolder("trash"));

        getInbox().addBehavior(softDelete);
        getInbox().addBehavior(move);
        
        getSent().addBehavior(softDelete);
        getSent().addBehavior(move);

        getTrash().addBehavior(move);
        getTrash().addBehavior(hardDelete);
    }
    public void receive(Email email) throws Exception {
        try{
            SimpleCondition applyCondition = getRoot().getInspector().evaluar(email, getRoot().getFolders());
            if( applyCondition != null ){
                Folder destiny = applyCondition.getDestiny();
                System.out.println("COPIAR MAIL A "+destiny.getName());
                destiny.addEmail(email);
            }else{
                getInbox().addEmail(email);
            }
        }catch(Exception e){
            getInbox().addEmail(email);
        }
    }
    public Folder addFolder(Folder folder) {
        return root.addFolder(folder.getName());
    }
    public void addFolder(Folder child, Folder parent) {
        try{
            Folder parentFolder = getFolderByName(parent);
            if(parentFolder != null)
                parentFolder.addFolder(child.getName());
        }catch(Exception e){
            System.out.println("Exception  " + e.getMessage());
        }
    }
    public Folder getFolderByName(Folder folder) throws Exception{
        return root.find(folder.getName());
    }
    public void addRule(Folder folder, SimpleCondition condition ) {
        try{
            Folder target = getRoot().find(folder.getName());
            target.addRule(condition);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void print(){
        root.print();
    }
    public Folder getRoot(){
        return root;
    }
    public Folder getInbox() {
        return inbox;
    }

    public void setInbox(Folder inbox) {
        this.inbox = inbox;
    }

    public Folder getSent() {
        return sent;
    }

    public void setSent(Folder sent) {
        this.sent = sent;
    }

    public Folder getTrash() {
        return trash;
    }

    public void setTrash(Folder trash) {
        this.trash = trash;
    }
    @Override
    public void notifyObservers() {
        for (Folder folder: getFolders())
            folder.apply();
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
}
