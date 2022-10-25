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
    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getCapacidade_normal() {
        return capacidade_normal;
    }

    public int getCapacidade_exame() {
        return capacidade_exame;
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
