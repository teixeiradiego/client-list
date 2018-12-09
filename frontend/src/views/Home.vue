<template lang="pug">

#home

	el-input(placeholder='Search by name', v-model='filter', style='width: 300px;', @keyup.enter.native="getClients(1)")
		el-button(slot='append', icon='el-icon-search', @click="getClients(1)")

	div(style='margin-top: 15px;')
		el-table(:data='clients', style='width: 300px;')
			el-table-column(label='', width='80')
				template(slot-scope="scope")
					avatar(:username="scope.row.name", :src="scope.row.photoUrl", :size="40")
			el-table-column(prop='name', label='Name', width='220')

	div(style='margin-top: 15px;')
		el-pagination(background, layout="prev, pager, next", :total="totalClients", :page-size="pageSize",
				:current-page="currentPage", @current-change="getClients")

</el-pagination>

</template>

<script>

import Avatar from 'vue-avatar';
import clientService from '../services/clientService';

export default {
	name: 'home',
	components: {
		Avatar
	},
	data() {
		return {
			clients: [],
			totalClients: 0,
			currentPage: 1,
			pageSize: 10,
			filter: ''
		}
	},
	methods: {
		getClients(currentPage) {

			if(currentPage) {
				this.currentPage = currentPage;
			}

			clientService.getClients(this.filter, this.currentPage - 1, this.pageSize).then(response => {
				this.clients = response.data.content;
				this.totalClients = response.data.totalElements;
			})

		}
	},
	created() {
		this.getClients();
	}
}

</script>
