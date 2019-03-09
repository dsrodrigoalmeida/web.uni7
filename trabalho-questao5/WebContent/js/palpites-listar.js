var app = new Vue({
  el: '#app',

  data: {
    mensagem: {},
    headers: [
      
      { text: 'Palavra', value: 'palavraTempString'},
      { text: 'Numero de Tentativas', value: 'numeroTentativas' },
      { text: 'Lista de Letras Digitadas', value: 'listaLetrasDigitadas' },
      { text: 'Status',  value:'status'}
      
    ],
    palpites: [],
  },

  mounted() {
    this.carregar();
  },
  
  methods: {
	    carregar() {
	      this.limparMensagem();
	      palpitesService.selecionarTodos()
	        .then(palpites => {
	          this.palpites = palpites;
	        })
	        .catch(error => {
	          this.palpites = [];
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
	    	window.location = 'palpites-editar.html?id=' + id;
	    	},
	  }
	  
	});