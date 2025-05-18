package manager;

import model.*;

public class SubscriptionService {
    public void consultareAbonamente() {
        for (Abonament a : Abonament.values()) {
            System.out.println(a);
        }
    }

    public void alegeAbonament(Client c, Abonament a) {
        c.setAbonament(a);
    }

    public void genereazaAbonament(Client c) {
        System.out.printf("Abonament %s generat pentru %s %s%n",
                c.getAbonament(), c.getNume(), c.getPrenume());
    }
}