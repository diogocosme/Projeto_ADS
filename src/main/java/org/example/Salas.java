package org.example;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Salas {

    private class Sala {
        private String edificio;
        private String nome;
        private int capacidade_normal;
        private int capacidade_exame;
        private List<String> caracteristicas;

        public Sala(String edificio,String nome,int capacidade_normal,int capacidade_exame,List<String> caracteristicas) {
            this.edificio=edificio;
            this.nome=nome;
            this.capacidade_normal=capacidade_normal;
            this.capacidade_exame=capacidade_exame;
            this.caracteristicas=caracteristicas;
        }

        @Override
        public String toString() {
            return "Sala{" +
                    "edificio='" + edificio + '\'' +
                    ", nome='" + nome + '\'' +
                    ", capacidade_normal=" + capacidade_normal +
                    ", capacidade_exame=" + capacidade_exame +
                    ", caracteristicas=" + caracteristicas +
                    '}';
        }
    }

    public void readDataLineByLine() {
        String file = "ADS - Caracterizacao das salas.csv";
        List<Sala> salas = new ArrayList<>();
        try {

            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            String[] columns = null;
            boolean first_line=true;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                /*for (String cell : nextRecord) {
                    System.out.print(cell);
                }*/
                if (first_line) {
                     columns= nextRecord[0].split(";");
                     first_line=false;
                }
                else {
                    String[] line = nextRecord[0].split(";");
                    List<String> caracteristicas = new ArrayList<>();

                    int num_caracteristicas = Integer.parseInt(line[4]);
                    for (int i=5; i<line.length; i++) {
                        if (line[i].equalsIgnoreCase("x") && num_caracteristicas > 0) {
                            caracteristicas.add(columns[i]);
                            num_caracteristicas--;
                        }
                    }
                    Sala s = new Sala(line[0],line[1],Integer.parseInt(line[2]),Integer.parseInt(line[3]),caracteristicas);
                    salas.add(s);
                }
                //System.out.print(nextRecord[0]);
                //System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (Sala s: salas) {
            System.out.println(s.toString());
        }
    }
}
