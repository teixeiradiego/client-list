<template lang="pug">

el-container.wrapper
	el-header
		h1 My Contacts
	el-container
		el-aside(width="200px")
			Menu
		el-main
			router-view

</template>

<script>

import Menu from './views/Menu.vue';

export default {
	components: {
		Menu
	}
}

</script>

<style lang="scss">
* {
	margin: 0;
	padding: 0;
}
#app {
	font-family: "Avenir", Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-align: center;
	color: #2c3e50;
}
#nav {
	padding: 30px;
	a {
		font-weight: bold;
		color: #2c3e50;
		&.router-link-exact-active {
			color: #42b983;
		}
	}
}
.el-header {
	color: #FFF;
	background-color: #000;
	line-height: 60px;
}
.el-aside {
	height: calc(100vh - 60px);
}
.el-main {
	height: calc(100vh - 60px);
	overflow: auto;
}
</style>
