// 后端地址唯一配置源（vite.config.js 的 /api 代理也引用同一份配置）
import { BACKEND_URL, API_PREFIX } from '../config/backend.js';

// App Store 链接（按 App ID 生成）
export const appStoreURL = (id) => `https://apps.apple.com/cn/app/id${id}`;

// ====== 后端接口地址（自动判断）======
// 规则：
//   - 本地调试时，dev server 会把 /api 代理到后端（见 vite.config.js），同源无 CORS
//   - 页面托管在后端同域时（如 *.fcapp.run），接口走同源
//   - 页面托管在 GitHub Pages 等静态平台时，接口直接走阿里云 FC
const LOCAL_BACKEND_HOSTS = ['localhost', '127.0.0.1'];
const isServedByBackend =
    LOCAL_BACKEND_HOSTS.includes(location.hostname) ||
    location.hostname.endsWith('.fcapp.run');
export const API_BASE = isServedByBackend ? '' : BACKEND_URL;

// ====== 兜底默认数据：接口不可用时保证页面仍可展示 ======
export const DEFAULT_DATA = {
  developer: {
    avatar: "🌱",
    name: "点木成材",
    slogan: "独立开发者 · 用 App 记录成长，让自律变简单",
    email: "bestheqin@gmail.com",
  },
  sections: [
    {
      title: "iOS 应用 · iOS Apps",
      platform: "ios",
      apps: [
        {
          name: "BeBetter 自律打卡",
          image: new URL('../assets/icons/bebetter.png', import.meta.url).href,
          icon: "⏱️",
          gradient: "linear-gradient(135deg, #4f8ef7 0%, #8e54e9 100%)",
          desc: "轻量自律打卡工具：设定目标与期限，每日提醒打卡，用连续的坚持看见更好的自己。",
          url: appStoreURL(6810910437),
          isNew: true,
        },
        {
          name: "PixShow 💎",
          image: new URL('../assets/icons/pixshow.png', import.meta.url).href,
          icon: "🖼️",
          gradient: "linear-gradient(135deg, #ff9f0a 0%, #ff375f 100%)",
          desc: "把照片变成艺术：一键将照片变成卡片、油画、素描、漫画、复古胶片等艺术效果，自动提取主色调，导出高清图片",
          url: appStoreURL(6771634234),
          isNew: false,
        },
      ],
    },
    {
      title: "Mac 应用 · Mac Apps",
      platform: "mac",
      apps: [
        {
          name: "WellDone",
          image: new URL('../assets/icons/welldone.png', import.meta.url).href,
          icon: "✅",
          gradient: "linear-gradient(135deg, #30d158 0%, #0a84ff 100%)",
          desc: "始终置顶的轻量待办提醒：悬浮桌面随时可见，到期循环提醒不遗漏，本地存储安全私密。",
          url: appStoreURL(6776590766),
          isNew: false,
        },
      ],
    },
  ],
};

// 从后端接口拉取页面数据；失败时回退到内置默认数据
export async function fetchPageData() {
  try {
    const res = await fetch(API_BASE + API_PREFIX + '/apps', {
      headers: { Accept: 'application/json' },
    });
    if (!res.ok) throw new Error('HTTP ' + res.status);
    const json = await res.json();
    if (json.code !== 0 || !json.data) throw new Error(json.message || '接口返回异常');
    return { developer: json.data.developer, sections: json.data.sections, loadFailed: false };
  } catch (err) {
    console.warn('[app] 接口请求失败，使用内置兜底数据：', err);
    return {
      developer: { ...DEFAULT_DATA.developer },
      sections: DEFAULT_DATA.sections,
      loadFailed: true,
    };
  }
}
