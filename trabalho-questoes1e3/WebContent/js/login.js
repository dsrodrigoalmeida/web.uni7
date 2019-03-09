var app = new Vue({
el : '#app',
data: {
username: '',
senha: '',
mensagem: { exibir: false, tipo: 'info', texto: '' },
},
methods: {
login(event){
event.preventDefault();
this.limparMensagem();
loginService.login(this.username, this.senha)
.then(response => {
window.location = 'index.html';
})
.catch(error => {
this.senha = null;
this.exibirMensagem('error', 'Usuário ou senha inválidos.');
});
},
exibirMensagem(tipo, texto) {
	this.mensagem = { tipo, texto, exibir: true };
	},
	limparMensagem() {
	this.mensagem = { tipo: 'info', texto: '', exibir: false };
	},
	}
	});

