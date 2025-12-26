import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;
import java.awt.Dimension;

public class Main {

    public static void main(String[] args) {

        // Criando os dados do gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(12, "Vendas", "Janeiro");
        dataset.addValue(18, "Vendas", "Fevereiro");
        dataset.addValue(9,  "Vendas", "Março");
        dataset.addValue(22, "Vendas", "Abril");
        dataset.addValue(16, "Vendas", "Maio");

        // Criando o gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Relatório de Vendas",
                "Mês",
                "Quantidade",
                dataset
        );

        // Painel do gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Janela principal
        JFrame frame = new JFrame("Sistema de Gráficos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
