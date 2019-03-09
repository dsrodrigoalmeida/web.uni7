var router = new VueRouter({ mode: 'history', routes: [] });

var app = new Vue({
  router,

  el: '#app',

  data: {
    mensagem: {},
    veiculo: {
      id: null,
      placa: null,
      nomeProprietario: null,
      dataEmplacamento: null,
      valorIPVA: null,
    },
    
    	erros: {
    	id: null,
        placa: null,
        nomeProprietario: null,
        dataEmplacamento: null,
        valorIPVA: null,
    }
    
  },
  
  mounted() {
	  this.veiculo.id = this.$route.query.id;
	  this.carregar();
	  },
	  
  methods: {
    modoEdicao(){
      return this.veiculo.id ? true : false;
    },

    inserir() {
      this.limparMensagem();
      veiculosService.inserir(this.veiculo)
      
        .then(id => {
          this.veiculo.id = id;
          this.exibirMensagem('success', 
            'Veiculo com id = ' + id + ' criado com sucesso.');
        })
        .catch(error => {
          this.tratarErro(error);
        });
    },
    
    tratarErro(error) {
        switch (error.response.status) {
        

        case 404: // Not found
        this.exibirMensagem('error',
        'O registro solicitado não foi encontrado!');
        break;
       

        case 422: // Unprocessable Entity
        let validacoes = error.response.data;
        for (let erro in this.erros) {
        for (let index in validacoes) {
        if (erro === validacoes[index].propriedade){
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
    	this.erros = { placa:null, nomeProprietario:null, dataEmplacamento:null, valorIPVA:null };
    	},
    	
    	carregar() {
    		if (this.modoEdicao()) {
    		veiculosService.selecionar(this.veiculo.id)
    		.then(veiculo => {
    		console.log(veiculo);
    		this.veiculo = veiculo;
    		})
    		.catch(error => {
    		this.tratarErro(error);
    		});
    		}
    		},
    		atualizar() {
    			this.limparErros();
    			this.limparMensagem();
    			veiculosService.atualizar(this.veiculo)
    			.then(data => {
    			this.exibirMensagem('success', 'Veiculo atualizado com sucesso.');
    			})
    			.catch(error => {
    			this.tratarErro(error);
    			});
    			},
    			excluir() {
    				this.limparErros();
    				this.limparMensagem();
    				veiculosService.excluir(this.veiculo.id)
    				.then(data => {
    				this.veiculo = {id: null, placa: null, nomeProprietario: null,
    				dataEmplacamento: null, valorIPVA: null};
    				this.exibirMensagem('success', 'Veiculo excluído com sucesso.');
    				})
    				.catch(error => {
    				this.tratarErro(error);
    				});
    				},

  }     
})