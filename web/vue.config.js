// vue.config.js for less-loader@6.0.0
module.exports = {
    css: {
        loaderOptions: {
            less: {
                lessOptions: {
                    modifyVars: {
                        'primary-color': '#5bb296',
                        'link-color': '#528674',
                        'border-radius-base': '2px',
                    },
                    javascriptEnabled: true,
                },
            },
        },
    },
        devServer: {
            proxy: {
                '/*': {
                    target: 'http://45.86.74.188:8880',
                    // ↑这个就是你的接口地址↑
                    changeOrigin: true,
                    pathRewrite: {
                        '^/api': '/'
                    }
                }
            }
        }
};