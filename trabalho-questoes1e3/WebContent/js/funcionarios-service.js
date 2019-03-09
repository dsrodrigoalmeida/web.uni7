class VeiculosService {
  constructor() {
    this.axios = axios.create({ baseURL: 'api/funcionarios' });
    
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


  


}
