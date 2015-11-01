/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marine_traffic;

import java.util.ArrayList;

/**
 *
 * @author IIIpaM
 */
public class Marine_traffic
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        db.getInstance();
        ArrayList<Marine> marines=db.getMarines();
        
        int size = marines.size();
        for (int i=0;i<size;i++)
        {
            System.out.println(marines.get(i));
        }
        
    }

}
