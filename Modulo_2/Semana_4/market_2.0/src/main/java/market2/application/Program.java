package market2.application;

import market2.connection.JpaConnectionFactory;
import market2.util.Input;
import market2.view.CategoryView;
import market2.view.ClientView;
import market2.view.ProductView;

import javax.persistence.EntityManager;

import static market2.util.Input.*;

public class Program {

    public static void main(String[] args) {

        EntityManager em = new JpaConnectionFactory().getEntityManage();
        int chosenTable = new Input().chooseTable();
        switch (chosenTable) {
            case 0 -> new ProductView(em).chooseOperation();
            case 1 -> new CategoryView(em).chooseOperation();
            case 2 -> new ClientView(em).chooseOperation();
        }

    }
}
