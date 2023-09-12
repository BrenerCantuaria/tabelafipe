package br.com.spring.tabelafipe.service;

import java.util.List;

public interface ConverteDadosApi {
    <T> T obterDados(String json, Class<T> classe);
    <T> List<T> obterLista(String json, Class<T> classe);
}
