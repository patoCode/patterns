
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import behaviours.IBehavior;
import behaviours.Moveable;
import elements.Email;
import elements.EmailClient;
import elements.Folder;
import rules.SimpleCondition;

/**
 *
 * @author
 */
public class TestClient {
    
    public static void main(String[] args) throws Exception {

        Folder nicono = new Folder ("!&CONO");
        Folder corani = new Folder("CORANI");
        Folder happy = new Folder("Cumpleañeros");

        EmailClient emailClient = new EmailClient();
        String body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin sit amet vulputate magna. Vestibulum volutpat, mauris sit amet finibus fringilla, est lorem tristique diam, id porta felis tellus a risus. Cras egestas lorem et nulla scelerisque condimentum. Nullam maximus nisi purus, id dignissim justo malesuada ullamcorper. In at sollicitudin felis. Ut arcu dui, tempus non nisi sed, finibus pharetra augue. Vivamus sed aliquam turpis, non rutrum turpis. Sed nulla nisi, laoreet id vestibulum sed, aliquam ut ipsum. Nam gravida nisl suscipit lectus semper semper. Phasellus varius metus tempor quam maximus dapibus. Curabitur eget mattis mauris";
        /*
        *  EMAILS =)
        */
        Email mail1 = new Email("test.test@test.com","Saludos", "miamigo@tu.com",body);
        Email mail2 = new Email("test@test.com","Mensaje 2", "miamigo@tu.com",body);
        Email mail3 = new Email("abc@test.com","Aviso Importante", "miamigo@tu.com",body);
        Email mail4 = new Email("test.test@test.com","Copia de envio", "miamigo@tu.com",body);

        emailClient.addFolder(happy);
        emailClient.addFolder(nicono);
        emailClient.addFolder(corani, nicono);

        IBehavior moveable = new Moveable();
        Email e1 = new Email();
        SimpleCondition c1 = new SimpleCondition(1,"Mover a cumpleaneros","miamigo@tu.com","cc" ,happy, moveable);
        c1.getBody().add(c1);
        SimpleCondition c2 = new SimpleCondition(2,"Mover a Corani", "abc@test.com","from",corani, moveable);
        c2.getBody().add(c2);

        emailClient.addRule(emailClient.getInbox(), c1);
        emailClient.addRule(emailClient.getInbox(), c2);

        emailClient.receive(mail1);
        emailClient.receive(mail2);
        emailClient.receive(mail3);
        emailClient.receive(mail4);

        emailClient.print();
    }
}
