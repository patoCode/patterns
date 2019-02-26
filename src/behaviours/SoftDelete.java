/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviours;

/**
 *
 * @author Favio
 */
public class SoftDelete implements IBehavior{

    @Override
    public void apply() {
        System.out.println("Soft delete");
    }
    
}
