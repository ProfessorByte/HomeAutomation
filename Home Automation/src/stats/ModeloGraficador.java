package stats;

import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author 
 */
public class ModeloGraficador {
    //Para esto modulo de simulación tedrá que crear y mandarme 
    //una clase llamada contador que tenga como atributos 
    //el tipo en un String y su valor o contador en int
    Queue<Queue> colaDeColas = new LinkedList(); 
    Queue<Integer> colaPrioridad1 = new PriorityQueue<Integer>();//Numero de Ingresos Puertas que se abren y cierran
    Queue<Integer> colaPrioridad2 = new PriorityQueue<Integer>();//Numero de veces que Ventanas que se abren y cierran
    Queue<Integer> colaPrioridad3 = new PriorityQueue<Integer>();//Numero de luces que se encienden y apagan
    
    private Contador contadorGen;
    private String instruccion = null;
    
    
    public ModeloGraficador()
    {
        //contadorGen = contadorGenn;
        //agregarColaPrioridad();
        colaDeColas.add(colaPrioridad1);
        colaDeColas.add(colaPrioridad2);
        colaDeColas.add(colaPrioridad3);
    }
    
    public void clasificarAgregar(Contador contadorGen)
    {
        if(contadorGen.getPrioridad()==1)
        {
            colaPrioridad1.add(contadorGen.getValor());
        }
        else if(contadorGen.getPrioridad()==2)
        {
            colaPrioridad2.add(contadorGen.getValor());
        }
        else if(contadorGen.getPrioridad()==3)
        {
            colaPrioridad3.add(contadorGen.getValor());
        } 
    }
    
    public int[] calcular()
    {
        int totalDeCola[] = new int[3];
        
        while(!colaPrioridad1.isEmpty())
        {
            totalDeCola[0] = totalDeCola[0]+(int)colaPrioridad1.poll();    
        }
        while(!colaPrioridad2.isEmpty())
        {
            totalDeCola[1] = totalDeCola[1]+(int)colaPrioridad2.poll();    
        }
        while(!colaPrioridad3.isEmpty())
        {
            totalDeCola[2] = totalDeCola[2]+(int)colaPrioridad3.poll();    
        }
        return totalDeCola;
    }
    /*public int calcular(int i)
    {
        int res = 0;
            if(instruccion.equals("sumatoria") && i==1)
            {
                Sumatoria s = new Sumatoria(colaPrioridad1);
                res = s.sumarValoresdeDatos();
            }
            else if(instruccion.equals("sumatoria") && i==2)
            {
                Sumatoria s = new Sumatoria(colaPrioridad1);
                res = s.sumarValoresdeDatos();
            }
            else if(instruccion.equals("sumatoria") && i==3)
            {
                Sumatoria s = new Sumatoria(colaPrioridad1);
                res = s.sumarValoresdeDatos();
            }
        return res;
    }*/
    
    
    public void agregarColaPrioridad()
    {
        colaDeColas.add(colaPrioridad1);
        colaDeColas.add(colaPrioridad2);
        colaDeColas.add(colaPrioridad3);
    }
    


}
