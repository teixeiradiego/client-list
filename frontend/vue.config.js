module.exports = {

	devServer: {
		proxy: {
			'/': {
				target: 'http://localhost:9000',
				changeOrigin: true
			}
		}
	},
	outputDir: 'target/dist',
	assetsDir: 'static'
};
