package asm04.dao;

import asm02.Account;
import asm04.file.BinaryFileService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private static final String FILE_PATH = "store"+ File.separator +"accounts.dat";
    public static void save(List<Account> accounts){
        BinaryFileService.writeObject(FILE_PATH,accounts);
    }
    public static List<Account> list(){
        return BinaryFileService.readFile(FILE_PATH);
    }
    public static void update(Account editAccount) {
            List<Account> accounts = list();
            boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
            List<Account> updatedAccounts;
            if (!hasExist) {
                updatedAccounts = new ArrayList<>(accounts);
                updatedAccounts.add(editAccount);
            } else {
                updatedAccounts = new ArrayList<>();
                for (Account account : accounts) {
                    if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
                        updatedAccounts.add(editAccount);
                    } else {
                        updatedAccounts.add(account);
                    }
                }
            }
            save(updatedAccounts);
        }
}
