<script setup>
import { onMounted, ref } from 'vue'
import HeroHeader from './components/HeroHeader.vue'
import AppCard from './components/AppCard.vue'
import PageFooter from './components/PageFooter.vue'
import { DEFAULT_DATA, fetchPageData } from './data/pageData.js'

const loading = ref(true)
const developer = ref({ ...DEFAULT_DATA.developer })
const sections = ref([])

onMounted(async () => {
  const data = await fetchPageData()
  developer.value = data.developer
  sections.value = data.sections
  loading.value = false
})
</script>

<template>
  <!-- 加载中提示 -->
  <div v-if="loading" class="loading">加载中…</div>

  <template v-else>
    <!-- 顶部头像与签名 -->
    <HeroHeader :developer="developer" />

    <!-- App 列表：按分区循环 -->
    <template v-for="section in sections" :key="section.platform">
      <div class="section-title">{{ section.title }}</div>
      <AppCard
        v-for="app in section.apps"
        :key="app.name"
        :app="app"
        :platform="section.platform"
      />
    </template>

    <!-- 底部信息 -->
    <PageFooter :developer="developer" />
  </template>
</template>

<style scoped>
/* 分区标题 */
.section-title {
  font-size: 13px;
  font-weight: 600;
  color: var(--text-tertiary);
  text-transform: uppercase;
  letter-spacing: 1px;
  margin: 18px 4px 10px;
}
</style>
