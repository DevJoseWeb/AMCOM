package com.amcom.rest;

import java.math.BigInteger;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.amcom.ejb.Service;
import com.amcom.model.Cidade;
import com.amcom.model.Estado;

/**
 * Serviço REST para cidades.
 * 
 * @author Jose RF Junior
 *
 */
@Path("/cidades")
public class CidadesRest {
    private static final Logger LOGGER = Logger.getLogger(CidadesRest.class.getName());
    private Service cidadeService;

    public CidadesRest() throws NamingException {
        cidadeService = (Service) (new InitialContext()).lookup("java:global/CidadeService");
    }

    /**
     * Resolução da questão 2.
     * 
     * @return Lista de cidades que são capitais por ordem alfabética.
     */
    @GET
    @Path("/capitais")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidade> getCliente() {
        return cidadeService.listaCapitais();
    }

    /**
     * Resolução da questão 3.
     * 
     * @return Lista contendo os estados com maior e menor número de cidades.
     */
    @GET
    @Path("/estado_maior_menor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> getMaioresMenoresEstados() {
        return cidadeService.estadoMaisMenosCidades();
    }

    /**
     * Resolução da questão 4.
     * 
     * @return Lista contendo os estados e seu número de cidades.
     */
    @GET
    @Path("/estados")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> getEstados() {
        return cidadeService.listaEstados();
    }

    /**
     * Resolução da questão 5.
     * 
     * @param id ibge da cidade procurada
     * @return Cidade procurada.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cidade/{id}")
    public Cidade buscaCidadeId(@PathParam("id") String id) {
        return cidadeService.buscaCidade(new BigInteger(id));
    }

    /**
     * Resolução da questão 6.
     * 
     * @param sigla do estado cujas cidades serão retornadas.
     * @return Lista de cidades para um determinado estado.
     */
    @GET
    @Path("/{estado}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCidadesPorEstado(@PathParam("estado") String estado) {
        return cidadeService.listaCidadePorEstado(estado);
    }

    /**
     * Resolução da questão 7.
     */
    @GET
    @Path("/cadastrar/ibge_id={ibge_id},uf={uf},name={name},capital={capital},lon={lon},lat={lat},"
            + "no_accents={no_accents},alternative_names={alternative_names},microregion={microregion},mesoregion={mesoregion}")
    @Produces(MediaType.APPLICATION_JSON)
    public String cadastrarCidade(
            @PathParam("ibge_id") String idIbge,
            @PathParam("uf") String estado,
            @PathParam("name") String nome,
            @PathParam("capital") String capital,
            @PathParam("lon") String longitude,
            @PathParam("lat") String latitude,
            @PathParam("no_accents") String nomeSemAcento,
            @PathParam("alternative_names") String nomeAlternativo,
            @PathParam("microregion") String microregiao,
            @PathParam("mesoregion") String mesoregiao) {
        Cidade cidade = new Cidade();
        cidade.setIdIbge(new BigInteger(idIbge));
        cidade.setNome(nome);
        cidade.setEstado(estado);
        cidade.setCapital(capital);
        cidade.setLongitude(Float.valueOf(longitude));
        cidade.setLatitude(Float.valueOf(latitude));
        cidade.setNomeSemAcento(nomeSemAcento);
        cidade.setNomeAlternativo(nomeAlternativo);
        cidade.setMicroregiao(microregiao);
        cidade.setMesoregiao(mesoregiao);
        try {
            cidadeService.adicionarCidade(cidade);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return "500 - Houve erro ao cadastrar a cidade desejada. Por favor verifique seus dados.";
        }
        return "200 - Cidade cadastrada com sucesso";
    }

    /**
     * Resolução da questão 8.
     * 
     * @return Lista de cidades para um determinado estado.
     */
    @GET
    @Path("/remove/{idCidade}")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeCidade(@PathParam("idCidade") String idCidade) {
        try {
            cidadeService.deletaCidade(new BigInteger(idCidade));
            return "200 - Cidade com id '" + idCidade + "' removida com sucesso.";
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            return "500 - Não foi possível remover a cidade. Verifique se mesma já não foi removida antes.";
        }
    }

    /**
     * Resolução da questão 9.
     * Retorna uma lista de cidades de acordo com o filtro escolhido.
     * @param propriedade correspondente ao nome da coluna no CSV.
     * @param valor a ser filtrado nos resultados
     * @return lista de cidades.
     */
    @GET
    @Path("/filtro/{propriedade}={valor}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidade> listaCidadesFiltro(@PathParam("propriedade") String propriedade, @PathParam("valor") String valor) {
        return cidadeService.listaCidadesFiltros(propriedade, valor);
    }

    /**
     * Resolução da questão 10.
     * Retorna uma lista de cidades de acordo com o filtro escolhido.
     * @param propriedade correspondente ao nome da coluna no CSV.
     * @param valor a ser filtrado nos resultados
     * @return lista de cidades.
     */
    @GET
    @Path("/quantidade/{propriedade}={valor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Long qtdeCidadesFiltro(@PathParam("propriedade") String propriedade, @PathParam("valor") String valor) {
        return cidadeService.contagemRegistrosPropriedade(propriedade, valor);
    }

    /**
     * Resolução da questão 11.
     * 
     * @return quantidade total de cidades.
     */
    @GET
    @Path("/quantidade")
    @Produces(MediaType.APPLICATION_JSON)
    public Long qtdeCidades() {
        return cidadeService.qtdTotalCidades();
    }

    /**
     * Resolução da questão 12.
     * 
     * @return Busca a cidade mais distante de uma dada cidade.
     */
    @GET
    @Path("/mais_distantes/{idCidadeReferencia}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidade> cidadesMaisDistantes(@PathParam("idCidadeReferencia") String idCidadeReferencia) {
        return cidadeService.cidadeMaisDistante(idCidadeReferencia);
    }
}
