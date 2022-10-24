package org.example;

import java.util.List;


public class Sala {
    private String edificio;
    private String nome;
    private int capacidade_normal;
    private int capacidade_exame;
    private List<String> caracteristicas;

    public Sala(String edificio,String nome,int capacidade_normal,int capacidade_exame,
                List<String> caracteristicas) {
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
