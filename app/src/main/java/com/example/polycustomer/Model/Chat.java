package com.example.polycustomer.Model;

import java.util.ArrayList;

public class Chat {
    private String tokenCustomer, tokenUser;
    private ArrayList<String> listMesCustomer,listMesUser;

    public Chat(String tokenCustomer, String tokenUser, ArrayList<String> listMesCustomer, ArrayList<String> listMesUser) {
        this.tokenCustomer = tokenCustomer;
        this.tokenUser = tokenUser;
        this.listMesCustomer = listMesCustomer;
        this.listMesUser = listMesUser;
    }

    public Chat() {
    }

    public String getTokenCustomer() {
        return tokenCustomer;
    }

    public void setTokenCustomer(String tokenCustomer) {
        this.tokenCustomer = tokenCustomer;
    }

    public String getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(String tokenUser) {
        this.tokenUser = tokenUser;
    }

    public ArrayList<String> getListMesCustomer() {
        return listMesCustomer;
    }

    public void setListMesCustomer(ArrayList<String> listMesCustomer) {
        this.listMesCustomer = listMesCustomer;
    }

    public ArrayList<String> getListMesUser() {
        return listMesUser;
    }

    public void setListMesUser(ArrayList<String> listMesUser) {
        this.listMesUser = listMesUser;
    }
}
