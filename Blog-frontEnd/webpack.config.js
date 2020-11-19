module.exports = (config, env) => {
	config.resolve.alias = {
		'Assets': `${__dirname}/src/assets`,
		'Components': `${__dirname}/src/components`,
		'Models': `${__dirname}/src/models`,
		'Routes': `${__dirname}/src/routes`,
		'Services': `${__dirname}/src/services`,
		'Utils': `${__dirname}/src/utils`,
		'Styles': `${__dirname}/src/styles`,
		'Constant': `${__dirname}/src/constant`,
	};

	return config;
}
	