module.exports = {

	devServer: {
		proxy: {
			'/': {
				target: 'http://localhost:9000',
				changeOrigin: true
			}
		}
	},
	outputDir: '../backend/src/main/resources/static',
	assetsDir: 'static'
};
