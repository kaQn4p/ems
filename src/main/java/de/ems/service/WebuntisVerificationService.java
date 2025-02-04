package de.ems.service;

import org.bytedream.untis4j.Session;
import org.springframework.stereotype.Service;
import org.bytedream.untis4j.Response;

@Service
public class WebuntisVerificationService {

    public boolean verifyCredentials(String username, String password) {
        try {
            Session session = Session.login(username, password, "hepta.webuntis.com", "cuno-bk1-hagen");
            
            if (session.getInfos() != null) {
                session.logout();
                return true;
            }
        } catch (Exception e) {
            // Fehlerbehandlung
        }
        return false;
    }
}
