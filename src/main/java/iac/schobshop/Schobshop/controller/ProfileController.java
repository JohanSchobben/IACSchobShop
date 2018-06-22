package iac.schobshop.Schobshop.controller;

import iac.schobshop.Schobshop.model.Account;
import iac.schobshop.Schobshop.service.AccountService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ProfileController {

    private AccountService accountService;

    @RequestMapping(path = "/profile/image/{id}")
    public void getProfileImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Account account = accountService.findAccount(id);

        if (account.getProfilePicture() != null){
            byte[] byteArray = new byte[account.getProfilePicture().length];

            int i = 0;
            for (Byte wrapByte : account.getProfilePicture()){
                byteArray[i++] = wrapByte;
            }
            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            IOUtils.copy(inputStream,response.getOutputStream());
        }

    }
}
