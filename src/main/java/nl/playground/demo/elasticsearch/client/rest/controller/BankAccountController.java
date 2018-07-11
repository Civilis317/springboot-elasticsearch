package nl.playground.demo.elasticsearch.client.rest.controller;

import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import nl.playground.demo.elasticsearch.client.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankAccountController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    private final StorageService storageService;

    public BankAccountController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<BankAccount> getAllBankAccounts() {
        return storageService.getAllBankAccounts();
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody
    BankAccount getBankAccount(@PathVariable(name = "id") String id) {
        return storageService.getBankAccount(id);
    }

    @PostMapping(value = "/create")
    public @ResponseBody
    BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return storageService.save(bankAccount);
    }
}
