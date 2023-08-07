package com.company;

public class Id {
    String id, tipo_id, tipo_valor;
    int scope, valorInt;
    double valorDouble;

    public Id(String _id,String _tipo_id,int _scope){
        this.id=_id;
        this.tipo_id=_tipo_id;
        this.scope=_scope;
    }
    public void setValorInt(int _valor,String _tipo_valor){
        this.valorInt=_valor;
        this.tipo_valor=_tipo_valor;
    }
    public void setValorDouble(double _valor,String _tipo_valor){
        this.valorDouble=_valor;
        this.tipo_valor=_tipo_valor;
    }
}