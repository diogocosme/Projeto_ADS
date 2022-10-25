package org.example;

import java.util.Date;
import java.util.List;

public class Avaliacao extends Evento {
    private int codigo;
    private String tipo;
    private String epoca;
    private String nome;
    private boolean requer_inscricao_previa;
    private String periodo_inscricao;
    private List<Sala> salas;
    private String estado_pedido_sala;
    private int capacidade_salas;


    public Avaliacao(Date data, Date data_final, int numero_de_alunos,
                     String[] cursos, String unidade,
                     String hora_inicio, String hora_fim, String[] line) {
        super(data, data_final, numero_de_alunos, cursos, unidade, hora_inicio, hora_fim);
        this.codigo = Integer.parseInt(line[0]);
        this.tipo = line[3];
        this.epoca = line[4];
        this.nome = line[5];
        if (line[6].equals("Não")) this.requer_inscricao_previa=false;
        else this.requer_inscricao_previa = true;
        this.periodo_inscricao = line[7];
        this.estado_pedido_sala = line[10];
    }


}
