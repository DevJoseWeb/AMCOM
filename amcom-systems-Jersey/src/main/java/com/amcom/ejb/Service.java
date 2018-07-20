package com.amcom.ejb;

import java.math.BigInteger;
import java.util.List;

import com.amcom.model.Cidade;
import com.amcom.model.Estado;

/**
 * Interface a ser implementada para o serviço de cidade ser injetado de acordo com o
 * padrão CDI aplicável em servlets (no caso o jersey servlet).
 *
 * @author Jose RF Junior
 *
 */
public interface Service {
    List<Cidade> listaCapitais();
    List<Estado> estadoMaisMenosCidades();
    Cidade buscaCidade(BigInteger idIbge);
    List<Estado> listaEstados();
    List<String> listaCidadePorEstado(String estado);
    Long qtdTotalCidades();
    void deletaCidade(BigInteger idIbge);
    List<Cidade> cidadeMaisDistante(String idCidade);
    void adicionarCidade(Cidade cidade);
    List<Cidade> listaCidadesFiltros(String propriedade, String valor);
    Long contagemRegistrosPropriedade(String propriedade, String valor);
}