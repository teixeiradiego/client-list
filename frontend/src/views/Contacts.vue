<template lang="pug">

#home

	el-input(placeholder='Search by name', v-model='filter', style='width: 300px;', @keyup.enter.native="getPeople(1)")
		el-button(slot='append', icon='el-icon-search', @click="getPeople(1)")

	div(style='margin-top: 15px;')
		el-table(:data='people', style='width: 300px;')
			el-table-column(label='', width='80')
				template(slot-scope="scope")
					avatar(:username="scope.row.name", :src="scope.row.photoUrl", :size="40")
			el-table-column(prop='name', label='Name', width='220')

	div(style='margin-top: 15px;')
		el-pagination(background, layout="prev, pager, next", :total="totalPeople", :page-size="pageSize",
				:current-page="currentPage", @current-change="getPeople")

</el-pagination>

</template>

<script>

import Avatar from 'vue-avatar';
import personService from '../services/personService';

export default {
	name: 'home',
	components: {
		Avatar
	},
	data() {
		return {
			people: [],
			totalPeople: 0,
			currentPage: 1,
			pageSize: 10,
			filter: ''
		}
	},
	methods: {
		getPeople(currentPage) {

			if(currentPage) {
				this.currentPage = currentPage;
			}

			personService.getPeople(this.filter, this.currentPage - 1, this.pageSize).then(response => {
				this.people = response.data.content;
				this.totalPeople = response.data.totalElements;
			})

		}
	},
	created() {
		this.getPeople();
	}
}

</script>
