package sexta13;

import javax.swing.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static util.DataHandler.*;
public class FridayTheThirteen {

    public static void sextaFeiraTreze () {
        LocalDate data = setData("Insira uma data",LocalDate.MIN,LocalDate.MAX);
        String mensagemResposta = data.getDayOfWeek().getValue()==DayOfWeek.FRIDAY.getValue()&&
                                  data.getDayOfMonth()==13 ? formatar(data)+" é sexta-feira, 13" :
                                  formatar(data)+" não é sexta-feita, 13.";
        JOptionPane.showMessageDialog(null,mensagemResposta);
    }

    public static void main(String[] args) {
        FridayTheThirteen.sextaFeiraTreze();
    }
}
