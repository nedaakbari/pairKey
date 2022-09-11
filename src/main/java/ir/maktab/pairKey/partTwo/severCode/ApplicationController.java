package ir.maktab.pairKey.partTwo.severCode;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("/getSecretMessage")
    public String getMessage() {
        String message = "TheXCoders";

        EncryptionManager manager = new EncryptionManager();
        manager.initFromStrings();

        try {
            return manager.encrypt(message);

        } catch (Exception ignored) {
        }

        return null;
    }
}