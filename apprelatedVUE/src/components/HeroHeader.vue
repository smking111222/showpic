<script setup>
defineProps({
  developer: { type: Object, required: true },
})
</script>

<template>
  <header class="hero">
    <div class="avatar" role="img" aria-label="开发者">{{ developer.avatar }}</div>
    <h1>{{ developer.name }}</h1>
    <p class="slogan">{{ developer.slogan }}</p>
  </header>
</template>

<style scoped>
/* 顶部头像区 */
.hero { text-align: center; padding: 8px 0 24px; }
.avatar {
  width: 76px; height: 76px;
  margin: 0 auto 14px;
  border-radius: 22px;
  background: linear-gradient(135deg, #4f8ef7 0%, #8e54e9 100%);
  display: flex; align-items: center; justify-content: center;
  font-size: 38px;
  box-shadow: var(--shadow);
}
.hero h1 { font-size: 22px; font-weight: 700; letter-spacing: 1px; }
.hero .slogan { margin-top: 6px; font-size: 13px; color: var(--text-secondary); }
</style>
