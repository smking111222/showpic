<script setup>
import { computed } from 'vue'

const props = defineProps({
  developer: { type: Object, required: true },
})

const year = computed(() => new Date().getFullYear())
</script>

<template>
  <footer class="footer">
    <div><a :href="'mailto:' + developer.email">{{ developer.email }}</a></div>
    <div>© {{ year }} {{ developer.name }} · 保留所有权利</div>
  </footer>
</template>

<style scoped>
/* 底部 */
.footer {
  text-align: center;
  padding-top: 20px;
  margin-top: 8px;
  border-top: 1px solid var(--divider);
  font-size: 12px;
  color: var(--text-tertiary);
  line-height: 2;
}
.footer a { color: var(--accent); text-decoration: none; }
</style>
