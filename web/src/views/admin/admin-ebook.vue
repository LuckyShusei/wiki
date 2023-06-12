<template>
  <a-layout>
    <a-layout-content
        style="padding: 0 50px">
      <a-breadcrumb style="margin: 16px 0">
        <a-breadcrumb-item>Home</a-breadcrumb-item>
        <a-breadcrumb-item>List</a-breadcrumb-item>
        <a-breadcrumb-item>App</a-breadcrumb-item>
      </a-breadcrumb>
      <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }"
        >
          <div class="about">
            <h1>Ebook Manage</h1>
          </div>

        </a-layout-content>
      </a-layout>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="ebooks"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:category="{ text, record }">
        <a-space size="small">
             <a-button type="primary">
              编辑
            </a-button>
            <a-button type="danger">
                删除
              </a-button>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'AdminEbook',
  setup() {
    const ebooks = ref();
    const pagination = ref({
      current: 1,
      pageSize: 2,
      total: 0
    });
    const loading = ref(false);

    const columns = [
      {
        title: 'Cover',
        dataIndex: 'cover',
        slots: { customRender: 'cover' }
      },
      {
        title: 'Name',
        dataIndex: 'name'
      },
      {
        title: 'Category',
        slots: { customRender: 'category' }
      },
      {
        title: ' Documents',
        dataIndex: 'docCount'
      },
      {
        title: 'Views',
        dataIndex: 'viewCount'
      },
      {
        title: 'Likes',
        dataIndex: 'voteCount'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * 数据查询
     **/
    const handleQuery = (params: any) => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      axios.get("/ebook/list", params).then((response) => {
        loading.value = false;
        const data = response.data;
        ebooks.value = data.content;
          // 重置分页按钮
          pagination.value.current = params.page;
      });
    };

    /**
     * 表格点击页码时触发
     */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有啥：" + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    onMounted(() => {
      handleQuery({});
    });

    return {
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChange,
    }
  }
});
</script>

