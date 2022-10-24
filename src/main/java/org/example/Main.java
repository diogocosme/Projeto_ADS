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
}