package org.example;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final String file_caracterizacao_das_salas = "ADS - Caracterizacao das salas.csv";
    private final String file_horario_1sem = "ADS - Horários 1º sem 2022-23.csv";
    private List<Sala> salas;

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        readFile_caracterizacaoDasSalas();
    }

    private void readFile_caracterizacaoDasSalas() {
        List<Sala> salas = new ArrayList<>();
        try {

            FileReader filereader = new FileReader(file_caracterizacao_das_salas);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            String[] columns = null;
            boolean first_line = true;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                if (first_line) {
                    columns = nextRecord[0].split(";");
                    first_line = false;
                } else {
                    String[] line = nextRecord[0].split(";");
                    List<String> caracteristicas = new ArrayList<>();

                    int num_caracteristicas = Integer.parseInt(line[4]);
                    for (int i = 5; i < line.length; i++) {
                        if (line[i].equalsIgnoreCase("x") && num_caracteristicas > 0) {
                            caracteristicas.add(columns[i]);
                            num_caracteristicas--;
                        }
                    }
                    Sala s = new Sala(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), caracteristicas);
                    salas.add(s);
                }
            }
            for (Sala s : salas) {
                System.out.println(s.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionar_espacos_a_eventos() {
        List<Slot> slots = new ArrayList<>();

        for (Slot slot : slots) {
            List<Evento> aulas_slot = slot.eventos;
            for (Evento e : aulas_slot) {
                /*if (aula.sala == null) {
                    //Alterar para getter
                    aula.sala = encontrar_sala(aula,slot.salas_livres);
                }*/
            }
        }
    }

    private List<Sala> salas_match_caracteristica(String caracteristica, List<Sala> salas_livres) {
        List<Sala> salas = new ArrayList<>();
        for (Sala s : salas_livres) {
            if (s.getCaracteristicas().contains(caracteristica)) salas.add(s);
        }
        return salas;
    }

    //Uma sala tem um evento. Não uma aula. Um evento pode ser exame ou aula
    private Sala encontrar_sala(Aula aula, List<Sala> salas_livres) {
        String caracteristica_aula = aula.caracteristica;
        List<Sala> salas_livres_com_caracteristica = salas_match_caracteristica(caracteristica_aula, salas_livres);

        if (!salas_livres_com_caracteristica.isEmpty()) {
            int diferenca = 0; //diferenca entre capacidade da sala e numero de inscritos
            Sala sala_to_return = null;

            for (Sala s : salas_livres_com_caracteristica) {
                int temp_dif = s.getCapacidade_normal() - aula.turno.inscritos;
                if (temp_dif >= 0 && temp_dif < diferenca) {
                    diferenca = temp_dif;
                    sala_to_return = s;
                }
            }
            if (sala_to_return != null) return sala_to_return;
            else {
                System.out.println("Sobrelotação");
                resolver_conflito();
            }
        }
        resolver_conflito();
        return null;
    }

    private void resolver_conflito() {

    }
}