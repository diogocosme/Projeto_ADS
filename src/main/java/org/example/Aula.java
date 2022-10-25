package org.example;

import java.util.Date;

public class Aula extends Evento{

	String professor;
	Turno turno;
	String unidade_de_execucao;
	String dia_semana;
	//String curso;
	String hora_inicial;
	String hora_final;
	//int inscritos;
	Sala sala;
	String caracteristica;


	public Aula(Date data, Date data_final, int numero_de_alunos, String[] cursos, String unidade, String hora_inicio, String hora_fim) {
		super(data, data_final, numero_de_alunos, cursos, unidade, hora_inicio, hora_fim);
	}
}
