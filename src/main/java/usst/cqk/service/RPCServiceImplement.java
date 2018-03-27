package usst.cqk.service;

import usst.bank.littlebank.DAO.AccountDAO;
import usst.bank.littlebank.model.Account;

public class RPCServiceImplement implements RPCService {
    AccountDAO accountDAO = new AccountDAO();

    @Override
    public String getInformation(String s) {
        return "received information:" + s;
    }

    @Override
    public String checkRest(String cardId) {
        Account foundAccount = accountDAO.searchAccountById(cardId);
        if (foundAccount == null) {
            return "no this account";
        } else {
            return String.valueOf(foundAccount.getMoney());
        }
    }

    @Override
    public String deposit(String cardId,double money) {
        Account foundAccount = accountDAO.searchAccountById(cardId);
        if (foundAccount == null) {
            return "no this account";
        } else {
            foundAccount.deposit(money);
            accountDAO.changeAccountMoney(foundAccount.getCardId(),foundAccount.getMoney());
            return "successfully deposit";
        }
    }

    @Override
    public String withdraw(String cardId,double money) {
        Account foundAccount = accountDAO.searchAccountById(cardId);
        if (foundAccount == null) {
            return "no this account";
        } else {
            if (foundAccount.getMoney() < money) {
                return "no enough money";
            } else {
                foundAccount.withdraw(money);
                accountDAO.changeAccountMoney(foundAccount.getCardId(),foundAccount.getMoney());
                return "successfully withdraw";
            }
        }
    }

    @Override
    public String transfer(String cardId,String toCardId, double money) {
        Account fromAccount = accountDAO.searchAccountById(cardId);
        if (fromAccount == null) {
            return "you have no account";
        } else {
            if (fromAccount.getMoney() < money) {
                return "no enough money";
            } else {
                Account toAccount = accountDAO.searchAccountById(toCardId);
                if (toAccount == null) {
                    return "no this to account";
                } else {
                    fromAccount.withdraw(money);
                    toAccount.deposit(money);
                    accountDAO.changeAccountMoney(fromAccount.getCardId(), fromAccount.getMoney());
                    accountDAO.changeAccountMoney(toAccount.getCardId(), toAccount.getMoney());
                    return "successfully transfer";
                }
            }
        }

    }

    @Override
    public String loginIn(String cardId, String password) {
        Account foundAccount = accountDAO.searchAccountById(cardId);
        if (foundAccount == null) {
            return "no this account";
        } else {
            if (!foundAccount.getCardPassword().equals(password)) {
                return "wrong password";
            } else {
                return "successfully login in";
            }
        }
    }

    @Override
    public String register(String cardId, String password) {
        Account foundAccount = accountDAO.searchAccountById(cardId);
        if (foundAccount != null) {
            return "exist account";
        } else {
            Account newAccount = new Account(cardId,password,0);
            accountDAO.inertNewAccount(newAccount);
            return "successfully register";
        }
    }
}
