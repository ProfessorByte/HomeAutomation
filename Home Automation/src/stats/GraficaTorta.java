package stats;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author 
 */
public class GraficaTorta {
    public void graficarTorta(int[] vectorTotales) {

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        pieDataset.setValue("Accesos: Apertura/Cierrre de Puertas", new Integer(vectorTotales[0]));
        pieDataset.setValue("Encendido/Apagado de Luces", new Integer(vectorTotales[1]));
        pieDataset.setValue("Cantidad de Luces", new Integer(vectorTotales[2]));
        
        
        
        JFreeChart chart = ChartFactory.createPieChart(
                "ESTADÍSTICAS DOMICILIO",
                pieDataset,
                true,
                true,
                false
        );
        
        ChartFrame frame = new ChartFrame("Estadísticas de Hechos que se dieron en el Domicilio- Rep.Grafica Circular", chart);
        frame.pack();
        frame.setVisible(true);

    }
}
