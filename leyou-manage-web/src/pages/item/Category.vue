<template>
  <v-card>
      <v-flex xs12 sm10>
        <v-tree url="/item/category/list"

                :isEdit="isEdit"
                @handleAdd="handleAdd"
                @handleEdit="handleEdit"
                @handleDelete="handleDelete"
                @handleClick="handleClick"
        />
      </v-flex>
    <!-- :treeData="treeData"指定的話會有預設的樹狀結構-->
    <!--真實情況是要用url遠程請求獲取數據-->
  </v-card>
</template>

<script>
  import {treeData} from "../../mockDB"
  export default {
    name: "category",
    data() {
      return {
        isEdit:true,
        treeData
      }
    },
    methods: {
      handleAdd(node) {
        console.log("add .... ");
        console.log(node);
      },
      handleEdit(id, name) {
        console.log("edit... id: " + id + ", name: " + name)
      },
      handleDelete(id) {
        console.log("handleDelete ~")
         this.$http.delete("/item/category/list", {
          params: {
            pid: id, // 搜索条件
          }
        }).then(resp => { 
          this.$forceUpdate();
        })
      },
      handleClick(node) {
        console.log(node)
      }
    }
  };
</script>

<style scoped>

</style>
