<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/">
      <router-link to="/">Home</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/admin-user">
        <router-link to="/admin/admin-user">User</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/admin-ebook">
      <router-link to="/admin/admin-ebook">Ebook</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/admin-category">
        <router-link to="/admin/admin-category">Category</router-link>
      </a-menu-item>
      <a-menu-item key="/about">
        <router-link to="/about">about us</router-link>
      </a-menu-item>
      <a class="login-menu"  @click="showLoginModal">
        <span>Login</span>
      </a>
    </a-menu>
    <a-modal
        title="Login"
        v-model:visible="loginModalVisible"
        :confirm-loading="loginModalLoading"
        @ok="login"
    >
      <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
        <a-form-item label="Login Name">
          <a-input v-model:value="loginUser.loginName" />
        </a-form-item>
        <a-form-item label="Password">
          <a-input v-model:value="loginUser.password" type="password" />
        </a-form-item>
      </a-form>
    </a-modal>

  </a-layout-header>

</template>

<script lang="ts">
import { defineComponent ,ref} from 'vue';

export default defineComponent({
  name: 'the-header',
  setup () {
     // 用来登录
    const loginUser = ref({
      loginName: "test",
      password: "test"
    });
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    // 登录
    const login = () => {
      console.log("Start Login");
    };

    return {
      loginModalVisible,
      loginModalLoading,
      showLoginModal,
      loginUser,
      login

    }
  }

});
</script>

<style>
.login-menu {
  float: right;
  color: white;
}
</style>

