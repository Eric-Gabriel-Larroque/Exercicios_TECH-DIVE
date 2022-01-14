package ex_01;
import java.lang.management.ManagementFactory;

public class PrintarRAM_E_SO {

    public static void main(String[] args) {
        final String NOME_SO = System.getProperty("os.name");
        long RAM_TOTAL = ((com.sun.management.OperatingSystemMXBean)
                ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        long RAM_LIVRE = ((com.sun.management.OperatingSystemMXBean)
                ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
        long RAM_ALOCADA = RAM_TOTAL - RAM_LIVRE;
        System.out.printf("Nome do Sistema Operacional: %s\nRAM total: %d\nRAM livre: %d\nRAM alocada: %d",
                NOME_SO,RAM_TOTAL,RAM_LIVRE,RAM_ALOCADA);
    }

}
