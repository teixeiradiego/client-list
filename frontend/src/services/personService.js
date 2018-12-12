import axios from 'axios';

const personService = {

	getPeople: (filter, currentPage, pageSize) => {

		return axios.get('/people', {
			params: {filter, currentPage, pageSize}
		});

	}

};

export default personService;
