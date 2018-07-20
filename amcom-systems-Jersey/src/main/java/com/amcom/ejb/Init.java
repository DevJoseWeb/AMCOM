package com.amcom.ejb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.amcom.model.Cidade;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Classe criada para acomodar as necessidades da inicialização do sistema.
 *
 * @author Jose RF Junior
 *
 */
@Startup
@Singleton
public class Init {
    private static final Logger LOGGER = Logger.getLogger(Init.class.getName());

    @EJB(name = "CidadeService")
    private Service cidadeService;

    /**
     * Ao iniciar o sistema, carregaremos os dados do arquivo 'cidades.csv' no banco
     * de dados.
     */
    @PostConstruct
    public void init() {
        LOGGER.info("iniciando a leitura do arquivo CSV.");
        Reader in = null;

        try {
            in = new FileReader(getClass().getClassLoader().getResource("cidades.csv").getFile());
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            Cidade cidade;
            for (CSVRecord record : records) {
                cidade = new Cidade();
                cidade.setIdIbge(new BigInteger(record.get("ibge_id")));
                cidade.setEstado(record.get("uf"));
                cidade.setNome(record.get("name"));
                cidade.setCapital(record.get("capital"));
                cidade.setLatitude(Float.parseFloat(record.get("lat")));
                cidade.setLongitude(Float.parseFloat(record.get("lon")));
                cidade.setNomeSemAcento(record.get("no_accents"));
                cidade.setNomeAlternativo(record.get("alternative_names"));
                cidade.setMicroregiao(record.get("microregion"));
                cidade.setMesoregiao(record.get("mesoregion"));
                cidadeService.adicionarCidade(cidade);
            }
            LOGGER.info("a leitura do arquivo CSV foi concluída com sucesso!");
        } catch (FileNotFoundException e) {
            LOGGER.severe("Não foi possível fazer a leitura do arquivo CSV. Confira se o mesmo "
                    + "encontra-se no diretório 'resources' do projeto e com o nome 'cidades.csv'.");
            LOGGER.severe(e.getMessage());
        } catch (IOException e) {
            LOGGER.severe("O arquivo 'cidades.csv' não pôde ser aberto. Confira as permissões no seu sistema "
                    + "e configure-as de forma a permitir a leitura do arquivo pelo usuário do sistema operacional "
                    + "que executa este sistema.");
            LOGGER.severe(e.getMessage());
        } catch (NumberFormatException e) {
            LOGGER.severe("Conteúdo inválido no arquivo. Verifique o erro abaixo: ");
            LOGGER.severe(e.getMessage());
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                LOGGER.warning("Problemas ao fechar o arquivo CSV.");
                LOGGER.warning(e.getMessage());
            }
        }
    }
}
