package nl.playground.demo.elasticsearch.client.database.conversion;

import nl.playground.demo.elasticsearch.client.database.entity.BankAccountEntity;
import nl.playground.demo.elasticsearch.client.rest.model.BankAccount;

public class BankAccountConverter {

    public static BankAccountEntity convertBankAccountToEntity(BankAccount bankAccount) {
        BankAccountEntity entity = new BankAccountEntity();
//        entity.setId(bankAccount.getId());

        entity.setAccount(bankAccount.getAccount_number());
        entity.setBalance(bankAccount.getBalance());
        entity.setFirstName(bankAccount.getFirstname());
        entity.setLastName(bankAccount.getLastname());
        entity.setAge(bankAccount.getAge());
        entity.setGender(bankAccount.getGender());
        entity.setAddress(bankAccount.getAddress());
        entity.setEmployer(bankAccount.getEmployer());
        entity.setEmail(bankAccount.getEmail());
        entity.setCity(bankAccount.getCity());
        entity.setState(bankAccount.getState());
        return entity;
    }

    public static BankAccount convertEntityToBankAccount(BankAccountEntity entity) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(String.valueOf(entity.getId()));
        bankAccount.setAccount_number(entity.getAccount());
        bankAccount.setBalance(entity.getBalance());
        bankAccount.setFirstname(entity.getFirstName());
        bankAccount.setLastname(entity.getLastName());
        bankAccount.setAge(entity.getAge());
        bankAccount.setGender(entity.getGender());
        bankAccount.setAddress(entity.getAddress());
        bankAccount.setEmployer(entity.getEmployer());
        bankAccount.setEmail(entity.getEmail());
        bankAccount.setCity(entity.getCity());
        bankAccount.setState(entity.getState());
        return bankAccount;
    }
 }
