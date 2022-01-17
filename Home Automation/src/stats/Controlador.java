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
public class Controlador {
    public static void main(String[] args) {
        /**En este bloque simulo como si el modulo de simulacion/control 
        me estuviera mandando datos de sus contadores*/
        ModeloGraficador grafica = new ModeloGraficador();
        Contador cont1 = new Contador(1,12);
        enviarDatoAGraficador(cont1);
        Contador cont2 = new Contador(1,12);
        enviarDatoAGraficador(cont2);
        Contador cont3 = new Contador(2,33);
        enviarDatoAGraficador(cont3);
        Contador cont4 = new Contador(3,61);
        enviarDatoAGraficador(cont4);
        grafica.clasificarAgregar(cont1);
        grafica.clasificarAgregar(cont2);
        grafica.clasificarAgregar(cont3);
        grafica.clasificarAgregar(cont4);
        GraficaBarras graf = new GraficaBarras();
        
        
        graf.graficarBarras(grafica.calcular());

        /**En este bloque simulo como si el modulo de simulacion/control 
        me estuviera mandando datos de sus contadores para la graficación de Tortas*/
        grafica.clasificarAgregar(cont1);
        grafica.clasificarAgregar(cont2);
        grafica.clasificarAgregar(cont3);
        grafica.clasificarAgregar(cont4);
        GraficaTorta graf2 = new GraficaTorta();
        graf2.graficarTorta(grafica.calcular());
        
        /**En este bloque simulo como si el modulo de simulacion/control 
        me estuviera mandando datos de sus contadores para la graficación de Lineas
        grafica.clasificarAgregar(cont1);
        grafica.clasificarAgregar(cont2);
        grafica.clasificarAgregar(cont3);
        grafica.clasificarAgregar(cont4);
        GraficaTorta graf2 = new GraficaTorta();
        graf2.graficarTorta(grafica.calcular());*/
        
        
    }
    
    public static void enviarDatoAGraficador(Contador cont){
            ModeloGraficador grafica = new ModeloGraficador();
            grafica.clasificarAgregar(cont);
    }
}
