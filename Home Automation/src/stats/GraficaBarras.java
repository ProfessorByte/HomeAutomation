package stats;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author 
 */
public class GraficaBarras {
     public void graficarBarras(int[] vectorTotales) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.setValue(vectorTotales[0], "Cantidad de Ingresos al Domicilio", "Accesos Apertura/Cierrre de Puertas");
        
        dataset.setValue(vectorTotales[1], "Cantidad de Encendido/Apagado de Luces", "Encendido/Apagado de Luces");
        
        dataset.setValue(vectorTotales[2], "Cantidad de Luces", "Cantiddad de Luces");
        
        JFreeChart chart = ChartFactory.createBarChart(
                "ESTADÍSTICAS DOMICILIO",
                "Hecho que ocurrió", 
                "Cantidad de Veces", 
                dataset, 
                PlotOrientation.HORIZONTAL,
                true, 
                false, 
                false
        );

        ChartFrame frame = new ChartFrame("Estadísticas de Hechos que se dieron en el Domicilio- Rep.Barras", chart);
        frame.pack();
        frame.setVisible(true);

    }
}
