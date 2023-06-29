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
      <a-menu-item key="/admin/admin-user">:style="user.id? {} : {display:'none'}">
        <router-link to="/admin/admin-user">User</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/admin-ebook">:style="user.id? {} : {display:'none'}">
      <router-link to="/admin/admin-ebook">Ebook</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/admin-category">:style="user.id? {} : {display:'none'}">
        <router-link to="/admin/admin-category">Category</router-link>
      </a-menu-item>
      <a-menu-item key="/about">
        <router-link to="/about">about us</router-link>
      </a-menu-item>
      <a-popconfirm
          title="Are you sure logout?"
          ok-text="Yes"
          cancel-text="No"
          @confirm="logout()"
      >
        <a class="login-menu" v-show="user.id">
          <span>Logout</span>
        </a>
      </a-popconfirm>

      <a class="login-menu" v-show="user.id">
        <span>Hello：{{user.name}}</span>
      </a>
      <a class="login-menu" v-show="!user.id" @click="showLoginModal">
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
import { defineComponent ,ref , computed} from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import store from "@/store";

// declare let hexMd5: any;
// declare let KEY: any;

export default defineComponent({
  name: 'the-header',
  setup () {
    // 登录后保存
    const user = computed(() => store.state.user);
    // const user =ref();
    // user.value ={};
    // 用来登录
    const loginUser = ref({
      loginName: "test",
      password: "test123"
    });
    const loginModalVisible = ref(false);
    const loginModalLoading = ref(false);
    const showLoginModal = () => {
      loginModalVisible.value = true;
    };

    // 登录
    const login = () => {
      console.log("Start Login");
        loginModalLoading.value = true;
        // loginUser.value.password = hexMd5(loginUser.value.password + KEY);
        axios.post('/user/login', loginUser.value).then((response) => {
          loginModalLoading.value = false;
          const data = response.data;
          if (data.success) {
            loginModalVisible.value = false;
            message.success("Login successful！");
            // user.value = data.content;
            store.commit("setUser", data.content);
          } else {
            message.error(data.message);
          }
        });
    };

    // 退出登录
    const logout = () => {
      console.log("退出登录开始");
      axios.get('/user/logout/' + user.value.token).then((response) => {
        const data = response.data;
        if (data.success) {
          message.success("退出登录成功！");
           store.commit("setUser", {});
        } else {
          message.error(data.message);
        }
      });
    };

    return {
      loginModalVisible,
      loginModalLoading,
      showLoginModal,
      loginUser,
      login,
      user,
      logout




    }
  }

});
</script>

<style>
.login-menu {
  float: right;
  color: whitesmoke;
  padding-left: 15px;
}
</style>

