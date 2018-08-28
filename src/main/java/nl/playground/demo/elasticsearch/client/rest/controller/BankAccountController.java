package nl.playground.demo.elasticsearch.client.rest.controller;

import nl.playground.demo.elasticsearch.client.database.DatabaseStorageService;
import nl.playground.demo.elasticsearch.client.elasticsearch.ElasticsearchStorageService;
import nl.playground.demo.elasticsearch.client.rest.model.Address;
import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;
import nl.playground.demo.elasticsearch.client.rest.model.BaseTrademark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankAccountController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    private static final String STATE_OK = "OK";
    private static final Double BALANCE_10K = Double.valueOf("10000");

    private final ElasticsearchStorageService elasticsearchStorageService;
    private final DatabaseStorageService databaseStorageService;

    public BankAccountController(ElasticsearchStorageService elasticsearchStorageService, DatabaseStorageService databaseStorageService) {
        this.elasticsearchStorageService = elasticsearchStorageService;
        this.databaseStorageService = databaseStorageService;
    }

    @GetMapping(value = "/list")
    public @ResponseBody
    List<BankAccount> getAllBankAccounts() {
        return elasticsearchStorageService.getAllBankAccounts();
    }

    @GetMapping(value = "/get/{id}")
    public @ResponseBody
    BankAccount getBankAccount(@PathVariable(name = "id") String id) {
        return elasticsearchStorageService.getBankAccount(id);
    }

    @PostMapping(value = "/create")
    public @ResponseBody
    BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return elasticsearchStorageService.save(bankAccount);
    }

    @GetMapping(value = "/index/{name}")
    public @ResponseBody
    boolean createIndex(@PathVariable(name = "name") String name) {

        elasticsearchStorageService.createIndex(name);
        return true;
    }

    @GetMapping(value = "/temp/copy")
    public @ResponseBody
    boolean copyIndexToDatabase() {
        elasticsearchStorageService.getAllBankAccounts().stream().forEach(b -> {
            databaseStorageService.save(b);
        });
        return true;
    }

    @GetMapping(value = "/elasticsearch/refresh")
    public @ResponseBody
    Iterable<BankAccount> refresh() {

        // 15-18 secs:
//        databaseStorageService.getAllBankAccounts().stream().forEach(b -> elasticsearchStorageService.save(b));
//        return elasticsearchStorageService.getAllBankAccounts();

        // 180 ms:
        return elasticsearchStorageService.saveAll(databaseStorageService.streamAllBankAccounts());
//        return elasticsearchStorageService.saveAll(databaseStorageService.streamSelected(STATE_OK, BALANCE_10K));
    }

    @GetMapping(value = "/elasticsearch/index/create")
    public @ResponseBody
    List<BankAccount> reCreateIndex() {
        elasticsearchStorageService.deleteIndex("bank");
        elasticsearchStorageService.createIndex("bank");
        return elasticsearchStorageService.getAllBankAccounts();
    }

    @GetMapping(value = "/elasticsearch/gender/{gender}")
    public @ResponseBody
    Iterable<BankAccount> getAllMalesRegistered(@PathVariable(name = "gender") String gender) {
        if ("M".equals(gender))
            return elasticsearchStorageService.getAllMales();
        else if ("F".equals(gender))
            return elasticsearchStorageService.getAllFemales();

        return elasticsearchStorageService.getAllBankAccounts();
    }

    @GetMapping(value = "/elasticsearch/target")
    public @ResponseBody
    Iterable<BankAccount> getRichYoungFemales() {
        return elasticsearchStorageService.getRichYoungFemales();
    }

    @GetMapping(value = "/elasticsearch/address/{address}")
    public @ResponseBody
    Iterable<BankAccount> findByAddress(@PathVariable(name = "address") String address) {
        return elasticsearchStorageService.findByAddress(address);
    }

    @GetMapping(value = "/elasticsearch/create-tm-index")
    public @ResponseBody Iterable<BaseTrademark> createTrademarkIndex() {
        return elasticsearchStorageService.saveTrademarks(generateTrademarks());
    }

    private List<BaseTrademark> generateTrademarks() {
        List<BaseTrademark> trademarkList = new ArrayList<>(16);

        BaseTrademark btm = new BaseTrademark();
        btm.setRegistrationNumber("6096");
        btm.setFilingDateTime(new Date());
        btm.setExpirationDate(new Date());

        Address holder = new Address();
        holder.setName("Surinaamse Brouwerij N.V. ");
        holder.setStreet("Brouwerijweg");
        holder.setNumber("1");
        holder.setCity("Paramaribo");
        holder.setCountry("Suriname");
        btm.setHolder(holder);

        Address representative = new Address();
        representative.setName("G.B. Steward VanEps Kunneman VanDoorne");
        representative.setStreet("Harbour View Office Complex B unit 15, Sparrow Road 4, P.O. Box 494");
        representative.setNumber("15");
        representative.setCity("Philipsburg");
        representative.setCountry("Sint Maarten");
        representative.setWebsite("http://www.ekvandoorne.com");
        btm.setRepresentative(representative);

        btm.setPublicationDate(new Date());
        btm.setFilingDateRenewal(new Date());
        btm.setFilingDateRenewal(new Date());
        btm.setStatus("Trademark registered");

        trademarkList.add(btm);

        return trademarkList;
    }

}
