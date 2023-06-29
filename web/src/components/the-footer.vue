<template>
  <a-layout-footer style="text-align: center">
    My Library <span v-show="user.id">,Hello: {{user.name}}</span>
  </a-layout-footer>
</template>

<script lang="ts">
import { defineComponent,computed} from 'vue';
import store from "@/store";

export default defineComponent({
  name: 'the-footer',
  // props: {
  //   msg: String,
  // },
  setup(){
    const user = computed(() => store.state.user);
    return{
      user
    }
  }
});
</script>
