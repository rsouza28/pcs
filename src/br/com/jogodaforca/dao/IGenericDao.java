package br.com.jogodaforca.dao;

import java.util.List;

public interface IGenericDao<E> {

    E salvar(E e);

    List<E> lerArquivo();
    
}
