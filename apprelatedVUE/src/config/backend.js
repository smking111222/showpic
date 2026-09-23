// ====== 后端服务地址（唯一的配置源）======
// 该文件同时被两类环境引用，务必保持零浏览器/Node 专属依赖：
//   1. vite.config.js（Node 环境）：dev server 用它做 /api 代理
//   2. src/data/pageData.js（浏览器环境）：静态托管时直连后端接口
export const BACKEND_URL = 'https://app-api-mckafsnitu.cn-beijing.fcapp.run';

// 后端接口前缀（相对路径，配合代理或同源托管使用）
export const API_PREFIX = '/api';
