package com.example.gta;

public class DataSingelton {

    String idClient;
    private static final DataSingelton instance = new DataSingelton();


    private DataSingelton(){}

    public static DataSingelton getInstance(){
        return instance;
    }

    public String getIdNomenclature(){
        return idClient;
    }
    public void setIdClient(String idClient){
        this.idClient = idClient;

    }
}
