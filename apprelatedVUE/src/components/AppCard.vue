<script setup>
import { computed, ref } from 'vue'

const props = defineProps({
  app: { type: Object, required: true },
  platform: { type: String, required: true },
})

const iconFailed = ref(false)

const iconStyle = computed(() =>
  iconFailed.value || !props.app.image ? { background: props.app.gradient } : {}
)

const linkText = computed(() =>
  props.platform === 'mac' ? 'Mac App Store' : 'App Store'
)
</script>

<template>
  <a
    class="app-card"
    :href="app.url"
    :aria-label="'在 App Store 查看 ' + app.name"
    target="_blank"
    rel="noopener"
  >
    <div class="app-icon" :style="iconStyle">
      <img
        v-if="app.image && !iconFailed"
        :src="app.image"
        :alt="app.name"
        @error="iconFailed = true"
      >
      <span v-else role="img" :aria-label="app.name + ' 图标'">{{ app.icon }}</span>
    </div>
    <div class="app-info">
      <div class="app-name">
        {{ app.name }}
        <span v-if="app.isNew" class="badge">NEW</span>
      </div>
      <div class="app-desc">{{ app.desc }}</div>
    </div>
    <span class="app-link">{{ linkText }}</span>
  </a>
</template>

<style scoped>
/* App 卡片 */
.app-card {
  display: flex; align-items: center; gap: 14px;
  background: var(--card);
  border-radius: 16px;
  padding: 14px;
  margin-bottom: 12px;
  box-shadow: var(--shadow);
  transition: transform .15s ease;
  text-decoration: none;      /* 去掉 <a> 默认下划线 */
  color: inherit;             /* 文字颜色继承主题色，不用链接默认色 */
  -webkit-tap-highlight-color: transparent;  /* 去掉移动端点击高亮 */
}
.app-card:active { transform: scale(0.98); }
.app-icon {
  flex-shrink: 0;
  width: 56px; height: 56px;
  border-radius: 13px;
  overflow: hidden;
  display: flex; align-items: center; justify-content: center;
  font-size: 28px;
}
.app-icon img { width: 100%; height: 100%; object-fit: cover; display: block; }
.app-info { flex: 1; min-width: 0; }
.app-name { font-size: 16px; font-weight: 600; display: flex; align-items: center; gap: 6px; }
.app-name .badge {
  font-size: 10px; font-weight: 600;
  color: #ff9f0a;
  background: rgba(255, 159, 10, 0.12);
  padding: 1px 6px; border-radius: 4px;
}
.app-desc {
  margin-top: 3px;
  font-size: 13px; color: var(--text-secondary);
  line-height: 1.45;
  display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical;
  line-clamp: 3; overflow: hidden;
}
.app-link {
  flex-shrink: 0;
  display: inline-flex; align-items: center; gap: 4px;
  text-decoration: none;
  font-size: 12px; font-weight: 600;
  color: #fff;
  background: var(--accent);
  padding: 7px 12px;
  border-radius: 999px;
}
.app-link::before { content: ""; width: 12px; height: 14px; background: #fff;
  -webkit-mask: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 384 512'%3E%3Cpath d='M318.7 268.7c-.2-36.7 16.4-64.4 50-84.8-18.8-26.9-47.2-41.7-84.7-44.6-35.5-2.8-74.3 20.7-88.5 20.7-15 0-49.4-19.7-76.4-19.7C63.3 141.2 4 184.8 4 273.5q0 39.3 14.4 81.2c12.8 36.7 59 126.7 107.2 125.2 25.2-.6 43-17.9 75.8-17.9 31.8 0 48.3 17.9 76.4 17.9 48.6-.7 90.4-82.5 102.6-119.3-65.2-30.7-61.7-90-61.7-91.9zm-56.6-164.2c27.3-32.4 24.8-61.9 24-72.5-24.1 1.4-52 16.4-67.9 34.9-17.5 19.8-27.8 44.3-25.6 71.9 26.1 2 49.9-11.4 69.5-34.3z'/%3E%3C/svg%3E") no-repeat center / contain;
  mask: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 384 512'%3E%3Cpath d='M318.7 268.7c-.2-36.7 16.4-64.4 50-84.8-18.8-26.9-47.2-41.7-84.7-44.6-35.5-2.8-74.3 20.7-88.5 20.7-15 0-49.4-19.7-76.4-19.7C63.3 141.2 4 184.8 4 273.5q0 39.3 14.4 81.2c12.8 36.7 59 126.7 107.2 125.2 25.2-.6 43-17.9 75.8-17.9 31.8 0 48.3 17.9 76.4 17.9 48.6-.7 90.4-82.5 102.6-119.3-65.2-30.7-61.7-90-61.7-91.9zm-56.6-164.2c27.3-32.4 24.8-61.9 24-72.5-24.1 1.4-52 16.4-67.9 34.9-17.5 19.8-27.8 44.3-25.6 71.9 26.1 2 49.9-11.4 69.5-34.3z'/%3E%3C/svg%3E") no-repeat center / contain;
}
</style>
