package stats;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class Contador {
    private int prioridad;
    private int valor;
   
    
    public Contador(int prioridadd, int valorr)
    {
        prioridad=prioridadd;
        valor=valorr;
    }
      
    public int getPrioridad()
    {
        return prioridad;
    }
    
    public int getValor()
    {
        return valor;
    }
}
