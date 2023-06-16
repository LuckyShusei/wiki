<template>
  <a-layout>
    <a-layout-content
        :style="{background: '#fff',padding: '24px',margin:0,minHeight: '280px' }"
    >
      <p>
        <a-form layout="inline" :model="param">
<!--          <a-form-item>-->
<!--            <a-input v-model:value="param.name" placeholder="Name">-->
<!--            </a-input>-->
<!--          </a-form-item>-->
          <a-form-item>
            <a-button type="primary" @click="handleQuery()">
              查询
            </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()">
              新增
            </a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="level1"
          :loading="loading"
          :pagination="false"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" style="max-width: 50px; max-height: 50px;" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑

            </a-button>
            <a-popconfirm
                title="Delete this category? You might not be able to recover it."
                ok-text="OK"
                cancel-text="Cancel"
                @confirm="handleDelete(record.id)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>

          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
  <a-modal
      title="category form"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="category" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="Name">
        <a-input v-model:value="category.name" />
      </a-form-item>
      <a-form-item label="Parent ID">
        <a-input v-model:value="category.parent" />
      </a-form-item>
      <a-form-item label="Sort">
        <a-input v-model:value="category.sort" type="textarea" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';
import {message} from "ant-design-vue";
import {Tool} from "@/Util/tool";

export default defineComponent({
  name: 'AdminCategory',
  setup() {
    const param = ref();
    param.value ={};
    const categorys = ref();

    const loading = ref(false);

    const columns = [
      {
        title: 'Name',
        dataIndex: 'name'
      },
      {
        title: 'Parent ID',
        key:'parent',
        dataIndex: 'parent'
      },
      {
        title: ' Sort',
        dataIndex: 'sort'
      },
      {
        title: 'Action',
        key: 'action',
        slots: {customRender: 'action'}
      }
    ];
    /**
     * 一级分类树，children属性就是二级分类
     * [{
     *   id: "",
     *   name: "",
     *   children: [{
     *     id: "",
     *     name: "",
     *   }]
     * }]
     */
    const level1 = ref(); // 一级分类树，children属性就是二级分类
    // level1.value = [];

    /**
     * 数据查询
     **/
    const handleQuery = () => {
      loading.value = true;
      // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
      axios.get("/category/all").then((response) => {
        loading.value = false;
        const data = response.data;
        if(data.success){
        categorys.value = data.content;
          console.log("原始数组：", categorys.value);

          level1.value = [];
          level1.value = Tool.array2Tree(categorys.value, 0);
          console.log("树形结构：", level1);
        }else{
          message.error(data.message);
        }
      });
    };



// -------- 表单 ---------
    /**
     * 数组，[100, 101]对应：前端开发 / Vue
     */
    const category = ref({});//定义响应式变量category
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;
      axios.post("/category/save",category.value).then((response) => {
        modalLoading.value = false;
        const data = response.data;
        if(data.success){
          modalVisible.value = false;
          modalLoading.value = false;
            //重新加载列表
          handleQuery();
        }else{
          message.error(data.massage);
        }

    });
    };
    // 编辑
    const edit = (record:any) => {
      modalVisible.value = true;
      category.value =Tool.copy(record); //record赋值到响应式变量category
    };
    // 新增
    const add = () => {
      modalVisible.value = true;
      category.value = {};
    };
    // 删除
    const handleDelete = (id: number) => {
      axios.delete("/category/delete/" + id).then((response) => {
        const data = response.data;
        if (data.success) {
          //重新加载列表
          handleQuery();
        }
      });
    };

    onMounted(() => {
      handleQuery();
    });

    return {
      param,
      // categorys,
      level1,
      columns,
      loading,
      handleQuery,

      edit,
      add,


      category,
      modalVisible,
      modalLoading,
      handleModalOk,

      handleDelete
    }
  }
});
</script>

