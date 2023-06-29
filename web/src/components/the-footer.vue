<template>
  <a-layout-footer style="text-align: center">
    My Library  Hello: {{user.name}}
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
