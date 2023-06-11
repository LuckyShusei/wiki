<template>
  <a-layout-footer style="text-align: center">
    Wiki ©2023 Created by Syusei
  </a-layout-footer>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'the-footer',
  props: {
    msg: String,
  },
});
</script>
