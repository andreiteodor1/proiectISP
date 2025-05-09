package manager;

import model.Client;

public class NotificationService {
    public void notificaClient(Client c, String mesaj) {
        System.out.printf("Notificare către %s: %s%n",
                c.getMail(), mesaj);
    }
}