package br.com.spring.tabelafipe.principal;

import br.com.spring.tabelafipe.model.Dados;
import br.com.spring.tabelafipe.model.Modelos;
import br.com.spring.tabelafipe.model.Veiculo;
import br.com.spring.tabelafipe.service.API;
import br.com.spring.tabelafipe.service.TransformaDados;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
        private Scanner scanner = new Scanner(System.in);
        private API reqAPI = new API();
        private String MARCAS  = "/marcas/";

     private TransformaDados transformaDados = new TransformaDados();

    public void Menu() throws IOException {
            String mensagemMenu= """
                                 Digite o número que corresponde a sua pesquisa:
                                 
                                 1 - Motos
                                 2 - Carros
                                 3 - Caminhões
                             
                                 """;
            System.out.println(mensagemMenu);


            Map<Integer,String> opcoes = new HashMap<>();
            opcoes.put(1,"motos");
            opcoes.put(2,"carros");
            opcoes.put(3,"caminhoes");



            var busca = scanner.nextInt();
            var opcaoSelecionada = opcoes.get(busca);
            var json = reqAPI.chamaAPI(ENDERECO + opcaoSelecionada + MARCAS);
            System.out.println(json);

            ObjectMapper mapper = new ObjectMapper();
            var marcas = mapper.readValue(json, new TypeReference<List<Dados>>() {});
                    marcas.stream()
                            .sorted(Comparator.comparing(Dados::codigo))
                            .forEach(System.out::println);


            System.out.println(opcaoSelecionada);
            System.out.println("Digite o código do veículo que você deseja:");
            var codigoMarca = scanner.nextInt();
            String url = ENDERECO + opcaoSelecionada + MARCAS + codigoMarca + "/modelos";
            json = reqAPI.chamaAPI(url);
            System.out.println(json);

            var listaDeModelos = mapper.readValue(json, Modelos.class);
            listaDeModelos.modelos().stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);


            System.out.println("Digite o nome do modelo desejado:");
            var nomeDoModelo = reader.readLine();

            List<Dados>  modeloFiltrado = listaDeModelos.modelos().stream()
                     .filter(m -> m.nome().toLowerCase().contains(nomeDoModelo.toLowerCase()))
                     .collect(Collectors.toList());

            System.out.println("Modelos filtrados:");
            modeloFiltrado.forEach(System.out::println);

            System.out.println("Digite o código do modelo para buscar o valores da avaliação:");
            var codigoModelo = reader.readLine();
            url = url + "/" + codigoModelo + "/anos";
            json = reqAPI.chamaAPI(url);

            List<Dados> anos = transformaDados.obterLista(json, Dados.class);
            List<Veiculo> veiculos = new ArrayList<>();

            for (int i = 0; i < anos.size(); i++) {
                    var urlAnos = url + "/" + anos.get(i).codigo();
                    json = reqAPI.chamaAPI(urlAnos);
                    Veiculo veiculo = mapper.readValue(json,Veiculo.class);
                    veiculos.add(veiculo);
            }

            System.out.println("Todos os veículos: ");
            veiculos.forEach(System.out::println);

//            var marcas =  transformaDados.obterLista(json, Dados.class);
//            marcas.stream()
//                    .sorted(Comparator.comparing(Dados::codigo))
//                    .forEach(System.out::println);
//            System.out.println("Informe o código da marca para consulta");
//
//            var codigoMarca = scanner.nextLine();
//            String url = ENDERECO + busca + MARCAS + codigoMarca + "/modelos";
//            json = reqAPI.chamaAPI(url);
//
//            var modeloLista = transformaDados.obterDados(json, Modelos.class);
//            System.out.println("Modelos dessa marca: ");
//            modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);
    }


}
