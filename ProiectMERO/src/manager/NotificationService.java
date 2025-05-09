package manager;

import model.Client;

/**
 * Trimite notificări către clienți.
 */
public class NotificationService {
    public void notificaClient(Client c, String mesaj) {
        System.out.printf("Notificare către %s: %s%n",
                c.getMail(), mesaj);
    }
}