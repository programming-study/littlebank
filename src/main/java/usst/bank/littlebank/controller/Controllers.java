package usst.bank.littlebank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import usst.cqk.server.Client;

@Controller
public class Controllers {


    @GetMapping(path = "/checkRest/{cardId}")
    public @ResponseBody
    String checkRest(@PathVariable String cardId) throws Exception {
        Client client = new Client("checkRest", new Class[]{String.class}, new Object[]{cardId});
        String result = client.invoke();
        return "{\"message\":\"" + result + "\"}";
    }

    @GetMapping(path = "/deposit/{cardId}/{money}")
    public @ResponseBody
    String deposit(@PathVariable String cardId,@PathVariable double money) throws Exception {
        Client client = new Client("deposit", new Class[]{String.class, double.class},
                new Object[]{cardId,money});
        String result = client.invoke();
        return "{\"message\":\"" + result + "\"}";
    }

    @GetMapping(path = "/withdraw/{cardId}/{money}")
    public @ResponseBody
    String withdraw(@PathVariable String cardId,@PathVariable double money) throws Exception {
        Client client = new Client("withdraw", new Class[]{String.class, double.class},
                new Object[]{cardId,money});
        String result = client.invoke();
        return "{\"message\":\"" + result + "\"}";
    }

    @GetMapping(path = "/transfer/{cardId}/{toCardId}/{money}")
    public @ResponseBody
    String transfer(@PathVariable String cardId, @PathVariable String toCardId, @PathVariable double money) throws Exception {
        Client client = new Client("transfer", new Class[]{String.class, String.class, double.class},
                new Object[]{cardId, toCardId, money});
        String result = client.invoke();
        return "{\"message\":\"" + result + "\"}";
    }

    @GetMapping(path = "/loginIn/{cardId}/{password}")
    public @ResponseBody
    String loginIn(@PathVariable String cardId, @PathVariable String password) throws Exception {
        Client client = new Client("loginIn", new Class[]{String.class, String.class},
                new Object[]{cardId, password});
        String result = client.invoke();
        return "{\"message\":\"" + result + "\"}";
    }

    @GetMapping(path = "/register/{cardId}/{password}")
    public @ResponseBody
    String register(@PathVariable String cardId, @PathVariable String password) throws Exception {
        Client client = new Client("register", new Class[]{String.class, String.class},
                new Object[]{cardId, password});
        String result = client.invoke();
        return "{\"message\":\"" + result + "\"}";
    }
}
