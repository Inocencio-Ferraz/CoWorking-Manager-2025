package model_;

public abstract class Espaco {
	
		private String id;
		private String nome;
		private Integer capacidade;
		private boolean disponivel;
		private double precoPorHora;
		
		
		public Espaco() {
		}

		public Espaco(String id, String nome, Integer capacidade, boolean disponivel, double precoPorHora) {
			this.id = id;
			this.nome = nome;
			this.capacidade = capacidade;
			this.disponivel = disponivel;
			this.precoPorHora = precoPorHora;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Integer getCapacidade() {
			return capacidade;
		}

		public void setCapacidade(Integer capacidade) {
			this.capacidade = capacidade;
		}

		public boolean isDisponivel() {
			return disponivel;
		}

		public void setDisponivel(boolean disponivel) {
			this.disponivel = disponivel;
		}

		public double getPrecoPorHora() {
			return precoPorHora;
		}

		public void setPrecoPorHora(double precoPorHora) {
			this.precoPorHora = precoPorHora;
		}

		public abstract double calcularCustoReserva(double duracao);

		
		
	}




