import axios from 'axios';

const personService = {

	getPeople: (filter, pageNumber, pageSize) => {

		return axios.get('/people', {
			params: {filter, pageNumber, pageSize}
		});

	}

};

export default personService;
