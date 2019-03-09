var app = new Vue({
  el: '#app',

  data: {
    mensagem: {},
    headers: [
      { text: 'Placa', sortable: true, value: 'placa' },
      { text: 'Nome do Proprietario', sortable: true, value: 'nomeProprietario' },
      { text: 'Data do Emplacamento', sortable: true, value: 'dataEmplacamento' },
      { text: 'Valor do IPVA', sortable: true, value: 'valorIpva' },
    ],
    veiculos: [],
  },

  mounted() {
    this.carregar();
  },
  
  methods: {
    carregar() {
      this.limparMensagem();
      veiculosService.selecionarTodos()
        .then(veiculos => {
          this.veiculos = veiculos;
        })
        .catch(error => {
          this.veiculos = [];
          this.exibirMensagem('error', 'Erro inesperado.');
        });
    },

    exibirMensagem(tipo, texto) {
      this.mensagem = { tipo, texto, exibir: true };
    },

    limparMensagem() {
      this.mensagem = { tipo: 'info', texto: '', exibir: false };
    },
    
    editar(id) {
    	window.location = 'veiculos-editar.html?id=' + id;
    	},
  }
  
});