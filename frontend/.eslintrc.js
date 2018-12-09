module.exports = {
	root: true,
	env: {
		node: true
	},
	'extends': [
		'plugin:vue/essential'
	],
	rules: {
		// 'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
		// 'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
		// 'indent': ['error', 'tab'],
		// 'no-tabs': ['error', {
		// 	allowIndentationTabs: true
		// }],
		// 'semi': ['error', 'always']
		"no-bitwise": 2,
		"camelcase": 2,
		"curly": 2,
		"eqeqeq": 2,
		"wrap-iife": [2, "outside"],
		"indent": [2, "tab", {
			"SwitchCase": 1,
			"VariableDeclarator": 1,
			"FunctionDeclaration": {
				"body": 1,
				"parameters": 2
			},
			"FunctionExpression": {
				"body": 1,
				"parameters": 2
			},
			"CallExpression": {
				"arguments": 1
			},
			"ArrayExpression": 1,
			"ObjectExpression": 1,
			"ImportDeclaration": 1,
			"flatTernaryExpressions": true
		}],
		"no-use-before-define": [2, {
			"functions": false
		}],
		"new-cap": 2,
		"no-caller": 2,
		"quotes": [2, "single", {
			"avoidEscape": true
		}],
		// "strict": 0,
		"no-loop-func": 1,
		"no-trailing-spaces": 2,
		"comma-dangle": [2, "never"],
		"no-multi-assign": 2,
		"no-multiple-empty-lines": [2, {
			"max": 1
		}],
		"no-unneeded-ternary": 2,
		"no-whitespace-before-property": 2,
		"nonblock-statement-body-position": [2, "below"],
		"object-curly-newline": [2, {
			"consistent": true
		}],
		"object-curly-spacing": [2, "never"],
		"no-console": 1,
		"brace-style": [2, "1tbs", {
			"allowSingleLine": false
		}],
		"comma-spacing": [2, {
			"before": false,
			"after": true
		}],
		"comma-style": [2, "last"],
		"array-bracket-newline": [2, "consistent"],
		"array-bracket-spacing": [2, "never"],
		"func-call-spacing": [2, "never"],
		"function-paren-newline": [2, "consistent"],
		"implicit-arrow-linebreak": [2, "beside"],
		"lines-between-class-members": [2, "always"],
		"operator-linebreak": [2, "before"],
		"no-case-declarations": 0,
		"no-fallthrough": 1,
		"keyword-spacing": [2, {
			"overrides": {
				"if": {
					"after": false
				},
				"for": {
					"after": false
				},
				"while": {
					"after": false
				},
				"switch": {
					"after": false
				},
				"catch": {
					"after": false
				},
				"super": {
					"after": false
				}
			}
		}],
		"padding-line-between-statements": [2,
			{
				"blankLine": "always",
				"prev": "*",
				"next": "return"
			},
			{
				"blankLine": "always",
				"prev": "*",
				"next": "block-like"
			},
			{
				"blankLine": "always",
				"prev": "block-like",
				"next": "*"
			},
			{
				"blankLine": "always",
				"prev": "class",
				"next": "*"
			},
			{
				"blankLine": "always",
				"prev": "*",
				"next": "class"
			},
			{
				"blankLine": "always",
				"prev": "function",
				"next": "*"
			},
			{
				"blankLine": "always",
				"prev": "*",
				"next": "function"
			}
		]
	},
	parserOptions: {
		parser: 'babel-eslint'
	}
}
