package just.education.security_messaging_app.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SignInController {

    public SignInController() {
    }


    @RequestMapping(method = RequestMethod.GET, value = {"/signin"}, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView signIn() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin_page.html");

        return modelAndView;
    }
}