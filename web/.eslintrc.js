module.exports = {
  root: true,

  env: {
    node: true
  },

  extends: ["plugin:vue/essential", "@vue/prettier", "@vue/typescript"],

  rules: {
    'no-console': 'off',
    'no-debugger': 'off',
    'vue/max-attributes-per-line': 'off'
  },

  parserOptions: {
    parser: 'typescript-eslint-parser'
  },

  'extends': [
    'plugin:vue/strongly-recommended',
    '@vue/prettier',
    '@vue/typescript'
  ]
};
