package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Metricas {
	
	//é preciso meter as salas das aulas a null se nao for feito de forma automatica!
	
	
	public Metricas() {
		List<Slot> slots = new ArrayList<>();
        for (Slot slot : slots) {
            List<Evento> eventos_slot = slot.eventos;
            for (Evento e : eventos_slot) {
            	quantidade_alocacao_automatica_falhada(e);
            	quantidade_aulas_sobrelotacao(e);
            	quantidade_avaliacoes_sobrelotacao(e);
            }
        }
        //System.out.print("O número de salas que não foram alocadas automáticamente é de:" + nao_foi_possivel_alocacao_automatica);
        mostra();
	}


	//quantidade de salas que não foram alocadas automáticamente
	int nao_foi_possivel_alocacao_automatica = 0;
	
	public void quantidade_alocacao_automatica_falhada(Evento e) {
        if (e instanceof Aula) {
        	Aula aula = (Aula) e;
        	if(aula.getSala() == null){
        		nao_foi_possivel_alocacao_automatica++;
                	}
                }  
		//return nao_foi_possivel_alocacao_automatica;
	}
	
	//Retorna a quantidade de aulas com possibilidade de sobrelotacao de salas e duas listas:
	//Uma lista do número de alunos que estão a mais sempre que existe uma sobrelotação das salas em aulas
	//Uma lista do número de lugares desperdiçados nas salas em aulas
	int aulas_sobrelotadas = 0;
	List<Integer> quantidade_alunos_sobrelotados_por_aulas = new ArrayList<Integer>();
	List<Integer> quantidade_alunos_desperdicados_por_aulas = new ArrayList<Integer>();
	
	public void quantidade_aulas_sobrelotacao(Evento e) {
		if (e instanceof Aula) {
        	Aula aula = (Aula) e;
        	Sala sala = aula.getSala();
        	if(sala != null){
            	if(e.getNumero_de_alunos() > sala.getCapacidade_normal()) {
            		aulas_sobrelotadas++;
            		quantidade_alunos_sobrelotados_por_aulas.add((e.getNumero_de_alunos() - sala.getCapacidade_normal()));
            	}
            	else {
            		quantidade_alunos_desperdicados_por_aulas.add((sala.getCapacidade_normal() - e.getNumero_de_alunos()));
            	}
        	}
		}
	}
	
	//quantidade de alunos com sobrelotação de aulas
	public int quantidade_alunos_sobrelotacao_aulas(){
		return quantidade_alunos_sobrelotados_por_aulas.stream().mapToInt(Integer::intValue).sum();
	}
	
	//quantidade_alunos_desperdicados_aulas, lugares vazios nas aulas
	public int quantidade_alunos_desperdicados_aulas(){
		return quantidade_alunos_desperdicados_por_aulas.stream().mapToInt(Integer::intValue).sum();
	}
	
	//Retorna a quantidade de avaliações com possibilidade de sobrelotacao de salas e duas listas:
	//Uma lista do número de alunos que estão a mais sempre que existe uma sobrelotação das salas em avaliações
	//Uma lista do número de lugares desperdiçados nas salas em avaliações
	int avaliacoes_sobrelotadas = 0;
	List<Integer> quantidade_alunos_sobrelotados_por_avaliacoes = new ArrayList<Integer>();
	List<Integer> quantidade_alunos_desperdicados_por_avaliacoes = new ArrayList<Integer>();
	
	public void quantidade_avaliacoes_sobrelotacao(Evento e) {
		int capacidade_maxima_salas_avaliacao = 0;
		if (e instanceof Avaliacao){
         	Avaliacao avaliacao = (Avaliacao) e;
         	List<Sala> sala1 = avaliacao.getSalas();
         	if(sala1 != null){
         		for(Sala a : sala1) {
         			capacidade_maxima_salas_avaliacao = capacidade_maxima_salas_avaliacao + a.getCapacidade_exame();
             	}
         		if(e.getNumero_de_alunos() > capacidade_maxima_salas_avaliacao) {
         			avaliacoes_sobrelotadas++;
         			quantidade_alunos_sobrelotados_por_avaliacoes.add((e.getNumero_de_alunos() - capacidade_maxima_salas_avaliacao));
         		}
            	else {
            		quantidade_alunos_desperdicados_por_avaliacoes.add((capacidade_maxima_salas_avaliacao - e.getNumero_de_alunos()));
            	}
         	}
		}
	}
	
	//quantidade de alunos com sobrelotação de avaliações
	public int quantidade_alunos_sobrelotacao_avaliacoes(){
		return quantidade_alunos_sobrelotados_por_avaliacoes.stream().mapToInt(Integer::intValue).sum();
	}
	
	//quantidade_alunos_desperdicados_avaliacoes, lugares vazios nas aulas
	public int quantidade_alunos_desperdicados_avaliacoes(){
		return quantidade_alunos_desperdicados_por_avaliacoes.stream().mapToInt(Integer::intValue).sum();
	}
	
	//quantidade de alunos com problemas de sobrelotação (aulas + avaliações)
	public int quantidade_alunos_sobrelotacao_total(){
		return quantidade_alunos_sobrelotados_por_aulas.stream().mapToInt(Integer::intValue).sum() + quantidade_alunos_sobrelotados_por_avaliacoes.stream().mapToInt(Integer::intValue).sum();
	}
	
	//quantidade de vezes que as características das salas são desperdiçadas, uma sala só é considerada desperdiçada 
	//se tiver várias características e estiver a ser usada como uma sala normal.
	int quantidade_vezes_caracteristicas_desperdiçadas = 0;
	int quantidade_caracteristicas_desperdiçadas = 0;
	int aula_mal_atribuida = 0;
	 //está mal pq eu não sei qual as caracteristicas que sao necessarias para ser considfrerado desperdicio, por exemplo, uma aula de mestrado tambem pode ser auditorio é desperdicio ?
	//lista de características pedida
	List<String> caracteristicas_pedidas = Arrays.asList("Anfiteatro aulas", "Arq 1","Arq 2","Arq 3","Arq 4","Arq 5","Arq 6","Arq 9","Lab ISTA", "Laboratório de Informática", "Não necessita de sala", "Sala Aulas Mestrado", "Sala Aulas Mestrado Plus", "Sala NNE");
	
	public void quantidade_caracteristicas_desperdicadas(Evento e) {
		if (e instanceof Aula) {
        	Aula aula = (Aula) e;
        	Sala sala = aula.getSala();
        	if(sala != null){
            	List<String> caracteristicas_list = sala.getCaracteristicas();
            	String caracteristica_aula = aula.getCaracteristica();
            	for(String caracteristica : caracteristicas_list) { // se não for igual a outra sala e a caracteristica necessaria for normal faz break
            		if(!(caracteristica.equals(caracteristica_aula))) {
            			aula_mal_atribuida++;
            		}
            		if(caracteristicas_pedidas.contains(caracteristica)) {
            			if(caracteristica_aula.equals("Sala de Aulas normal")) {
            				quantidade_vezes_caracteristicas_desperdiçadas = 0;
            				quantidade_caracteristicas_desperdiçadas = quantidade_caracteristicas_desperdiçadas + caracteristicas_list.size() - 1;
            			}
            		}
            	}
        	}
		}
	}
	
	
	//Isto vai ver as mudanças de sala e de edificio de uma determinada turma para ver todas as turmas basta meter isto a percorrer a lista de todas as turmas.
	//mudar as aulas numa aula de 3 horas da mesma disciplina
	int mudanca_edificio = 0;
	int mudanca_sala = 0;
	
	public void mudanca_sala(String turma) {
		List<Slot> slots = new ArrayList<>();
		List<Evento> slots_turma = new ArrayList<>();
		for (Slot slot : slots) {
           List<Evento> eventos_slot = slot.eventos;
           for (Evento e : eventos_slot) {
        	   String[] cursos = e.getCursos();
        	   for(String curso : cursos) {
        		   if(turma.equals(curso)) {
        			   slots_turma.add(e);
        		   }
        	   }
           }
        }
		for (Evento e1 : slots_turma) {
			for (Evento e2 : slots_turma) {
				if(e1.getData().equals(e2.getData())) {
					if(e1.getHora_fim().equals(e2.getHora_inicio())) {
						if (e1 instanceof Aula && e2 instanceof Aula) {
				        	Aula aula1 = (Aula) e1;
				        	Aula aula2 = (Aula) e1;
				        	Sala sala1 = aula1.getSala();
				        	Sala sala2 = aula2.getSala();
				        	if(!(sala1.getEdificio().equals(sala2.getEdificio()))) {
				        		mudanca_edificio++;
				        	}
				        	if(aula1.unidade_de_execucao.equals(aula2.unidade_de_execucao)) {
				        		if(!(sala1.getNome().equals(sala2.getNome()))) {
				        			mudanca_sala++;
				        		}
				        	}
						}	
					}
				}
			}
		}
	}
	
	//Corre todas as turmas e faz 2 listas com os valores de todas as turmas. Uma com todas as mudanças de sala por cada turma e outra com todas as mudanças de edificio por cada turma.
	
	public void mudanca_sala_todas() {
		List<String> turmas = new ArrayList<>();
		List<Integer> mudanca_edificio_list = new ArrayList<>();
		List<Integer> mudanca_sala_list = new ArrayList<>();
		for (String turma : turmas) {
			mudanca_sala(turma);
			mudanca_edificio_list.add(mudanca_edificio);
			mudanca_sala_list.add(mudanca_sala);
			mudanca_edificio = 0;
			mudanca_sala = 0;
		}
		System.out.print("o numero de mudanca de edificios de todas as turmas é de: " + mudanca_edificio_list.stream().mapToInt(Integer::intValue).sum());
		System.out.print("o numero de mudanca de salas de todas as turmas é de:  " + mudanca_sala_list.stream().mapToInt(Integer::intValue).sum());
	}
	

	public void mostra() {

		System.out.print("o numero de salas de aulas sobrelotadas é de: " + aulas_sobrelotadas);

	    System.out.print("o numero de salas de exames sobrelotados é de: " + avaliacoes_sobrelotadas);

	    System.out.print("os estudantes afetados por esta sobrelotação de salas de aulas é de: " + quantidade_alunos_sobrelotacao_aulas());

	    System.out.print("os estudantes afetados por esta sobrelotação de salas de exames é de: " + quantidade_alunos_sobrelotacao_avaliacoes());

	    System.out.print("O número de lugares desperdiçados nas aulas é de: " + quantidade_alunos_desperdicados_aulas());

	    System.out.print("O número de lugares desperdiçados nas avaliações é de: " + quantidade_alunos_desperdicados_avaliacoes());

	    System.out.print("O numero de salas que tiveram caracteristicas desperdiçadas foi de:" + quantidade_vezes_caracteristicas_desperdiçadas);
	    
	    System.out.print("O numero de caracteristicas em potêncial desperdiçadas foi de:" + quantidade_caracteristicas_desperdiçadas);
	    
	    System.out.print("O numero de salas que tiveram caracteristicas desperdiçadas foi de:" + quantidade_vezes_caracteristicas_desperdiçadas);
	    
	    System.out.print("O numero de caracteristicas em potêncial desperdiçadas foi de:" + quantidade_caracteristicas_desperdiçadas);
	}
	
}


