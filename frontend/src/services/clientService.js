import axios from 'axios';

const clientService = {

	getClients: (filter, currentPage, pageSize) => {

		return axios.get('/clients', {
			params: {filter, currentPage, pageSize}
		});

	}

};

export default clientService;
