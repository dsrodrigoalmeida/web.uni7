class PalpitesService {
  constructor() {
    this.axios = axios.create({ baseURL: 'api/palpites' });
        		
  }

  request(method, url, data) {
    return this.axios[method](url, data)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.error(
                  '[ERROR: PalpitesService] ' + method + ' ' + url, error);
        return Promise.reject(error);
      });
  }

  selecionar(id) {
    return this.request('get', '/' + id); 
  }

  selecionarTodos() { 
    return this.request('get');
  }
  
  atualizar(palpite) {
	  return this.request('put', '/' + palpite.id, palpite);
	  }
  
}

var palpitesService = new PalpitesService();