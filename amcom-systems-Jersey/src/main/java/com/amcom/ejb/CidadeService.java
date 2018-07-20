package com.amcom.ejb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.amcom.model.Cidade;
import com.amcom.model.Estado;

/**
 * Serviços de 'Cidade', padrão MVC.
 *
 * @author Jose RF Junior
 *
 */
@Stateless
public class CidadeService implements Service {
    private static final Logger LOGGER = Logger.getLogger(CidadeService.class.getName());

    @PersistenceContext
    private EntityManager em;

    /**
     * Retorna uma lista de capitais por ordem alfabética de nome.
     * 
     * @return lista de cidades que são capitais.
     */
    public List<Cidade> listaCapitais() {
        TypedQuery<Cidade> consulta = em.createQuery("from Cidade where capital is not "
                + "null and capital <> '' order by nome", Cidade.class);
        if (consulta.getResultList().isEmpty())
            return new ArrayList<>();
        return consulta.getResultList();
    }

    /**
     * Retorna as duas cidades mais distantes entre sí.
     * 
     * @return lista contendo as duas cidades mais distantes.
     */
    public List<Cidade> cidadeMaisDistante(String idCidade) {
        Cidade cidadeReferencia = buscaCidade(new BigInteger(idCidade));
        final String formulaHaversine = "(6371 * acos(cos(radians(:latitude)) * cos(radians(c.latitude)) "
                + "* cos(radians(c.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(c.latitude))))";

        StringBuilder consultaStr = new StringBuilder();
        consultaStr.append("select c.idIbge, c.nome, " + formulaHaversine + " as distancia from Cidade c "
                + "where " + formulaHaversine + " > 2000 order by distancia desc");
        TypedQuery<Object[]> consulta = em.createQuery(consultaStr.toString(), Object[].class);
        consulta.setParameter("latitude", cidadeReferencia.getLatitude());
        consulta.setParameter("longitude", cidadeReferencia.getLongitude());
        consulta.setMaxResults(1);
        List<Object[]> objCidadeDistante = consulta.getResultList();
        BigInteger idCidadeDistante = null;
        for (Object[] o : objCidadeDistante) {
            idCidadeDistante = (BigInteger) o[0];
        }
        List<Cidade> listaCidades = new ArrayList<>();
        listaCidades.add(cidadeReferencia);
        listaCidades.add(buscaCidade(idCidadeDistante));
        return listaCidades;
    }

    /**
     * Retorna o nome dos estados que contém o maior e o menor número de cidades, e o número de
     * cidades que os mesmos possuem.
     * 
     * @return Lista de estados com maior e menor número de cidades
     */
    public List<Estado> estadoMaisMenosCidades() {
        List<Estado> listaEstados = new ArrayList<>();
        TypedQuery<Estado> consulta = em.createQuery("select new hugo.eustaquio.amcom.model.Estado(c.estado, count(c.estado) as cnt) from Cidade c "
                + "group by c.estado order by cnt", Estado.class);
        consulta.setMaxResults(1);
        listaEstados.add(consulta.getSingleResult());
        consulta = em.createQuery("select new hugo.eustaquio.amcom.model.Estado(c.estado, count(c.estado) as cnt) from Cidade c "
                + "group by c.estado order by cnt desc", Estado.class);
        consulta.setMaxResults(1);
        listaEstados.add(consulta.getSingleResult());
        return listaEstados;
    }

    /**
     * Retorna o nome dos estados que contém o maior e o menor número de cidades, e o número de
     * cidades que os mesmos possuem.
     * 
     * @return Lista de estados com maior e menor número de cidades
     */
    public List<Estado> listaEstados() {
        TypedQuery<Estado> consulta = em.createQuery("select new hugo.eustaquio.amcom.model.Estado(c.estado, count(c.estado) as cnt) "
                + "from Cidade c group by c.estado order by c.estado", Estado.class);
        return consulta.getResultList();
    }

    /**
     * Lista nomes das cidades de um determinado estado.
     * 
     * @return Lista de nomes das cidades do estado escolhido.
     */
    public List<String> listaCidadePorEstado(String estado) {
        TypedQuery<String> consulta = em.createQuery("select nome from Cidade where estado = :estado order by nome", String.class);
        consulta.setParameter("estado", estado);
        return consulta.getResultList();
    }

    /**
     * Retorna uma cidade por seu id ibge.
     *
     * @param idIbge id ibge da cidade
     * @return cidade correspondente ao id procurado.
     */
    public Cidade buscaCidade(BigInteger idIbge) {
        return em.find(Cidade.class, idIbge);
    }

    /**
     * Retorna o número total de cidades cadastradas.
     * 
     * @return quantidade total de cidades.
     */
    public Long qtdTotalCidades() {
        return em.createQuery("select count(c) from Cidade c", Long.class).getSingleResult();
    }
    

    /**
     * Remove uma cidade por seu id ibge.
     *
     * @param idIbge id ibge da cidade a ser removida
     */
    public void deletaCidade(BigInteger idIbge) {
        em.remove(em.find(Cidade.class, idIbge));
    }

    /**
     * Adiciona uma cidade ao banco de dados.
     * 
     * @param cidade a ser adicionada.
     */
    public void adicionarCidade(Cidade cidade) {
        em.persist(cidade);
    }

    /**
     * Busca todas as cidades com uma determinada propriedade igual a um certo valor
     * 
     * @param propriedade
     * @param valor
     * @return Lista resultante da pesquisa realizada.
     */
    public List<Cidade> listaCidadesFiltros(String propriedade, String valor) {
        StringBuilder consultaStr = new StringBuilder();
        consultaStr.append("from Cidade where ");
        consultaStr.append(adicionaNomeParametro(propriedade));
        consultaStr.append(" = :valor order by nome");
        TypedQuery<Cidade> consulta = em.createQuery(consultaStr.toString(), Cidade.class);
        // Parâmetros mudarão de tipo para campos que não sejam String.
        switch (propriedade) {
            case "ibge_id" : consulta.setParameter("valor", new BigInteger(valor));break;
            case "lat" : consulta.setParameter("valor", Float.valueOf(valor));break;
            case "lon" : consulta.setParameter("valor", Float.valueOf(valor));break;
            default:
                consulta.setParameter("valor", valor);
        }
        if (consulta.getResultList().isEmpty())
            return new ArrayList<>();
        return consulta.getResultList();
    }

    /**
     * Retorna a quantidade de registros que contenham determinada propriedade igual
     * a um certo valor.
     * Não foi aproveitada a consulta do método 'listaCidadesFiltros' por velocidade,
     * já que o 'count' é mais rápido que buscar os registros e retornar a quantidade
     * retornada.
     * 
     * @param propriedade campo a ser buscado
     * @param valor valor a ser filtrado
     * @return quantidade de registros correspondente à pesquisa.
     */
    public Long contagemRegistrosPropriedade(String propriedade, String valor) {
        StringBuilder consultaStr = new StringBuilder();
        consultaStr.append("select count(idIbge) from Cidade where ");
        consultaStr.append(adicionaNomeParametro(propriedade));
        consultaStr.append(" = :valor group by ");
        consultaStr.append(adicionaNomeParametro(propriedade));
        TypedQuery<Long> consulta = em.createQuery(consultaStr.toString(), Long.class);
        // Parâmetros mudarão de tipo para campos que não sejam String.
        switch (propriedade) {
            case "ibge_id" : consulta.setParameter("valor", new BigInteger(valor));break;
            case "lat" : consulta.setParameter("valor", Float.valueOf(valor));break;
            case "lon" : consulta.setParameter("valor", Float.valueOf(valor));break;
            default:
                consulta.setParameter("valor", valor);
        }

        return consulta.getSingleResult();
    }
    
    /**
     * Método local para auxiliar a construção da query sem concatenar o valor que o usuário passou pela
     * URL, evitando assim um ataque 'HQL inject'.
     * 
     * @param consultaStr
     * @param propriedade
     * @return consulta
     */
    private String adicionaNomeParametro(String propriedade) {
        switch (propriedade) {
        case "ibge_id" : return "idIbge";
        case "uf" : return "estado";
        case "name" : return "nome";
        case "no_accents" : return "nomeSemAcento";
        case "alternative_names" : return "nomeAlternativo";
        case "capital" : return "capital";
        case "microregion" : return "microregiao";
        case "mesoregion" : return "mesoregiao";
        case "lat" : return "latitude";
        case "lon" : return "longitude";
        default:
            throw new IllegalArgumentException("A propriedade escolhida não foi encontrada.");
        }
    }
}
