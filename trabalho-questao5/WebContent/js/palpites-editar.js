var router = new VueRouter({ mode: 'history', routes: [] });

var app = new Vue({
  router,

  el: '#app',

  data: {
    mensagem: {},
    palpite: {
      id: null,
      palavraSorteada: null,
      palavraTemp: null,
      numeroTentativas: null,
      letraDigitada: null,
      listaLetrasDigitadas: null,
      status: null,
      palavraTempString: null
    },
    
    	erros: {
    	id: null,
    	palavraSorteada: null,
        palavraTemp: null,
        numeroTentativas: null,
        letraDigitada: null,
        listaLetrasDigitadas: null,
        status: null,
        palavraTempString: null
    }
    
  },
  
  mounted() {
	  this.palpite.id = this.$route.query.id;
	  this.carregar();
	  },
	  
  methods: {
    modoEdicao(){
      return this.palpite.id ? true : false;
    },
    
    tratarErro(error) {
        
        switch (error.response.status) {
        	case 422: // Unprocessable Entity
        		let validacoes = error.response.data;
        		for (let erro in this.erros) {
        			for (let index in validacoes) {
        				if (erro === validacoes[index].propriedade) {
        					this.erros[erro] = validacoes[index].mensagem;
        			}
        		}
        	}
        	this.exibirMensagem('error', 'Verifique erros no formulário!');
        	break;
        	

        
        default:
            this.exibirMensagem('error', 'Erro inesperado.');
        break;
        }
    },
    
    exibirMensagem(tipo, texto) {
        this.mensagem = { tipo, texto, exibir: true };
    },

    limparMensagem() {
      this.mensagem = { tipo: 'info', texto: '', exibir: false };
    },
    
    limparErros() {
    	this.erros = { palavraSorteada: null,palavraTemp: null, numeroTentativas: null, letraDigitada: null,listaLetrasDigitadas: null, status: null, palavraTempString: null };
    	},
    	
    	carregar() {
    		if (this.modoEdicao()) {
    		palpitesService.selecionar(this.palpite.id)
    		.then(palpite => {
    		console.log(palpite);
    		this.palpite = palpite;
    		})
    		.catch(error => {
    		this.tratarErro(error);
    		});
    		}
    		},
    		atualizar() {
    			this.limparErros();
    			this.limparMensagem();
    			palpitesService.atualizar(this.palpite)
    			.then(data => {
    			window.location = 'palpites-listar.html';
    			})
    			.catch(error => {
    			this.tratarErro(error);
    			});
    			
    			},
    			}     
})