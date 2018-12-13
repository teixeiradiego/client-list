<template lang="pug">

el-menu
	el-menu-item(index="1", router=true)
		i.el-icon-menu
		span Contacts

</template>

<script>

export default {
	name: 'menu'
}

</script>

<style lang="scss">

.el-menu {
	height: calc(100vh - 60px);
}

</style>