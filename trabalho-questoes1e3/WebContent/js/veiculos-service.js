class VeiculosService {
  constructor() {
    this.axios = axios.create({ baseURL: 'api/veiculos' });
    
    this.axios.interceptors.request.use(
    (config) => {
    config.headers['Authorization'] = sessionStorage.authtoken;
    return config;
    },
    (error) => {
    return Promise.reject(error);
    }
    );
    
    this.axios.interceptors.response.use(
    		(response) => {
    		sessionStorage.authtoken = response.headers.authorization;
    		return Promise.resolve(response);
    		},
    		(error) => {
    		if (error.response.status == 403) {
    		window.location = 'login.html';
    		return error;
    		} else {
    		return Promise.reject(error);
    		}
    		}
    		);
    		
  }

  request(method, url, data) {
    return this.axios[method](url, data)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        console.error(
                  '[ERROR: VeiculosService] ' + method + ' ' + url, error);
        return Promise.reject(error);
      });
  }

  selecionar(id) {
    return this.request('get', '/' + id); 
  }

  selecionarTodos() { 
    return this.request('get');
  }
  
  inserir(veiculo) {
  	return this.request('post', '', veiculo);
  }
  
  atualizar(veiculo) {
	  return this.request('put', '/' + veiculo.id, veiculo);
	  }
  
  excluir(id) {
	  return this.request('delete', '/' + id);
	  }
}

var veiculosService = new VeiculosService();